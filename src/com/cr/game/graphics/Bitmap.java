package com.cr.game.graphics;

import java.awt.image.BufferedImage;

import com.cr.game.util.ImageLoader;

/**
 * Class for representing a bitmap with a pixel array.
 * @author Anders
 */
public class Bitmap {
	
	//the width and height of the bitmap
	private int width, height;
	//the pixel array containing all the pixels of the bitmap
	private int[] pixels;
	
	/**
	 * Creates a new bitmap from the given filepath
	 * @param fileName the file path for the bitmap
	 */
	public Bitmap(String name){
		BufferedImage img = ImageLoader.getImage(name);
		width = img.getWidth();
		height = img.getHeight();
		pixels = new int[width*height];
		img.getRGB(0, 0, width, height, pixels, 0, width);
	}
	
	/**
	 * Creates an empty bitmap of size width*height
	 * @param width the width of the bitmap
	 * @param height the height of the bitmap
	 */
	public Bitmap(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
	}
	
	/**
	 * Fills the pixel array contained in this bitmap with the given color
	 * @param color the pixel color to fill the pixel array with
	 */
	public void fillBitmap(int color){
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = color;
	}
	
	/**
	 * Sets the pixel located at (x,y) to the given color
	 * @param x the x position of the pixel
	 * @param y the y position of the pixel
	 * @param color the color which the pixel will be set to
	 */
	public void setPixel(int x, int y, int color){
		pixels[x + (y*width)] = color;
	}
	
	/**
	 * Retrieves the pixel located at position (x,y)
	 * @param x the x position of the pixel
	 * @param y the y position of the pixel
	 * @return the pixel located at (x,y)
	 */
	public int getPixel(int x, int y){
		return pixels[x + (y*width)];
	}
	
	/**
	 * Returns the size of the bitmap
	 * @return the size of the bitmap
	 */
	public int getSize(){
		return pixels.length;
	}

	/**
	 * Returns the width of the bitmap
	 * @return the width of the bitmap
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the bitmap
	 * @return the height of the bitmap
	 */
	public int getHeight() {
		return height;
	}

}
