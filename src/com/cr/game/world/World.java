package com.cr.game.world;

import com.cr.game.core.EntityManager;
import com.cr.game.graphics.Screen;
import com.cr.game.util.Camera;
import com.cr.game.world.tile.Tile;

public class World {
	
	private TileMap map;
	
	private Camera camera;
	private int xScroll, yScroll;
	
	private int width, height;
	
	private EntityManager em;

	public World(){
		
		camera = new Camera(0, 0);
		map = new TileMap();

		width = map.getWidth();
		height = map.getHeight();
		
		em = new EntityManager();
		
	}
	
	int timer = 0;
	
	public void tick(float dt){
		if(timer < 7500) timer++;
		else timer = 0;
		
		if(camera.getPos().x < 0) camera.getPos().x = 0;
		if(camera.getPos().x > ((width*Tile.TILE_WIDTH) - camera.getWidth()))
			camera.getPos().x = (width*Tile.TILE_WIDTH) - camera.getWidth();
		
		if(camera.getPos().y < 0) camera.getPos().y = 0;
		if(camera.getPos().y > ((height*Tile.TILE_HEIGHT) - camera.getHeight()))
			camera.getPos().y = (height*Tile.TILE_HEIGHT) - camera.getHeight();
		
		camera.setCamX(EntityManager.getHero().getX() - (camera.getWidth()/2 - EntityManager.getHero().getWidth()));
		camera.setCamY(EntityManager.getHero().getY() - (camera.getHeight()/2 - EntityManager.getHero().getHeight()));
		
		em.tick(dt);
	}

	public void render(Screen screen) {
		xScroll = (int) (Camera.getCamX());
		yScroll = (int) (Camera.getCamY());
		
		map.renderMap(screen, xScroll, yScroll);
		
//		em.render(screen);
	}

}
