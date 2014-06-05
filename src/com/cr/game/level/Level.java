package com.cr.game.level;



import com.cr.game.graphics.Bitmap;
import com.cr.game.graphics.Mesh;
import com.cr.game.graphics.Shader;
import com.cr.game.util.Transform;
import com.cr.game.util.Vector2f;
import com.cr.game.util.Vector3f;
import com.cr.game.util.Vertex;
import com.cr.game.world.tile.GrassTile;
import com.cr.game.world.tile.Tile;

public class Level {
	
	private int width, height;
	
	private Bitmap bitmap;
	private Shader shader;
	private Transform transform;
	private Mesh mesh;
	
	Tile tile;
	
	public Level(String path){
		bitmap = new Bitmap(path);
		width = bitmap.getWidth();
		height = bitmap.getHeight();
		
		shader = new Shader();
		
		shader.addVertexShader("vertexShader");
		shader.addFragmentShader("fragmentShader");
		shader.createShaderProgram();
		
		shader.addUniform("transformation");
		shader.addUniform("sampler");
		shader.updateUniformi("sampler", 0);
		
		transform = new Transform();
		
		tile = new GrassTile();
		
		generateLevel();
	}
	
	public void generateLevel(){
		Vertex[] vertices = {new Vertex(new Vector3f(0,0,0), new Vector2f(0,0)),
							 new Vertex(new Vector3f(0,32,0), new Vector2f(0,1)),
							 new Vertex(new Vector3f(0+32,32,0), new Vector2f(1,1)),
							 new Vertex(new Vector3f(0+32,0,0), new Vector2f(1,0))};
		
		int[] indices = {0,1,2, 
				         2,3,0};
		
		mesh = new Mesh(vertices, indices);
	}
	
	public void tick(float dt){
		
	}
	
	public void render(float xScroll, float yScroll){
		transform.translate(xScroll, yScroll, 0);
		shader.bind();
		shader.updateUniform("transformation", transform.getOrthoProjection());
		tile.getTexture().bind();

		mesh.render();
		
		tile.getTexture().unbind();
		shader.unbind();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
