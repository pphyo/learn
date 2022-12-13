package com.jdc.app;

public class CreamDecorator extends AbstractCoffeeDecorator {

	public CreamDecorator(ICoffee cafe) {
		super(cafe);
	}

	@Override
	public String makeCoffee() {
		return cafe.makeCoffee() +  " => Cream";
	}

}
