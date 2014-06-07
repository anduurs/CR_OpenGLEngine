package com.cr.game.entity.mob;

import com.cr.game.entity.Entity;
import com.cr.game.entity.Renderable;
import com.cr.game.entity.Tickable;
import com.cr.game.graphics.Screen;
import com.cr.game.graphics.Sprite;
import com.cr.game.util.Camera;
import com.cr.game.util.Transform;
import com.cr.game.util.Vector2f;
import com.cr.game.world.World;

public abstract class Mob extends Entity implements Tickable, Renderable{
	
	protected World world;

	protected float speedX, speedY;
	protected Vector2f velocity;
	protected Sprite sprite;
	
	protected Transform transform;
	
	protected enum Direction{
		NORTH, SOUTH, EAST, WEST;
	}
	
	protected static Direction currentDir;
	
	public Mob(Vector2f position, World world) {
		super(position);
		this.world = world;
		transform = new Transform();
	}

	protected float approach(float target, float current, float dt){
		float diff = target - current;
		if(diff > dt)
			return current + dt;
		if(diff < -dt)
			return current - dt;
		return target;
	}

	protected void move(float dt){
		position = position.add(velocity.mul(dt));
	}
	
	@Override
	public void tick(float dt){
		
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderSprite(sprite, position.x - Camera.getCamX(), position.y - Camera.getCamY());
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
