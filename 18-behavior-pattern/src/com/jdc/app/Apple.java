package com.jdc.app;

public class Apple {

	private String color;
	private int size;

	public Apple(String color, int size) {
		super();
		this.color = color;
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "color=" + color + ", size=" + size;
	}

}
