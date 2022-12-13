package com.jdc.app;

public class ProxyApp {

	public static void main(String[] args) {
		
		IGreeting greet = new VerboseGreetingProxy(new FormalGreeting());
		System.out.println(greet.greet("P Phyo"));

	}

}
