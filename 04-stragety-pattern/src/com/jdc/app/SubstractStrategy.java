package com.jdc.app;

public class SubstractStrategy implements CalculateStrategy {

	@Override
	public double calculate(int a, int b) {
		return a - b;
	}
}
