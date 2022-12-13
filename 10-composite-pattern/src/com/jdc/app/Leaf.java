package com.jdc.app;

public class Leaf extends Branch {

	public Leaf(String name) {
		super(name);
	}

	@Override
	public void printTree() {
		System.out.println("      |--" + getName());
	}	

}