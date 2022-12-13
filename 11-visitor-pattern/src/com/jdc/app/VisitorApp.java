package com.jdc.app;

public class VisitorApp {

	public static void main(String[] args) {
		
		Visitable visitable = new Bangkok();
		
		Visitor visitor = new LocalVisitor();
		visitable.accept(visitor);

	}

}
