package com.cr.game.world.tile;

import com.cr.game.graphics.Screen;
import com.cr.game.graphics.Sprite;

public abstract class Tile {
	
	public static final int TILE_WIDTH = 46, TILE_HEIGHT = 22;
	public static  final int TILE_DRAW_OFFSET_X = -7;
	public static  final int TILE_DRAW_OFFSET_Y = -5;
	
	private Sprite sprite;
	
	protected boolean solid;
	
	public Tile(String imageString){
		sprite = new Sprite(imageString);
		solid = true;
	}
	
	public void render(Screen screen, int xPos, int yPos, int xOffset, int yOffset){
		xPos = (xPos * Tile.TILE_WIDTH) - Tile.TILE_DRAW_OFFSET_X - 10;
		yPos = (yPos * Tile.TILE_HEIGHT) - Tile.TILE_DRAW_OFFSET_Y - 10;
		xPos -= xOffset;
		yPos -= yOffset;
		
		screen.renderSprite(sprite, xPos, yPos);
	}

	public Sprite getSprite() {
		return sprite;
	}

	public boolean isSolid() {
		return solid;
	}

}
