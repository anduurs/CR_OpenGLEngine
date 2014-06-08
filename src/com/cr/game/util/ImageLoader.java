package com.cr.game.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	private static HashMap<String, BufferedImage> imageLib = 
			new HashMap<String, BufferedImage>();
	
	public ImageLoader(){
		//----------------------------Tiles-------------------------------------//
		imageLib.put("tileatlas", loadImage("/tiles/tileatlas.png"));
		imageLib.put("grass", loadImage("/tiles/grass.png"));
		imageLib.put("grass2", loadImage("/tiles/grass2.png"));
		imageLib.put("dirt", loadImage("/tiles/dirt.png"));
		imageLib.put("dirt2", loadImage("/tiles/dirt2.png"));
		imageLib.put("sand", loadImage("/tiles/sand.png"));
		imageLib.put("stone", loadImage("/tiles/stone.png"));
		imageLib.put("water", loadImage("/tiles/water.png"));
		//----------------------------------------------------------------------//
		
		//---------------------------TileLayers---------------------------------//
		imageLib.put("grasslayer", loadBitmap("/tilelayers/grasslayer.png"));
		imageLib.put("dirtlayer", loadBitmap("/tilelayers/dirtlayer.png"));
		imageLib.put("sandlayer", loadBitmap("/tilelayers/sandlayer.png"));
		imageLib.put("waterlayer", loadBitmap("/tilelayers/waterlayer.png"));
		imageLib.put("stonelayer", loadBitmap("/tilelayers/stonelayer.png"));
		//----------------------------------------------------------------------//

		//-----------------------------Hero-------------------------------------//
		imageLib.put("hero", loadImage("/hero/hero.png"));
		imageLib.put("heroatlas", loadImage("/hero/hero_atlas.png"));
		//----------------------------------------------------------------------//
	}
	
	private static synchronized BufferedImage loadImage(String path){
		BufferedImage img = null;
		try {
			img = ImageIO.read(ImageLoader.class.getResource("/textures/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	private static synchronized BufferedImage loadBitmap(String path){
		BufferedImage img = null;
		try {
			img = ImageIO.read(ImageLoader.class.getResource("/bitmaps/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public static BufferedImage getImage(String name){
		return imageLib.get(name);
	}
	
}