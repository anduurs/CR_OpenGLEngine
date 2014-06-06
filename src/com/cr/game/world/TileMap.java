package com.cr.game.world;

import com.cr.game.util.Transform;
import com.cr.game.world.biome.Grasslands;

public class TileMap {
	
	private int width;
	private int height;
	
	private TileLayer bottomLayer, middleLayer, topLayer;
	
	private static Transform transform;
	
	public TileMap(int width, int height){
		this.width = width;
		this.height = height;
		
		transform = new Transform();
		
		Grasslands g = new Grasslands(width, height, transform);
		
		bottomLayer = g.getBottomLayer();
		middleLayer = g.getMiddleLayer();
		topLayer = g.getTopLayer();
		
		bottomLayer.generateTileLayer();
		middleLayer.generateTileLayer();
		topLayer.generateTileLayer();
	}
	
	public TileMap(){
		transform = new Transform();

	}
	
	public void renderMap(float xScroll, float yScroll){
		bottomLayer.renderTileLayer(xScroll, yScroll, 0);
		middleLayer.renderTileLayer(xScroll, yScroll, 0);
		topLayer.renderTileLayer(xScroll, yScroll, 0);
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	

}
