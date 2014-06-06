package com.cr.game.core;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;


public abstract class CoreEngine {
	
	public static int TARGET_FPS = 60;
	private volatile boolean running = false;
	
	public abstract void getInput();
	public abstract void tick(float dt);
	public abstract void render();
	public abstract void cleanUp();
	
	protected synchronized void start(){
		running = true;
		gameLoop();
	}
	
	protected synchronized void stop(){
		running = false;
	}
	
	private void gameLoop(){
		double currentTime = 0;
		double previousTime = System.nanoTime();
		double passedTime = 0;
		double accumulator = 0;
		double frameCounter = 0;
		final double OPTIMAL_TICK_TIME = 1.0 / TARGET_FPS;
		
		float dt = (float) (OPTIMAL_TICK_TIME * 10);
		int fps = 0;
		boolean shouldRender;
		
		while(running){
			shouldRender = false;
			currentTime = System.nanoTime();
			passedTime = (currentTime - previousTime) / 1000000000.0;
			accumulator += passedTime;
			frameCounter += passedTime;
			previousTime = currentTime;
		
			while(accumulator >= OPTIMAL_TICK_TIME){
				shouldRender = true;
				getInput();
				tick(dt);
				accumulator -= OPTIMAL_TICK_TIME;
			}
			
			render();
			fps++;
			Window.update();
	
			if(frameCounter >= 1){
				Window.setTitle("OpenGL GameEngine" + " || " + fps + " fps");
				fps = 0;
				frameCounter = 0;
			}
			
			if(Window.isCloseRequested()) stop();
		}
		cleanUp();
		Window.dispose();
	}
	

}
