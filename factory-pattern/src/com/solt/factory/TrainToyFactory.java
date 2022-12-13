package com.solt.factory;

public class TrainToyFactory extends ToyFactory {

	@Override
	public IToy makeToy() {
		return new TrainToy();
	}

}
