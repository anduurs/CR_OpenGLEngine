package com.cr.game.graphics;

import static org.lwjgl.opengl.GL11.*;

import com.cr.game.core.GameStateManager;

public class Screen {
	
	public Screen(){
		initGL();
	}
	
	private void initGL(){
		glClearColor(0f,0f,0f,0f);
	
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_ALPHA);
		glEnable(GL_BLEND);
		glEnable(GL_CULL_FACE);
		//glEnable(GL_DEPTH_TEST);
		
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glCullFace(GL_BACK);
	}
	
	public void render(GameStateManager gsm){
		clearScreen();
		gsm.render(this);
	}
	
	private void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void renderSprite(Sprite sprite, float x, float y){
		sprite.getTransform().translate(x, y, 0);
		sprite.bind();
		sprite.getMesh().render();
		sprite.unbind();
	}

}
