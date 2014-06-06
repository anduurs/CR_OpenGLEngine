package com.cr.game.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cr.game.graphics.Bitmap;
import com.cr.game.graphics.Mesh;
import com.cr.game.graphics.Shader;
import com.cr.game.util.Transform;
import com.cr.game.util.Vector2f;
import com.cr.game.util.Vector3f;
import com.cr.game.util.Vertex;
import com.cr.game.world.tile.Tile;

public class TileLayer {
	
	public int[] pixels;
	private int width, height;

	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	
	private HashMap<Integer, Tile> tiles;
	
	private Bitmap bitmap;

	public TileLayer(int width, int height, Transform transform){
		this.width = width;
		this.height = height;
		this.transform = transform;
		this.shader = World.getShader();
		
		pixels = new int[width*height];
		
		tiles = new HashMap<Integer, Tile>();
	}
	
	public TileLayer(String name){
		bitmap = new Bitmap(name);
	}
	
	public void generateTileLayer(){
		List<Vertex> vertices = new ArrayList<Vertex>();
		List<Integer> indices = new ArrayList<Integer>();
		
		float tWidth = tiles.get(getTileID()).getTexture().getWidth();
		float tHeight = tiles.get(getTileID()).getTexture().getHeight();
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(pixels[x+y*width] == 0) continue;
				
				float xPos = x * tWidth ;
				float yPos = y * tHeight ;
				
				float xOffset = 0f;
				float yOffset = 0f;
				
				indices.add(vertices.size() + 0);
				indices.add(vertices.size() + 1);
				indices.add(vertices.size() + 2);
				
				indices.add(vertices.size() + 2);
				indices.add(vertices.size() + 3);
				indices.add(vertices.size() + 0);
				
				vertices.add(new Vertex(new Vector3f(xPos, yPos, 0), new Vector2f(0,0)));
				vertices.add(new Vertex(new Vector3f(xPos, yPos + tHeight + yOffset, 0), new Vector2f(0,1)));
				vertices.add(new Vertex(new Vector3f(xPos + tWidth + xOffset , yPos + tHeight + yOffset, 0), new Vector2f(1,1)));
				vertices.add(new Vertex(new Vector3f(xPos + tWidth + xOffset , yPos, 0), new Vector2f(1,0)));
			}
		}
		
		Vertex[] vertexArray = new Vertex[vertices.size()];
		Integer[] indexArray = new Integer[indices.size()];
		
		vertices.toArray(vertexArray);
		indices.toArray(indexArray);
		
		int[] iArray = new int[indexArray.length];
		
		for(int i = 0; i < indexArray.length; i++)
			iArray[i] = indexArray[i];
		
		mesh = new Mesh(vertexArray, iArray);
	}
	
	public void addTile(int color, Tile tile){
		tiles.put(color, tile);
	}
	
	public void removeTile(int x, int y){
		pixels[x + (y*width)] = 0;
	}
	
	public int getTileID(){
		int col = 0;
		for(Integer i : tiles.keySet())
			col = i;
		return col;
	}
	
	public boolean validID(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return false;
		return true;
	}
	
	public int getTileID(int x, int y){
		return pixels[x + (y*width)];
	}
	
	public Tile getTile(int x, int y){
		return tiles.get(pixels[x + (y*width)]);
	}
	
	public Tile getTile(int color){
		return tiles.get(color);
	}
	
	public boolean shouldRender(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return false;
		if(tiles.containsKey(pixels[x + (y*width)]))
			return true;
		return false;
	}
	
	public void renderTileLayer(float xScroll, float yScroll, float depth){
		transform.translate(-xScroll, -yScroll, depth);
		shader.bind();
		shader.setUniform("transformation", transform.getFullTransformation());
		tiles.get(getTileID()).getTexture().bind();
		mesh.render();
		tiles.get(getTileID()).getTexture().unbind();
		shader.unbind();
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

}
