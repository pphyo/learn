package com.jdc.app;

public abstract class Shape {

	private int x;
	private int y;
	private String color;

	public Shape() {
	}

	public Shape(Shape source) {
		super();
		this.x = source.x;
		this.y = source.y;
		this.color = source.color;
	}

	public abstract Shape cloneObj();

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getColor() {
		return color;
	}

}
