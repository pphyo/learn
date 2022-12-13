package com.jdc.app;

public abstract class CarAbstraction {

	protected IAudio audio;

	public CarAbstraction(IAudio audio) {
		this.audio = audio;
	}
	
	public abstract void playSound();
	
}
