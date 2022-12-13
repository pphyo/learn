package com.jdc.app;

public class ContextStrategy {
	
	private CalculateStrategy strategy;
	
	public void setStrategy(CalculateStrategy strategy) {
		this.strategy = strategy;
	}
	
	public double calculate(int a, int b) {
		return strategy.calculate(a, b);
	}

}
