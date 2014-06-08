package com.cr.game.datastructure;

public class LinkedStack<E> {
	
	public Node<E> top;
	
	public LinkedStack(){
		top = null;
	}
	
	public void push(E elem){
		if(top == null){
			top = new Node<E>(elem,null);
		}else{
			Node<E> newTop = new Node<E>(elem, null);
			newTop.next = top;
			top = newTop;
		}
	}
	
	public void pop(){
		if(top != null){
			Node<E> temp = top.next;
			top = temp;
		}
	}
	
	public E pop(E elem){
		return elem;
	}

}
