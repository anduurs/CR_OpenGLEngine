package com.cr.game.entity.mob.npc;

import com.cr.game.core.Vector2f;
import com.cr.game.entity.mob.Mob;
import com.cr.game.world.World;

public abstract class NPC extends Mob{

	public NPC(Vector2f position, World world) {
		super(position, world);
		
	}

}
