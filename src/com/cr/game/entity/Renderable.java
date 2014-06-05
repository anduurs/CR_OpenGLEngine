package com.cr.game.entity;

import com.cr.game.graphics.Screen;
import com.cr.game.graphics.Sprite;

public interface Renderable {
	
	public void render(Screen screen);
	public Sprite getSprite();
}
