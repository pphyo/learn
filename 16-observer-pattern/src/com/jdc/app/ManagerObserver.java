package com.jdc.app;

public class ManagerObserver implements Observer {

	@Override
	public void update(String operation, String record) {
		System.out.println("Manager says " + operation + " operation at record " + record);		
	}
	
}
