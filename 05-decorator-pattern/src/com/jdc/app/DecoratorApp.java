package com.jdc.app;

public class DecoratorApp {

	public static void main(String[] args) {
		
		AbstractCoffeeDecorator dec = new SugarDecorator(new MilkDecorator(new CreamDecorator(new PlainCoffee())));
		System.out.println(dec.makeCoffee());
		
	}

}
