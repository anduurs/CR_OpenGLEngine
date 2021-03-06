package com.cr.game.graphics;

import com.cr.game.core.Transform;
import com.cr.game.core.Vector2f;
import com.cr.game.core.Vector3f;
import com.cr.game.core.Vertex;
import com.cr.game.graphics.shader.Shader;

public class Sprite {
	
	private int width, height, atlasWidth, atlasHeight;
	
	private Texture texture, textureAtlas;
	private Mesh mesh;
	
	private Transform transform;
	private Shader shader;
	
	private float xLow = 0;
	private float xHigh = 0;
	private float yLow = 0;
	private float yHigh = 0;
	
	private float rows, cols;
	
	private boolean tAtlas = false;
	
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
	
	/**
	 * Creates a sprite from a sprite atlas. A sprite atlas simply is a collection of sprites tucked together in one texture.
	 * @param name the name of the sprite atlas
	 * @param rows the number of rows in the sprite atlas. (eg how many sprites there are in every column)
	 * @param cols the number of columns in the sprite atlas. (eg how many sprites there are in every row)
	 * @param startRow the row that the sprite to be generated is on. (counts from 0 to rows)
	 * @param startCol the column that the sprite to be generated is on. (counts from 0 to cols)
	 * @param shader the shader that will be used on this sprite
	 * @param transform the transform that will be used on this sprite
	 */
	public Sprite(String name, float rows, float cols, float startRow, float startCol, Shader shader, Transform transform){
		this.rows = rows;
		this.cols = cols;
		this.shader = shader;
		this.transform = transform;
		
		textureAtlas = new Texture(name);
		
		atlasWidth = textureAtlas.getWidth();
		atlasHeight = textureAtlas.getHeight();
		width = (int) (atlasWidth / cols);
		height = (int) (atlasHeight / rows) ;
		
		Vertex[] vertices = {new Vertex(new Vector3f(0, 0, 0)),
							 new Vertex(new Vector3f(0, height, 0)),
							 new Vertex(new Vector3f(width, height, 0)),
							 new Vertex(new Vector3f(width, 0, 0))};
		
		calcTexCoords(startRow,startCol);
		
		Vector2f[] texCoords = {new Vector2f(xLow, yLow),
							    new Vector2f(xLow, yHigh),
							    new Vector2f(xHigh, yHigh),
							    new Vector2f(xHigh, yLow)};
		
		int[] indices = {0,1,2, 
						 2,3,0};
		
		mesh = new Mesh(vertices, texCoords, indices);
		tAtlas = true;
	}
	
	public void calcTexCoords(float row, float col){
		xLow = col / cols;
		xHigh = xLow + (1 / cols);
		yLow = row / rows;
		yHigh = yLow + (1 / rows);
	}
	
	public void updateTexCoords(float row, float col){
		xLow = col / cols;
		xHigh = xLow + (1 / cols);
		yLow = row / rows;
		yHigh = yLow + (1 / rows);
		
		Vector2f[] texCoords = {new Vector2f(xLow, yLow),
			    				new Vector2f(xLow, yHigh),
			    				new Vector2f(xHigh, yHigh),
			    				new Vector2f(xHigh, yLow)};
		
		mesh.updateTexCoordData(texCoords);
	}
	
	public void bind(){
		shader.bind();
		shader.setUniform("transformation", transform.getOrthoTransformation());
		if(!tAtlas)
			texture.bind();
		else textureAtlas.bind();
	}
	
	public void unbind(){
		if(!tAtlas)
			texture.unbind();
		else textureAtlas.unbind();
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

	public float getRows() {
		return rows;
	}

	public float getCols() {
		return cols;
	}
}
