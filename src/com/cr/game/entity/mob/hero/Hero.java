package com.cr.game.entity.mob.hero;

import com.cr.game.entity.Collideable;
import com.cr.game.entity.mob.Mob;
import com.cr.game.graphics.Screen;
import com.cr.game.graphics.Sprite;
import com.cr.game.input.Input;
import com.cr.game.util.Camera;
import com.cr.game.util.Vector2f;
import com.cr.game.world.World;

public class Hero extends Mob implements Collideable{
	
	private Vector2f targetVel;
	private float accSpeed = 3.5f;
	
	Direction d = currentDir; 
	
	public Hero(Vector2f position, World world) {
		super(position, world);
		
		sprite = new Sprite("hero", World.getShader(), transform);
		
		width = sprite.getWidth();
		height = sprite.getHeight();
		
		velocity = new Vector2f(0f, 0f);
		targetVel = new Vector2f(0, 0);
		
		currentDir = Direction.SOUTH;
	}
	
	private void processInput(){
		if(Input.getKey(Input.KEY_D)){
			targetVel.x = 10f;
			currentDir = Direction.EAST;
		}
		if(Input.getKey(Input.KEY_A)){
			targetVel.x = -10f;
			currentDir = Direction.WEST;
		}
		if(Input.getKey(Input.KEY_W)){
			targetVel.y = -10f;
			currentDir = Direction.NORTH;
		}
		if(Input.getKey(Input.KEY_S)){
			targetVel.y = 10f;
			currentDir = Direction.SOUTH;
		}
		
		if(!Input.getKey(Input.KEY_W) && !Input.getKey(Input.KEY_S))
			targetVel.y = 0;
		if(!Input.getKey(Input.KEY_D) && !Input.getKey(Input.KEY_A))
			targetVel.x = 0;
	}
	
	boolean dirChanged = false;
	
	@Override
	public void tick(float dt){
		
		processInput();
		
	
		
		if(collisionWithTile(targetVel.x*dt,0)){
			targetVel.x = 0;
			
		}
		
		if(collisionWithTile(0, targetVel.y*dt)){
			
			targetVel.y = 0;
		}
		
	

		velocity.x = approachTarget(targetVel.x, velocity.x, dt*accSpeed);
		velocity.y = approachTarget(targetVel.y, velocity.y, dt*accSpeed);
		//transform.rotate(0, 0, velocity.x);
		transform.scale(1f, 1f, 0);
		move(dt);
	}
	
	

	@Override
	public void collisionWith(Collideable obj) {
		
		
	}

}
