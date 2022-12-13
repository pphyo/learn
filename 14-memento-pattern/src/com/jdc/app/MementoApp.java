package com.jdc.app;

public class MementoApp {

	public static void main(String[] args) {
		
		Originator ori = new Originator();
		CareTaker taker = new CareTaker();
		
		ori.setState("Commit One");
		ori.setState("Commit Two");
		ori.setState("Commit Three");
		
		Memento mem1 =  ori.save();
		taker.addState(mem1);
		
		ori.setState("Commit Four");
		ori.setState("Commit Five");
		
		Memento mem2 = ori.save();
		taker.addState(mem2);
		
		ori.setState("Commit Six");
		ori.setState("Commit Seven");
		
		System.out.println("Last State: " + ori.getState());
		System.out.println("First Save State: " + taker.getState(0).getState());

	}

}
