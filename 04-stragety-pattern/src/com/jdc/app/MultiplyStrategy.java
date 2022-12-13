package com.jdc.app;

public class MultiplyStrategy implements CalculateStrategy {
	@Override
	public double calculate(int a, int b) {
		return a * b;
	}
}
