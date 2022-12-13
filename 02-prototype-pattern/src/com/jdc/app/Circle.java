package com.jdc.app;

public class Circle extends Shape {
	
	private int radius;
	
	public Circle() {
	}
	
	public Circle(Circle source) {
		super(source);
		this.radius = source.radius;
	}
	
	@Override
	public Shape cloneObj() {
		return new Circle(this);
	}

}
