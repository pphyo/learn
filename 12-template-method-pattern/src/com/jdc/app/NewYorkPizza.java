package com.jdc.app;

public class NewYorkPizza extends PizzaTemplate {

	@Override
	public void prepare() {
		System.out.println("NewYork Pizza prepared.");
	}

	@Override
	public void bake() {
		System.out.println("NewYork Pizza baked.");
		
	}

	@Override
	public void topping() {
		System.out.println("NewYork Pizza topping.");		
	}

	@Override
	public void serve() {
		System.out.println("NewYork Pizza served.");
	}

	@Override
	public void payment() {
		System.out.println("NewYork Pizza makes payment.");
	}

}
