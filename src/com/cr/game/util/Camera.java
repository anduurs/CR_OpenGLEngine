package com.cr.game.util;

import com.cr.game.core.Vector3f;
import com.cr.game.entity.EntityManager;
import com.cr.game.graphics.Window;

public class Camera {
	
	private static Vector3f position;
	
	public Camera(){
		this(new Vector3f(0,0,0));
		position.x = EntityManager.getHero().getX() - (Window.getWidth()/2 - EntityManager.getHero().getWidth());
		position.y = EntityManager.getHero().getY() - (Window.getHeight()/2 - EntityManager.getHero().getHeight());
	}
	
	public Camera(Vector3f position) {
		Camera.position = position;
	}
	
	public void tick(float dt){
		position.x = EntityManager.getHero().getX() - (Window.getWidth()/2 - EntityManager.getHero().getWidth());
		position.y = EntityManager.getHero().getY() - (Window.getHeight()/2 - EntityManager.getHero().getHeight());
	}
	
	public static float getCamX(){
		return position.x;
	}
	
	public static float getCamY(){
		return position.y;
	}
	
	public static float getCamZ(){
		return position.z;
	}
	
	public static Vector3f getPos() {
		return position;
	}

	public void setPos(Vector3f position) {
		Camera.position = position;
	}


	
	
	
	

}
