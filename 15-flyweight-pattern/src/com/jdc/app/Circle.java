package com.jdc.app;

public class Circle implements Shape {

	private String color;
	private int radius;

	public Circle(String color, int radius) {
		super();
		this.color = color;
		this.radius = radius;
	}

	@Override
	public String draw() {
		return "%s color circle is drawing with radus %d cm.".formatted(color, radius);
	}
}
