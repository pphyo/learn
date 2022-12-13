package com.solt.factory;

public class PlainToyFactory extends ToyFactory {

	@Override
	public IToy makeToy() {
		return new PlainToy();
	}

}
