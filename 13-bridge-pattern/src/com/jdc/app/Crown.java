package com.jdc.app;

public class Crown extends CarAbstraction {

	public Crown(IAudio audio) {
		super(audio);
	}

	@Override
	public void playSound() {
		System.out.println(audio.makeSound());
	}
	
}
