package com.cr.game;

import com.cr.game.core.CoreEngine;
import com.cr.game.core.GameStateManager;
import com.cr.game.core.Window;
import com.cr.game.graphics.Screen;
import com.cr.game.input.Input;
import com.cr.game.resource.ImageLoader;
import com.cr.game.state.PlayState;

public class Game extends CoreEngine{
	
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 675;
	
	private Screen screen;
	private GameStateManager gsm;
	
	public Game(){
		Window.createWindow(WIDTH, HEIGHT);
		new ImageLoader();
		
		gsm = new GameStateManager();
		screen = new Screen();
		
		gsm.push(new PlayState(gsm));
	}

	@Override
	public void getInput() {
		Input.tick();
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
		screen.cleanUp();
	}
	
	public static void main(String[] args){
		Game game = new Game();
		game.start();
	}

}