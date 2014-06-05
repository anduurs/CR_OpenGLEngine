package com.cr.game.util;

import com.cr.game.core.Window;

public class Camera {
	
	private static Vector2f pos;
	
	private static float camX, camY;
	
	private int width = Window.getWidth();
	private int height = Window.getHeight();
	
	public Camera(float camX, float camY){
		this.camX = camX;
		this.camY = camY;
		pos = new Vector2f(camX, camY);
	}
	
	
	
	public float centerX(){
		return camX + (width/2);
	}
	
	public float centerY(){
		return camY + (height/2);
	}
	
	public Vector2f getPos() {
		return pos;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void setPos(Vector2f pos) {
		this.pos = pos;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	public static float getCamX() {
		return camX;
	}

	public static float getCamY() {
		return camY;
	}

	public void setCamX(float camX) {
		this.camX = camX;
	}

	public void setCamY(float camY) {
		this.camY = camY;
	}
	
	

}
