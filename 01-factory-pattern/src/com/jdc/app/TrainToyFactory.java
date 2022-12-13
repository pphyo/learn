package com.jdc.app;

public class TrainToyFactory extends AbstractToyFactory {

	@Override
	public IToy makeToy() {
		return new TrainToy();
	}

}
