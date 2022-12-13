package com.jdc.app;

public class ProgrammerObserver implements Observer {

	@Override
	public void update(String operation, String record) {
		System.out.println("Programmer says " + operation + " operation at record " + record);		
	}
	
}
