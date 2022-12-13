package com.jdc.app;

public class Singapore implements Visitable {
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
