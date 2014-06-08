package com.cr.game.state;

import com.cr.game.graphics.Screen;
import com.cr.game.world.World;

public class PlayState extends GameState{
	
	private World world;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		world = new World();
	}

	@Override
	public void tick(float dt) {
		world.tick(dt);
	}

	@Override
	public void render(Screen screen) {
		world.render(screen);
	}

}
