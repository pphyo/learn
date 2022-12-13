package com.jdc.app;

public abstract class Component implements Visitable {
	
	private String name;

	public Component(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	public abstract void printTree();

}
