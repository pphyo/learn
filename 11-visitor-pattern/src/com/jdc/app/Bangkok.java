package com.jdc.app;

public class Bangkok implements Visitable {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);		
	}
	
}
