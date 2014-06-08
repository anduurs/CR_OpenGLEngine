package com.cr.game.entity.mob.hero;

import com.cr.game.core.Vector2f;
import com.cr.game.entity.Collideable;
import com.cr.game.entity.mob.Mob;
import com.cr.game.graphics.Animation;
import com.cr.game.graphics.Sprite;
import com.cr.game.input.Input;
import com.cr.game.world.World;

public class Hero extends Mob implements Collideable{
	
	private Vector2f targetVel;
	private float accSpeed = 3.5f;
	private float speed = 15f;
	
	private Animation anim;

	public Hero(Vector2f position, World world) {
		super(position, world);
		
		sprite = new Sprite("heroatlas", 4, 4, 0, 0, World.getShader(), transform);
		
		anim = new Animation(sprite, 8);
		
		width = sprite.getWidth();
		height = sprite.getHeight();
		
		velocity = new Vector2f(0f, 0f);
		targetVel = new Vector2f(0, 0);
		
		currentDir = Direction.SOUTH;
		transform.scale(1.2f, 1.2f, 0);
	}
	
	private float diagonalSpeed(float speed){
		float res = (float) Math.sqrt((speed*speed) + (speed*speed));
		return res/2.0f;
	}
	
	private void processInput(){
		if(Input.getKey(Input.KEY_W)){
			targetVel.y = -speed;
			currentDir = Direction.NORTH;
			moving = true;
		}
		
		if(Input.getKey(Input.KEY_S)){
			targetVel.y = speed;
			currentDir = Direction.SOUTH;
			moving = true;
		}
		
		if(Input.getKey(Input.KEY_D)){
			targetVel.x = speed;
			currentDir = Direction.EAST;
			moving = true;
		}
		
		if(Input.getKey(Input.KEY_A)){
			targetVel.x = -speed;
			currentDir = Direction.WEST;
			moving = true;
		}
		
		if(Input.getKey(Input.KEY_W) && Input.getKey(Input.KEY_A)){
			targetVel.x = -diagonalSpeed(speed);
			targetVel.y = -diagonalSpeed(speed);
			currentDir = Direction.NORTH;
			moving = true;
		}
		
		if(Input.getKey(Input.KEY_W) && Input.getKey(Input.KEY_D)){
			targetVel.x = diagonalSpeed(speed);
			targetVel.y = -diagonalSpeed(speed);
			currentDir = Direction.NORTH;
			moving = true;
		}
		
		if(Input.getKey(Input.KEY_S) && Input.getKey(Input.KEY_A)){
			targetVel.x = -diagonalSpeed(speed);
			targetVel.y = diagonalSpeed(speed);
			currentDir = Direction.SOUTH;
			moving = true;
		}
		
		if(Input.getKey(Input.KEY_S) && Input.getKey(Input.KEY_D)){
			targetVel.x = diagonalSpeed(speed);
			targetVel.y = diagonalSpeed(speed);
			currentDir = Direction.SOUTH;
			moving = true;
		}
		
		if(!Input.getKey(Input.KEY_W) && !Input.getKey(Input.KEY_S))
			targetVel.y = 0;
		if(!Input.getKey(Input.KEY_D) && !Input.getKey(Input.KEY_A))
			targetVel.x = 0;
		
		if(targetVel.y == 0 && targetVel.x == 0) moving = false;
	}
	
	private void animate(){
		if(moving){
			if(currentDir == Direction.SOUTH) anim.animateRow(0);
			if(currentDir == Direction.WEST) anim.animateRow(1);
			if(currentDir == Direction.EAST) anim.animateRow(2);
			if(currentDir == Direction.NORTH) anim.animateRow(3);
		}else{
			if(currentDir == Direction.SOUTH) anim.setFrame(0);
			if(currentDir == Direction.WEST) anim.setFrame(1);
			if(currentDir == Direction.EAST) anim.setFrame(2);
			if(currentDir == Direction.NORTH) anim.setFrame(3);
		}
	}
	
	@Override
	public void tick(float dt){
		processInput();
		animate();

		velocity.x = approachTarget(targetVel.x, velocity.x, dt*accSpeed);
		velocity.y = approachTarget(targetVel.y, velocity.y, dt*accSpeed);
		
		move(dt);
	}
	

	@Override
	public void collisionWith(Collideable obj) {
		
		
	}

}
