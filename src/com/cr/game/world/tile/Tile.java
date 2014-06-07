package com.cr.game.world.tile;

import com.cr.game.graphics.Texture;

public abstract class Tile {
	
	private static Texture texture = new Texture("tileatlas");
	
	public final static float TILE_ATLAS_ROWS = 4;
	public final static float TILE_ATLAS_COLS = 4;
	
	private static int width, height;
	protected float row, col;
	protected boolean walkable;
	
	public Tile(){
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

	public float getRow() {
		return row;
	}

	public float getCol() {
		return col;
	}

}
