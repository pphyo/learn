package com.jdc.app;

public class Originator {
	
	private String state;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public Memento save() {
		return new Memento(state);
	}
	
	public void restore(Memento mem) {
		this.state = mem.getState();
	}
	
}
