package com.cr.game.world.tile;

import com.cr.game.graphics.Texture;

public abstract class Tile {
	
	private Texture texture;
	
	private static int width, height;
	protected boolean solid;
	
	public Tile(String imageString){
		texture = new Texture(imageString);
		width = texture.getWidth();
		height = texture.getHeight();
		solid = true;
	}

	public Texture getTexture() {
		return texture;
	}
	
	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}

	public boolean isSolid() {
		return solid;
	}

}
