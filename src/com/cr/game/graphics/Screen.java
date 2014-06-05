package com.cr.game.graphics;

import static org.lwjgl.opengl.GL11.*;
import com.cr.game.core.GameStateManager;
import com.cr.game.util.Transform;

public class Screen {
	
	private Shader shader;
	private Transform transform;

	public Screen(){
//		shader = new Shader();
//		
//		shader.addVertexShader("vertexShader");
//		shader.addFragmentShader("fragmentShader");
//		shader.createShaderProgram();
//		
//		shader.addUniform("transformation");
//		shader.addUniform("sampler");
//		shader.updateUniformi("sampler", 0);
//		
//		transform = new Transform();
		
		initGL();
	}
	
	private void initGL(){
		glClearColor(0f,0f,0f,0f);
	
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_ALPHA);
		glEnable(GL_BLEND);
		glCullFace(GL_BACK);
		glEnable(GL_CULL_FACE);
		//glEnable(GL_DEPTH_TEST);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void render(GameStateManager gsm){
		clearScreen();
		gsm.render(this);
	}
	
	private void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
//	public void renderSprite(Sprite sprite, float x, float y){
//		transform.translate(x, y, 0);
//	
//		shader.bind();
//			shader.updateUniform("transformation", transform.getOrthoProjection());
//			sprite.bind();
//				glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE, 0);
//			sprite.unbind();
//		shader.unbind();
//	}
	
	public void cleanUp(){
		//shader.deleteShader();
	}

}
