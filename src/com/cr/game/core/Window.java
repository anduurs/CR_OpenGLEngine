package com.cr.game.core;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	
	private static boolean fullscreenMode;
	
	public static void createWindow(int width, int height, boolean fullscreenMode){
		Window.fullscreenMode = fullscreenMode;
		if(fullscreenMode){
			try {
				Display.setFullscreen(true);
				Display.setVSyncEnabled(true);
				Display.create();
				Keyboard.create();
				Mouse.create();
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}else{
			try {
				Display.setDisplayMode(new DisplayMode(width, height));
				//Display.setVSyncEnabled(true);
				Display.create();
				Keyboard.create();
				Mouse.create();
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static void setTitle(String title){
		Display.setTitle(title);
	}
	
	public static void update(){
		Display.update();
	}
	
	public static boolean isCloseRequested(){
		return Display.isCloseRequested();
	}
	
	public static void dispose(){
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
	
	public static int getWidth(){
		return Display.getDisplayMode().getWidth();
	}
	
	public static int getHeight(){
		return Display.getDisplayMode().getHeight();
	}
	
	public static String getTitle(){
		return Display.getTitle();
	}

	public static boolean isFullscreenMode() {
		return fullscreenMode;
	}
}