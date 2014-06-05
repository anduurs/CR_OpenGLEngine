package com.cr.game.world.tile;

import com.cr.game.graphics.Screen;
import com.cr.game.graphics.Sprite;
import com.cr.game.graphics.Texture;

public abstract class Tile {
	
	public static final int TILE_WIDTH = 46, TILE_HEIGHT = 22;
	public static  final int TILE_DRAW_OFFSET_X = -7;
	public static  final int TILE_DRAW_OFFSET_Y = -5;
	
	private int width, height;
	
	private Texture texture;
	
	protected boolean solid;
	
	public Tile(String imageString){
	
		texture = new Texture(imageString);
		width = texture.getWidth();
		height = texture.getHeight();
		solid = true;
	}
	
	public void render(Screen screen, int xPos, int yPos, int xOffset, int yOffset){
		xPos = (xPos * Tile.TILE_WIDTH) - Tile.TILE_DRAW_OFFSET_X - 10;
		yPos = (yPos * Tile.TILE_HEIGHT) - Tile.TILE_DRAW_OFFSET_Y - 10;
		xPos -= xOffset;
		yPos -= yOffset;
		
		//screen.renderSprite(sprite, xPos, yPos);
	}

	public Texture getTexture() {
		return texture;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isSolid() {
		return solid;
	}

}
