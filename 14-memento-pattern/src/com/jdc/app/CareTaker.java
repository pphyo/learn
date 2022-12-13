package com.jdc.app;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
	
	private List<Memento> states;
	
	public CareTaker() {
		states = new ArrayList<>();
	}
	
	public void addState(Memento state) {
		states.add(state);
	}
	
	public Memento getState(int index) {
		return states.get(index);
	}

}
