package com.jdc.app;

public class StrategyApp {

	public static void main(String[] args) {
		
		CalculateStrategy cal = new MultiplyStrategy();
		
		ContextStrategy context = new ContextStrategy();
		context.setStrategy(cal);
		
		double result = context.calculate(10, 20);
		
		System.out.println("Result: " + result);

	}

}
