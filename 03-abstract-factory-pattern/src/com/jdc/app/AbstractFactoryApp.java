package com.jdc.app;

public class AbstractFactoryApp {

	public static void main(String[] args) {
		
		IComputer com = new ExpensiveComputer();
		com.installMemory().setMemory();
		com.installScreen().setScreen();
		com.installStorage().setStorage();

	}

}
