package com.jdc.app;

public class Rectangle extends Shape {

	private int width;
	private int height;

	public Rectangle() {
	}
	
	public Rectangle(Rectangle source) {
		super(source);
		this.width = source.width;
		this.height = source.height;
	}

	@Override
	public Shape cloneObj() {
		return new Rectangle(this);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
