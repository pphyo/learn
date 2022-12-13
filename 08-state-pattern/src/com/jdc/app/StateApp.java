package com.jdc.app;

public class StateApp {

	public static void main(String[] args) {
		
		Context cont = new Context();
		
		RedState red =new RedState();
		red.doAction(cont);
		
		GreenState green = new GreenState();
		green.doAction(cont);
		
		System.out.println("Current State: " + cont.getData());
		
	}

}