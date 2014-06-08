package com.cr.game.graphics;

public class Animation {
	
	private int animSpeed = 0;
	private int timer = 0;
	private int currentFrame = 0;
	
	private Sprite sprite;
	
	public Animation(Sprite sprite, int animSpeed){
		this.sprite = sprite;
		this.animSpeed = animSpeed;
	}
	
	public void setFrame(int row){
		sprite.updateTexCoords(row, 0);
	}
	
	public void animate(int row){
		if(timer < 7500) timer++;
		else timer = 0;
		
		if(timer % animSpeed == 0 && currentFrame < sprite.getCols()){
			sprite.updateTexCoords(row, currentFrame);
			currentFrame++;
		}
		
		if(currentFrame == sprite.getCols()) currentFrame = 0;
	}

	public int getAnimSpeed() {
		return animSpeed;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setAnimSpeed(int animSpeed) {
		this.animSpeed = animSpeed;
	}

}
