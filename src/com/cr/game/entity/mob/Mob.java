package com.cr.game.entity.mob;

import com.cr.game.core.Transform;
import com.cr.game.core.Vector2f;
import com.cr.game.entity.Entity;
import com.cr.game.entity.Renderable;
import com.cr.game.entity.Tickable;
import com.cr.game.graphics.Screen;
import com.cr.game.graphics.Sprite;
import com.cr.game.world.World;
import com.cr.game.world.tile.Tile;

public abstract class Mob extends Entity implements Tickable, Renderable{
	
	protected World world;

	protected float speedX, speedY;
	protected Vector2f velocity;
	protected Vector2f distance;
	protected Sprite sprite;
	
	protected Transform transform;
	
	protected enum Direction{
		NORTH, SOUTH, EAST, WEST;
	}
	
	protected static Direction currentDir;
	
	protected boolean moving = false;
	
	public Mob(Vector2f position, World world) {
		super(position);
		this.world = world;
		transform = new Transform();
		distance = new Vector2f(0,0);
	}

	protected float approachTarget(float target, float current, float dt){
		float diff = target - current;
		if(diff > dt)
			return current + dt;
		if(diff < -dt)
			return current - dt;
		return target;
	}
	
	protected boolean collisionWithTile(float x, float y){
		int nextX = (int)position.x + (int)x;
		int nextY = (int)position.y + (int)y;
		int w = Tile.getTileWidth()-10;
		int h = Tile.getTileHeight()-3;
		int xPos =  nextX / (w);
		int yPos =  nextY / (h);
		if(world.tileExists(xPos, yPos)){
			if(!world.getTile(xPos, yPos).isWalkable()){
				return true;
			}
		}
		return false;
	}

	protected void move(float dt){
		distance = velocity.mul(dt);
		position = position.add(distance);
	}
	
	@Override
	public void tick(float dt){
		
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, position.x , position.y);
	}

	public Vector2f getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2f velocity) {
		this.velocity = velocity;
	}
	
	@Override
	public Sprite getSprite(){
		return sprite;
	}

}
