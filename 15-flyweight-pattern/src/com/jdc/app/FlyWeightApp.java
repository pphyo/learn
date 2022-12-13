package com.jdc.app;

import java.util.Random;

public class FlyWeightApp {

	static String[] colors = {"Blue", "White", "Green", "Black", "Yellow"};
	
	public static void main(String[] args) {
		
		Random ran = new Random();
		
		for(int i = 0; i < 20; i++) {
			var circle = CircleFactory.createCircle(colors[ran.nextInt(colors.length)], ran.nextInt(90) * 10);
			System.out.println(circle.draw());
		}
		
		System.out.println(CircleFactory.count);

	}

}
