package com.cr.game;

import com.cr.game.core.CoreEngine;
import com.cr.game.core.GameStateManager;
import com.cr.game.core.Window;
import com.cr.game.graphics.Screen;
import com.cr.game.input.Input;
import com.cr.game.state.PlayState;
import com.cr.game.util.ImageLoader;
import com.cr.game.world.World;

public class Game extends CoreEngine{
	
	private GameStateManager gsm;
	private Screen screen;
	
	public Game(){
		boolean fullscreen = false;
		Window.createWindow(1200, 675, fullscreen);
		init();
	}
	
	private void init(){
		new ImageLoader();
		screen = new Screen();
		gsm = new GameStateManager();
		gsm.push(new PlayState(gsm));
	}

	@Override
	public void getInput() {
		Input.tick();
		if(Input.getKey(Input.KEY_ESCAPE))
			stop();
	}

	@Override
	public void tick(float dt) {
		gsm.tick(dt);
	}

	@Override
	public void render() {
		screen.render(gsm);
	}
	
	@Override
	public void cleanUp() {
		World.getShader().deleteShader();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}

}
