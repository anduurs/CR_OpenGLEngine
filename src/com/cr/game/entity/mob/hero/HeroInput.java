package com.cr.game.entity.mob.hero;

import com.cr.game.entity.mob.Mob.Direction;
import com.cr.game.input.Input;

public class HeroInput {
	
	private Hero hero;
	
	public HeroInput(Hero hero){
		this.hero = hero;
	}
	
	private float diagonalSpeed(float speed){
		float res = (float) Math.sqrt((speed*speed) + (speed*speed));
		return res/2.0f + 0.3f;
	}
	
	public void input(){
		if(Input.getKey(Input.KEY_W)){
			hero.getTargetVel().y = -hero.getSpeed();
			hero.setCurrentDir(Direction.NORTH);
			hero.setMoving(true);
		}
		
		if(Input.getKey(Input.KEY_S)){
			hero.getTargetVel().y = hero.getSpeed();
			hero.setCurrentDir(Direction.SOUTH);
			hero.setMoving(true);
		}
		
		if(Input.getKey(Input.KEY_D)){
			hero.getTargetVel().x = hero.getSpeed();
			hero.setCurrentDir(Direction.EAST);
			hero.setMoving(true);
		}
		
		if(Input.getKey(Input.KEY_A)){
			hero.getTargetVel().x = -hero.getSpeed();
			hero.setCurrentDir(Direction.WEST);
			hero.setMoving(true);
		}
		
		if(Input.getKey(Input.KEY_W) && Input.getKey(Input.KEY_A)){
			hero.getTargetVel().x = -diagonalSpeed(hero.getSpeed());
			hero.getTargetVel().y = -diagonalSpeed(hero.getSpeed());
			hero.setCurrentDir(Direction.NORTH);
			hero.setMoving(true);
		}
		
		if(Input.getKey(Input.KEY_W) && Input.getKey(Input.KEY_D)){
			hero.getTargetVel().x = diagonalSpeed(hero.getSpeed());
			hero.getTargetVel().y  = -diagonalSpeed(hero.getSpeed());
			hero.setCurrentDir(Direction.NORTH);
			hero.setMoving(true);
		}
		
		if(Input.getKey(Input.KEY_S) && Input.getKey(Input.KEY_A)){
			hero.getTargetVel().x = -diagonalSpeed(hero.getSpeed());
			hero.getTargetVel().y  = diagonalSpeed(hero.getSpeed());
			hero.setCurrentDir(Direction.SOUTH);
			hero.setMoving(true);
		}
		
		if(Input.getKey(Input.KEY_S) && Input.getKey(Input.KEY_D)){
			hero.getTargetVel().x = diagonalSpeed(hero.getSpeed());
			hero.getTargetVel().y  = diagonalSpeed(hero.getSpeed());
			hero.setCurrentDir(Direction.SOUTH);
			hero.setMoving(true);
		}
		
		if(!Input.getKey(Input.KEY_W) && !Input.getKey(Input.KEY_S))
			hero.getTargetVel().y  = 0;
		if(!Input.getKey(Input.KEY_D) && !Input.getKey(Input.KEY_A))
			hero.getTargetVel().x = 0;
		
		if(hero.getTargetVel().y  == 0 && hero.getTargetVel().x == 0)hero.setMoving(false);
	}

}
