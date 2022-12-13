package com.jdc.app;

public class Branch extends Root {

	public Branch(String name) {
		super(name);
	}
	
	@Override
	public void printTree() {
		System.out.println("   |--" + getName());
		print();
	}

}
