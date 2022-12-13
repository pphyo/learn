package com.jdc.app;

public class ExpensiveComputer implements IComputer {

	@Override
	public IMemory installMemory() {
		return new ExpensiveMemory();
	}
	
	@Override
	public IScreen installScreen() {
		return new HighResolution();
	}
	
	@Override
	public IStorage installStorage() {
		return new ExpensiveStorage();
	}
	
}
