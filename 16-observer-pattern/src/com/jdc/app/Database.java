package com.jdc.app;

import java.util.ArrayList;
import java.util.List;

public class Database implements Observerable {

	private String operation;
	private String record;
	private List<Observer> observers;
	
	public Database() {
		observers = new ArrayList<>();
	}
	
	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObserver() {
		for(Observer obs : observers)
			obs.update(operation, record);
	}
	
	public void editData(String operation, String record) {
		this.operation = operation;
		this.record = record;
		notifyObserver();
	}

}
