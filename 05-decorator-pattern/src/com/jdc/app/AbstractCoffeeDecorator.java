package com.jdc.app;

public abstract class AbstractCoffeeDecorator implements ICoffee {
	
	protected ICoffee cafe;
	
	public AbstractCoffeeDecorator(ICoffee cafe) {
		this.cafe = cafe;
	}

}
