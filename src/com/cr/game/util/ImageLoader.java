package com.cr.game.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	private static HashMap<String, BufferedImage> imageLib = 
			new HashMap<String, BufferedImage>();
	
	public ImageLoader(){
		// Tiles
		imageLib.put("grass", loadImage("/textures/grass.png"));
		imageLib.put("grass2", loadImage("/textures/grass2.png"));
		imageLib.put("dirt", loadImage("/textures/dirt.png"));

		//hero
		imageLib.put("hero", loadImage("/textures/hero.png"));
	}
	
	private static synchronized BufferedImage loadImage(String path){
		BufferedImage img = null;
		try {
			img = ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public static BufferedImage getImage(String name){
		return imageLib.get(name);
	}
	
}