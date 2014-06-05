package com.cr.game.world;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cr.game.core.Window;
import com.cr.game.graphics.Mesh;
import com.cr.game.graphics.Screen;
import com.cr.game.graphics.Shader;
import com.cr.game.util.Randomizer;
import com.cr.game.util.Transform;
import com.cr.game.util.Vector2f;
import com.cr.game.util.Vector3f;
import com.cr.game.util.Vertex;
import com.cr.game.world.tile.Tile;

public class TileLayer {
	
	private BufferedImage img;
	public int[] pixels;
	private int width, height;
	
	private HashMap<Integer, Tile> tiles;
	
	public Tile getTile(int color){
		return tiles.get(color);
	}
	
	private int xOffset, yOffset;
	
	
	private Mesh mesh;
	private Shader shader;
	private Transform transform;

	public TileLayer(BufferedImage img){
		this.img = img;
		tiles = new HashMap<Integer, Tile>();
	
		width = img.getWidth();
		height = img.getHeight();
		pixels = new int[width*height];
		img.getRGB(0, 0, width, height, pixels, 0, width);
	}
	
	public TileLayer(int width, int height){
		tiles = new HashMap<Integer, Tile>();
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		
		shader = new Shader();
		
		shader.addVertexShader("vertexShader");
		shader.addFragmentShader("fragmentShader");
		shader.createShaderProgram();
		
		shader.addUniform("transformation");
		shader.addUniform("sampler");
		shader.updateUniformi("sampler", 0);
		
		transform = new Transform();
		
		//generateTileLayer();
		
		
	}
	
	public void generateRandomLayer(){
		for(int i = 0; i < pixels.length; i++){
			int rn = Randomizer.getInt(0, tiles.size());
			int count = 0;
			for(Integer col : tiles.keySet()){
				if(rn == count){
					pixels[i] = col;
					break;
				}else{
					count++;
				}
			}	
		}
	}
	
	
	
	public void addTile(int color, Tile tile){
		tiles.put(color, tile);
	}
	
	public void removeTile(int x, int y){
		pixels[x + (y*width)] = 0;
	}
	
	public int getTileID(){
		int col = 0;
		for(Integer i : tiles.keySet()){
			col = i;
		}
		
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
	
	public boolean shouldRender(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return false;
		if(tiles.containsKey(pixels[x + (y*width)]))
			return true;
		return false;
	}
	
//	public void renderTileLayer(Screen screen, int xScroll, int yScroll){
//
//		int x0 = xScroll / Tile.TILE_WIDTH;
//		int x1 = (xScroll + Window.getWidth() + Tile.TILE_WIDTH) / Tile.TILE_WIDTH;
//		int y0 = yScroll / Tile.TILE_HEIGHT;
//		int y1 = (yScroll + Window.getHeight() + Tile.TILE_HEIGHT) / Tile.TILE_HEIGHT;
//		
//		glBindTexture(GL_TEXTURE_2D, tiles.get(getTileID()).getSprite().getTexID());
//		
//		for(int y = y0; y < y1; y++)
//			for(int x = x0; x < x1; x++)
//				if(shouldRender(x, y))
//					getTile(x, y).render(screen, x, y, xScroll, yScroll);	
//		
//		glBindTexture(GL_TEXTURE_2D, 0);
//	}
	
	public void renderTileLayer(Screen screen, int xScroll, int yScroll){
		
		transform.translate(xScroll, yScroll, 0);
		shader.bind();
		shader.updateUniform("transformation", transform.getOrthoProjection());
		tiles.get(getTileID()).getTexture().bind();

		mesh.render();
		
		tiles.get(getTileID()).getTexture().unbind();
		shader.unbind();
		
	}
	
	public void generateTileLayer(){
		List<Vertex> vertices = new ArrayList<Vertex>();
		List<Integer> indices = new ArrayList<Integer>();
		
		
		for(int x = 0; x < width; x++){
			for(int y = 0; y < height; y++){
				
				float xHigh = 1;
				float xLow = 0;
				float yHigh = 1;
				float yLow = 0;
				
				float tWidth = tiles.get(getTileID()).getTexture().getWidth();
				float tHeight = tiles.get(getTileID()).getTexture().getHeight();
				
				float xPos = x * tWidth;
				float yPos = y * tHeight;
				
				indices.add(vertices.size() + 0);
				indices.add(vertices.size() + 1);
				indices.add(vertices.size() + 2);
				
				indices.add(vertices.size() + 2);
				indices.add(vertices.size() + 3);
				indices.add(vertices.size() + 0);
				
				vertices.add(new Vertex(new Vector3f(xPos, yPos, 0), new Vector2f(xLow,yLow)));
				vertices.add(new Vertex(new Vector3f(xPos, yPos + tHeight, 0), new Vector2f(xHigh,yLow)));
				vertices.add(new Vertex(new Vector3f(xPos+tWidth, yPos + tHeight, 0), new Vector2f(xHigh,yHigh)));
				vertices.add(new Vertex(new Vector3f(xPos + tWidth, yPos, 0), new Vector2f(xLow,yHigh)));
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
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

}
