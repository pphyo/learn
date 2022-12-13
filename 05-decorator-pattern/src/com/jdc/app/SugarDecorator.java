package com.jdc.app;

public class SugarDecorator extends AbstractCoffeeDecorator {

	public SugarDecorator(ICoffee cafe) {
		super(cafe);
	}

	@Override
	public String makeCoffee() {
		return cafe.makeCoffee() + " => Sugar";
	}

}
