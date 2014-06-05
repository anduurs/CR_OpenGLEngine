package com.cr.game.world;

import java.awt.image.BufferedImage;
import java.util.List;

import com.cr.game.core.Window;
import com.cr.game.graphics.Screen;
import com.cr.game.util.ColorRGBA;
import com.cr.game.util.LinkedStack;
import com.cr.game.world.tile.DirtTile;
import com.cr.game.world.tile.GrassTile;
import com.cr.game.world.tile.Tile;

public class TileMap {
	
	private int width;
	private int height;
	
	private BufferedImage img;
	
	TileLayer stoneLayer, waterLayer, dirtLayer, sandLayer, grassLayer;
	
	private List<TileLayer> layerList;
	private LinkedStack<TileLayer> tileStack;
	
//	public TileMap(int width, int height){
//		this.width = width;
//		this.height = height;
//		
//		layerList = new ArrayList<TileLayer>();
//		
//		layer1 = new TileLayer(width, height);
//		layer2 = new TileLayer(width, height);
//		
//		layer1.addTile(ColorRGBA.GREEN, new GrassTile());
//		layer2.addTile(ColorRGBA.GREEN, new PoisonTile());
//		
//
//		for(int i = 0; i < layer1.pixels.length; i++){
//			layer1.pixels[i] = ColorRGBA.GREEN;
//		}
//		
//		for(int i = 0; i < layer1.pixels.length; i++){
//			layer2.pixels[i] = 0;
//		}
//	
//	}
	
	public TileMap(){
//		stoneLayer = new TileLayer(ImageLoader.getImage("stonelayer"));
//		waterLayer = new TileLayer(ImageLoader.getImage("waterlayer"));
//		sandLayer = new TileLayer(ImageLoader.getImage("sandlayer"));
		grassLayer = new TileLayer(500, 500);
//		dirtLayer = new TileLayer(50, 50);
		
		
		
		width = grassLayer.getWidth();
		height = grassLayer.getHeight();
	
//		
//		stoneLayer.addTile(ColorRGBA.GRAY, new StoneTile());
//		waterLayer.addTile(ColorRGBA.BLUE, new WaterTile());
//		sandLayer.addTile(ColorRGBA.ORANGE, new SandTile());
		grassLayer.addTile(ColorRGBA.GREEN, new GrassTile());
		grassLayer.generateTileLayer();
		//dirtLayer.addTile(ColorRGBA.BROWN, new DirtTile());
		
		for(int i = 0; i < grassLayer.pixels.length; i++){
			grassLayer.pixels[i] = grassLayer.getTileID();
		}
		
//		for(int i = 0; i < dirtLayer.pixels.length; i++){
//			dirtLayer.pixels[i] = 0;
//		}
	
//		grassLayer.removeTile(10, 10);
//		grassLayer.removeTile(11, 10);
//		grassLayer.removeTile(11, 11);
//		grassLayer.removeTile(10, 11);
//		dirtLayer.removeTile(50, 50);
	}
	
	public void renderMap(Screen screen, int xScroll, int yScroll){
		
//		int x0 = xScroll / Tile.TILE_WIDTH;
//		int x1 = (xScroll + Window.getWidth() + Tile.TILE_WIDTH) / Tile.TILE_WIDTH;
//		int y0 = yScroll / Tile.TILE_HEIGHT;
//		int y1 = (yScroll + Window.getHeight() + Tile.TILE_HEIGHT) / Tile.TILE_HEIGHT;
//		
//		for(int y = y0; y < y1; y++)
//			for(int x = x0; x < x1; x++){
//				if(grassLayer.validID(x, y))
//					if(grassLayer.getTileID(x, y) == 0) 
//						dirtLayer.pixels[x + y*width] = dirtLayer.getTileID();
//					
//			}
		
		
		//dirtLayer.renderTileLayer(screen, xScroll, yScroll);
		grassLayer.renderTileLayer(screen, xScroll, yScroll);		
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	

}
