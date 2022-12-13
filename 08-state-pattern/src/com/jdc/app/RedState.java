package com.jdc.app;

public class RedState implements State {
	
	@Override
	public void doAction(Context context) {
		context.setData(this);		
	}
	
	@Override
	public String toString() {
		return "Red Light";
	}

}
