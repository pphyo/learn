package com.jdc.app;

public class FactoryApp {

	public static void main(String[] args) {
		
		AbstractToyFactory factory = new PlaneToyFactory();
		IToy toy = factory.makeToy();
		
		System.out.println(toy.makeToy());

	}

}
