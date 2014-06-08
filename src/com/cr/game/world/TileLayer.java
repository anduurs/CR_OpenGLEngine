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
	
	private int width, height;

	private Bitmap bitmap;
	private Mesh mesh;
	private Shader shader;
	private Transform transform;
	
	private HashMap<Integer, Tile> tiles;
	
	private float xLow = 0;
	private float xHigh = 0;
	private float yLow = 0;
	private float yHigh = 0;
	
	private float scaleFactor = 0.2f;
	
	public TileLayer(int width, int height, Transform transform){
		bitmap = new Bitmap(width, height);
		
		this.width = width;
		this.height = height;
		this.transform = transform;
		this.shader = World.getShader();
		
		tiles = new HashMap<Integer, Tile>();
	}
	
	public TileLayer(String name, Transform transform){
		this.transform = transform;
		bitmap = new Bitmap(name);
		
		this.width = bitmap.getWidth();
		this.height = bitmap.getHeight();
		this.transform = transform;
		this.shader = World.getShader();
		
		tiles = new HashMap<Integer, Tile>();
	}
	
	public void generateTileLayer(){
		List<Vertex> vertices = new ArrayList<Vertex>();
		List<Integer> indices = new ArrayList<Integer>();
		
		float tWidth = Tile.getAtlasWidth();
		float tHeight = Tile.getAtlasHeight();
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				if(bitmap.getPixel(x, y) == 0) continue;
				
				calcTexCoords(tiles.get(bitmap.getPixel(x, y)).getRow(), tiles.get(bitmap.getPixel(x, y)).getCol());
			
				float xPos = x * tWidth ;
				float yPos = y * tHeight ;
				
				float xOffset = 20f;
				float yOffset = 18f;
				
				indices.add(vertices.size() + 0);
				indices.add(vertices.size() + 1);
				indices.add(vertices.size() + 2);
				
				indices.add(vertices.size() + 2);
				indices.add(vertices.size() + 3);
				indices.add(vertices.size() + 0);
				
				vertices.add(new Vertex(new Vector3f(xPos, yPos, 0), new Vector2f(xLow,yLow)));
				vertices.add(new Vertex(new Vector3f(xPos, yPos + tHeight + yOffset, 0), new Vector2f(xLow,yHigh)));
				vertices.add(new Vertex(new Vector3f(xPos + tWidth + xOffset , yPos + tHeight + yOffset, 0), new Vector2f(xHigh,yHigh)));
				vertices.add(new Vertex(new Vector3f(xPos + tWidth + xOffset , yPos, 0), new Vector2f(xHigh,yLow)));
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
	
	public void renderTileLayer(float xScroll, float yScroll, float depth){
		transform.translate(-xScroll, -yScroll, depth);
		transform.scale(scaleFactor, scaleFactor, 0);
		//transform.rotate(0, 0, xScroll-width/2);
		shader.bind();
		shader.setUniform("transformation", transform.getOrthoTransformation());
		Tile.getTexture().bind();
		mesh.render();
		Tile.getTexture().unbind();
		shader.unbind();
	}
	
	private void calcTexCoords(float row, float col){
		xLow = col / Tile.TILE_ATLAS_COLS;
		xHigh = xLow + (1 / Tile.TILE_ATLAS_COLS);
		yLow = row / Tile.TILE_ATLAS_ROWS;
		yHigh = yLow + (1 / Tile.TILE_ATLAS_ROWS);
	}
	
	public void addTileType(int color, Tile tile){
		tiles.put(color, tile);
	}
	
	public void setTile(int x, int y, int color){
		bitmap.setPixel(x, y, color);
	}
	
	public void removeTile(int x, int y){
		bitmap.setPixel(x, y, 0);
	}
	
	public int getTileID(){
		int col = 0;
		for(Integer i : tiles.keySet())
			col = i;
		return col;
	}
	
	public int getTileID(int x, int y){
		return bitmap.getPixel(x, y);
	}
	
	public Tile getTile(int x, int y){
		return tiles.get(bitmap.getPixel(x, y));
	}
	
	public Tile getTile(int color){
		return tiles.get(color);
	}
	
	public boolean tileExists(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return false;
		if(tiles.containsKey(bitmap.getPixel(x, y)))
			return true;
		return false;
	}
	

	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

}
