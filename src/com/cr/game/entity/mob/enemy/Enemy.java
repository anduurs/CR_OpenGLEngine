package com.cr.game.entity.mob.enemy;

import com.cr.game.core.Vector2f;
import com.cr.game.entity.mob.Mob;
import com.cr.game.world.World;

public abstract class Enemy extends Mob{

	public Enemy(Vector2f position, World world) {
		super(position, world);
	
	}

}
