package com.cr.game.graphics;

import com.cr.game.util.Transform;
import com.cr.game.util.Vector2f;
import com.cr.game.util.Vector3f;
import com.cr.game.util.Vertex;

public class Sprite {
	
	private int width, height;
	
	private Texture texture;
	private Mesh mesh;
	
	private Transform transform;
	private Shader shader;
	
	public Sprite(String name, Shader shader, Transform transform){
		this.shader = shader;
		this.transform = transform;
		
		texture = new Texture(name);
		
		width = texture.getWidth();
		height = texture.getHeight();
		
		Vertex[] vertices = {new Vertex(new Vector3f(0, 0, 0), new Vector2f(0,0)), 
							 new Vertex(new Vector3f(0, 0 + height, 0), new Vector2f(0,1)),
							 new Vertex(new Vector3f(0 + width , 0 + height, 0), new Vector2f(1,1)),
							 new Vertex(new Vector3f(0 + width, 0, 0), new Vector2f(1,0))};
		
		int[] indices = {0,1,2, 
						 2,3,0};
		
		mesh = new Mesh(vertices, indices);
	}
	
	public void bind(){
		shader.bind();
		shader.updateUniform("transformation", transform.getFullTransformation());
		texture.bind();
	}
	
	public void unbind(){
		texture.unbind();
		shader.unbind();
	}

	public Mesh getMesh() {
		return mesh;
	}

	public Transform getTransform() {
		return transform;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Texture getTexture() {
		return texture;
	}
}
