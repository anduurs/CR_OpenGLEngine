package com.cr.game.state;

import com.cr.game.datastructure.LinkedStack;
import com.cr.game.datastructure.Node;
import com.cr.game.graphics.Screen;

public class GameStateManager {
	
	private LinkedStack<GameState> states;
	
	public GameStateManager(){
		states = new LinkedStack<GameState>();
	}

	/**
	 * Adds a state at the top of the state stack
	 * @param state the game state that will be added to the top of the state stack
	 */
	public void push(GameState state){
		states.push(state);
	}
	
	/**
	 * Deletes the state at the top of the state stack
	 */
	public void pop(){
		states.pop();
	}
	
	public GameState next(){
		return states.top.next.data;
	}
	
	public Node<GameState> peek(){
		return states.top;
	}
	
	public void tick(float dt){
		tick(states.top, dt);
	}
	
	private void tick(Node<GameState> state, float dt){
		state.data.tick(dt);
		if(!state.data.isTickingBlocked())
			tick(state.next, dt);
	}
	
	public void render(Screen screen){
		render(states.top, screen);
	}
	
	private void render(Node<GameState> state, Screen screen){
		if(!state.data.isRenderingBlocked())
			render(state.next, screen);
		state.data.render(screen);
	}

}
