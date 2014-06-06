package com.cr.game.world;

import com.cr.game.util.ColorRGBA;
import com.cr.game.util.Transform;
import com.cr.game.world.tile.DirtTile;
import com.cr.game.world.tile.GrassTile;

public class TileMap {
	
	private int width;
	private int height;
	
	private TileLayer dirtLayer, grassLayer;
	
	private static Transform transform;
	
	public TileMap(int width, int height){
		this.width = width;
		this.height = height;
		
		transform = new Transform();
		
		grassLayer = new TileLayer(width, height, transform);
		dirtLayer = new TileLayer(width, height, transform);
		
		grassLayer.addTile(ColorRGBA.GREEN, new GrassTile());
		dirtLayer.addTile(ColorRGBA.BROWN, new DirtTile());
		
		for(int i = 0; i < grassLayer.pixels.length; i++)
			grassLayer.pixels[i] = grassLayer.getTileID();
		
		for(int i = 0; i < dirtLayer.pixels.length; i++)
			dirtLayer.pixels[i] = dirtLayer.getTileID();
		
		grassLayer.removeTile(10, 10);
		
		grassLayer.generateTileLayer();
		dirtLayer.generateTileLayer();
	}
	
	public void renderMap(float xScroll, float yScroll){
		dirtLayer.renderTileLayer(xScroll, yScroll, 0f);
		grassLayer.renderTileLayer(xScroll, yScroll, 1f);		
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	

}
