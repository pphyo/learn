package com.jdc.app;

import java.util.HashMap;
import java.util.Map;

public class CircleFactory {

	static int count;
	private static Map<String, Shape> map = new HashMap<>();
	
	public static Shape createCircle(String color, int radius) {
		
		Circle c = (Circle)map.get(color);
		
		if(null == c) {
			c = new Circle(color, radius);
			map.put(color, c);
			count++;
		}
		
		return c;
		
	}
	
}
