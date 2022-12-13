package com.jdc.app;

public class GreenState implements State {

	@Override
	public void doAction(Context context) {
		context.setData(this);
	}
	
	@Override
	public String toString() {
		return "Green State";
	}
	
}
