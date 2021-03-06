package com.cr.game.entity.mob.hero;

import com.cr.game.core.Vector2f;
import com.cr.game.entity.Collideable;
import com.cr.game.entity.mob.Mob;
import com.cr.game.graphics.Animation;
import com.cr.game.graphics.Screen;
import com.cr.game.graphics.Sprite;
import com.cr.game.input.Input;
import com.cr.game.world.World;

public class Hero extends Mob implements Collideable{
	
	private Vector2f targetVel;
	private float accSpeed = 3.5f;
	private float speed = 10f;
	
	private Animation anim;
	private HeroInput input;

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
		
		input = new HeroInput(this);
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
		input.input();
		animate();

		velocity.x = approachTarget(targetVel.x, velocity.x, dt*accSpeed);
		velocity.y = approachTarget(targetVel.y, velocity.y, dt*accSpeed);
		
		if(!collisionWithTile(targetVel.x, 0)){
			position.x = position.x + targetVel.x*dt;
		}
		
		if(!collisionWithTile(0, targetVel.y)){
			position.y = position.y + targetVel.y*dt;
		}
	}
	
//	@Override
//	public void render(Screen screen) {
//		screen.renderSprite(sprite, position.x , position.y, 1f,1f,1f,1f);
//	}
	

	@Override
	public void collisionWith(Collideable obj) {
		
		
	}

	public Vector2f getTargetVel() {
		return targetVel;
	}

	public float getSpeed() {
		return speed;
	}

}
