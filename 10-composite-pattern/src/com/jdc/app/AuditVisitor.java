package com.jdc.app;

public class AuditVisitor implements Visitor {
	
	@Override
	public void visit(Component com) {
		System.out.println(getClass().getName() + " visits by auditing: " + com.getName());		
	}

}