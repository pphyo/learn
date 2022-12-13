package com.jdc.app;

public class Root extends Composite {

	public Root(String name) {
		super(name);
	}
	
	@Override
	public void printTree() {
		System.out.println("|--" + getName());
		print();
	}

}
