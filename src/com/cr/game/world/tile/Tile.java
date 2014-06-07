package com.cr.game.world.tile;

import com.cr.game.graphics.Texture;

public abstract class Tile {
	
	private static Texture texture = new Texture("tileatlas");
	
	private static int width, height;
	protected boolean walkable;
	
	public Tile(){
		width = texture.getWidth();
		height = texture.getHeight();
		walkable = true;
	}
	
	public Tile(String imageString){
		texture = new Texture(imageString);
		width = texture.getWidth();
		height = texture.getHeight();
		walkable = true;
	}

	public static Texture getTexture() {
		return texture;
	}
	
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public boolean isWalkable() {
		return walkable;
	}

}
