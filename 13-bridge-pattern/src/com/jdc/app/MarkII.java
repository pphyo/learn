package com.jdc.app;

public class MarkII extends CarAbstraction {

	public MarkII(IAudio audio) {
		super(audio);
	}

	@Override
	public void playSound() {
		System.out.println(audio.makeSound());		
	}

}
