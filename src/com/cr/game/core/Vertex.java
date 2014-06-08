package com.cr.game.core;

public class Vertex {
	
	private Vector3f pos;
	private Vector2f texCoord;
	
	public final static int VERTEX_SIZE = 5;
	
	public Vertex(Vector3f pos){
		this.pos = pos;
	}
	
	public Vertex(Vector3f pos, Vector2f texCoord){
		this(pos);
		this.texCoord = texCoord;
	}

	public Vector3f getPos() {
		return pos;
	}

	public Vector2f getTexCoord() {
		return texCoord;
	}
}
