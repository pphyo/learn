package com.jdc.app;

public class LocalVisitor implements Visitor {
	
	@Override
	public void visit(Visitable visitable) {
		System.out.println(getClass().getSimpleName() + " visits " + visitable.getClass().getSimpleName());		
	}

}
