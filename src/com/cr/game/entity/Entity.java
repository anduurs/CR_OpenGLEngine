package com.cr.game.entity;

import com.cr.game.util.Vector2f;

public abstract class Entity {
	
	protected Vector2f position;
	protected int width, height;
	protected boolean live = true;

	public Entity(Vector2f position) {
		this.position = position;
	}

	public Vector2f getPosition() {
		return position;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public boolean isLive() {
		return live;
	}

}
