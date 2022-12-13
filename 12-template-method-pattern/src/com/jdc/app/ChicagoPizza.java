package com.jdc.app;

public class ChicagoPizza extends PizzaTemplate {

	@Override
	public void prepare() {
		System.out.println("Chicago Pizza prepared.");
	}

	@Override
	public void bake() {
		System.out.println("Chicago Pizza baked.");		
	}

	@Override
	public void topping() {
		System.out.println("Chicago Pizza topping.");
		
	}

	@Override
	public void serve() {
		System.out.println("Chicago Pizza served.");
	}

	@Override
	public void payment() {
		System.out.println("Chicago Pizza makes payment.");		
	}

}
