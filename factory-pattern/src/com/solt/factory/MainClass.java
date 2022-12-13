package com.solt.factory;

public class MainClass {

	public static void main(String[] args) {
		
		ToyFactory toyFactory = new RobotToyFactory();
		IToy toy = toyFactory.makeToy();
		
		System.out.println(toy.makeToy());
		
	}

}
