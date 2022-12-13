package com.jdc.app;

public class ToppingDecorator extends AbstractCoffeeDecorator {

	public ToppingDecorator(ICoffee cafe) {
		super(cafe);
	}

	@Override
	public String makeCoffee() {
		return cafe.makeCoffee() + " => Topping";
	}

}
