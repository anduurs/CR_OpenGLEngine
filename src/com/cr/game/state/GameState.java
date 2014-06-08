package com.cr.game.state;

import com.cr.game.graphics.Screen;

public abstract class GameState {
	
	protected GameStateManager gsm;
	protected boolean blockTicking = true;
	protected boolean blockRendering = true;
	
	public GameState(GameStateManager gsm){
		this.gsm = gsm;
	}
	
	public abstract void init();
	public abstract void tick(float dt);
	public abstract void render(Screen screen);
	
	public boolean isTickingBlocked() {
		return blockTicking;
	}
	public boolean isRenderingBlocked() {
		return blockRendering;
	}
	public void setTickingBlocked(boolean blockTicking) {
		this.blockTicking = blockTicking;
	}
	public void setRenderingBlocked(boolean blockRendering) {
		this.blockRendering = blockRendering;
	}

}
