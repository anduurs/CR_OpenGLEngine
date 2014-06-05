package com.cr.game.state;

import com.cr.game.core.GameStateManager;
import com.cr.game.graphics.Screen;
import com.cr.game.level.Level;
import com.cr.game.world.World;

public class PlayState extends GameState{
	
	private World world;
//	private Level level;

	public PlayState(GameStateManager gsm) {
		super(gsm);
		init();
	}

	@Override
	public void init() {
		world = new World();
//		level = new Level("/bitmaps/level.png");
	}

	@Override
	public void tick(float dt) {
		world.tick(dt);
//		level.tick(dt);
	}

	@Override
	public void render(Screen screen) {
		world.render(screen);
//		level.render(0,0);
	}

}
