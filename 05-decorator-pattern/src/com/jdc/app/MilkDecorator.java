package com.jdc.app;

public class MilkDecorator extends AbstractCoffeeDecorator {

	public MilkDecorator(ICoffee cafe) {
		super(cafe);
	}

	@Override
	public String makeCoffee() {
		return cafe.makeCoffee() + " => Milk";
	}

}
