package com.cr.game.graphics;

import java.util.ArrayList;
import java.util.List;

import com.cr.game.core.Transform;
import com.cr.game.core.Vector2f;
import com.cr.game.core.Vector3f;
import com.cr.game.core.Vertex;
import com.cr.game.graphics.shader.Shader;

public class SpriteAtlas {
	
	private int width, height, spriteWidth, spriteHeight;
	
	private Texture textureAtlas;
	private Mesh mesh;
	private Transform transform;
	private Shader shader;
	
	private float xLow = 0;
	private float xHigh = 0;
	private float yLow = 0;
	private float yHigh = 0;
	
	public SpriteAtlas(String name, int rows, int cols, Shader shader, Transform transform){
		this.shader = shader;
		this.transform = transform;
		textureAtlas = new Texture(name);
		
		width = textureAtlas.getWidth();
		height = textureAtlas.getHeight();
		spriteWidth = width / cols;
		spriteHeight = height / rows;
		
		List<Vertex> vertices = new ArrayList<Vertex>();
		List<Integer> indices = new ArrayList<Integer>();
		List<Vector2f> texCoords = new ArrayList<Vector2f>();
		
		for(int y = 0; y < rows; y++){
			for(int x = 0; x < cols; x++){
				
				indices.add(vertices.size() + 0);
				indices.add(vertices.size() + 1);
				indices.add(vertices.size() + 2);
				
				indices.add(vertices.size() + 2);
				indices.add(vertices.size() + 3);
				indices.add(vertices.size() + 0);
				
				xLow = x / cols;
				xHigh = xLow + (1 / cols);
				yLow = y / rows;
				yHigh = yLow + (1 / rows);
				
				texCoords.add(new Vector2f(xLow, yLow));
				texCoords.add(new Vector2f(xLow, yHigh));
				texCoords.add(new Vector2f(xHigh, yHigh));
				texCoords.add(new Vector2f(xHigh, yLow));
				
				vertices.add(new Vertex(new Vector3f(x*spriteWidth, y*spriteHeight, 0)));
				vertices.add(new Vertex(new Vector3f(x*spriteWidth, (y*spriteHeight) + spriteHeight, 0)));
				vertices.add(new Vertex(new Vector3f((x*spriteWidth) + spriteWidth, (y*spriteHeight) + spriteHeight, 0)));
				vertices.add(new Vertex(new Vector3f((x*spriteWidth) + spriteWidth, y*spriteHeight, 0)));
			}
		}
		
		Vertex[] vertexArray = new Vertex[vertices.size()];
		Integer[] indexArray = new Integer[indices.size()];
		Vector2f[] texCoordArray = new Vector2f[texCoords.size()];
		
		vertices.toArray(vertexArray);
		indices.toArray(indexArray);
		texCoords.toArray(texCoordArray);
		
		int[] iArray = new int[indexArray.length];
		
		for(int i = 0; i < indexArray.length; i++)
			iArray[i] = indexArray[i];
		
		mesh = new Mesh(vertexArray, texCoordArray, iArray);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getSpriteWidth() {
		return spriteWidth;
	}

	public int getSpriteHeight() {
		return spriteHeight;
	}

	public Texture getTextureAtlas() {
		return textureAtlas;
	}

	public Mesh getMesh() {
		return mesh;
	}

	public Transform getTransform() {
		return transform;
	}

	public Shader getShader() {
		return shader;
	}

}
