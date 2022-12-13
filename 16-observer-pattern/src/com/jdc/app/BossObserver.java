package com.jdc.app;

public class BossObserver implements Observer {

	@Override
	public void update(String operation, String record) {
		System.out.println("Boss says " + operation + " operation at record " + record);		
	}
	
}
