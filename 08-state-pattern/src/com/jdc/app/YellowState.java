package com.jdc.app;

public class YellowState implements State {

	@Override
	public void doAction(Context context) {
		context.setData(this);
	}
	
	@Override
	public String toString() {
		return "Yellow State";
	}

}
