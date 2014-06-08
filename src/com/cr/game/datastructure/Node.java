package com.cr.game.datastructure;


public class Node<E> {
	
	public E data;
	public Node<E> next;
	
	public Node(E data, Node<E> next){
		this.data = data;
		this.next = next;
	}
	

}
