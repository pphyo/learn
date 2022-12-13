package com.jdc.app;

public class CheapComputer implements IComputer {

	@Override
	public IMemory installMemory() {
		return new CheapMemory();
	}

	@Override
	public IScreen installScreen() {
		return new LowResolution();
	}

	@Override
	public IStorage installStorage() {
		return new CheapStorage();
	}

}
