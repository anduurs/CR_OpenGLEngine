package com.cr.game.world;

import com.cr.game.core.EntityManager;
import com.cr.game.graphics.Screen;
import com.cr.game.graphics.Shader;
import com.cr.game.util.Camera;
import com.cr.game.world.tile.Tile;

public class World {
	
	private int width, height;
	private int timer = 0;
	
	private TileMap map;
	private Camera camera;
	private EntityManager em;

	private static Shader shader;
	
	public World(){
		shader = new Shader("vertexshader", "fragmentshader");
		
		shader.addUniform("transformation");
		shader.addUniform("sampler");
		shader.setUniformi("sampler", 0);
		
		camera = new Camera(0, 0);
		
		map = new TileMap(100, 100);

		width = map.getWidth();
		height = map.getHeight();
		
		em = new EntityManager(this);
	}
	
//	public Tile getTile(int x, int y){
//		
//	}
	
	public void tick(float dt){
		if(timer < 7500) timer++;
		else timer = 0;
		
		if(camera.getPos().x < 0) camera.getPos().x = 0;
		if(camera.getPos().x > ((width*Tile.getWidth()) - camera.getWidth()))
			camera.getPos().x = (width*Tile.getWidth()) - camera.getWidth();
		
		if(camera.getPos().y < 0) camera.getPos().y = 0;
		if(camera.getPos().y > ((height*Tile.getHeight()) - camera.getHeight()))
			camera.getPos().y = (height*Tile.getHeight()) - camera.getHeight();
		
		camera.setCamX(EntityManager.getHero().getX() - (camera.getWidth()/2 - EntityManager.getHero().getWidth()));
		camera.setCamY(EntityManager.getHero().getY() - (camera.getHeight()/2 - EntityManager.getHero().getHeight()));
		
		em.tick(dt);
	}

	public void render(Screen screen) {
		float xScroll = Camera.getCamX();
		float yScroll = Camera.getCamY();
		
		map.renderMap(xScroll, yScroll);
		em.render(screen);
	}

	public static Shader getShader() {
		return shader;
	}

}
