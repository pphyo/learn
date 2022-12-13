package com.jdc.app;

public class VerboseGreetingProxy implements IGreeting {

	private IGreeting greeting;

	public VerboseGreetingProxy(IGreeting greeting) {
		super();
		this.greeting = greeting;
	}

	@Override
	public String greet(String name) {
		coding();
		return greeting.greet(name).concat(". What's up?");
	}

	private void coding() {
		System.out.println("Coding First!");
	}
}
