package com.jdc.app;

public class TVAdapter {

	private TVAdaptee adaptee;

	public TVAdapter(TVAdaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}

	public void switchOn() {
		adaptee.turnRight();
	}

	public void switchOff() {
		adaptee.turnLeft();
	}

}
