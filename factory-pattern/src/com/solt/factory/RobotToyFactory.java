package com.solt.factory;

public class RobotToyFactory extends ToyFactory {

	@Override
	public IToy makeToy() {
		return new RobotToy();
	}

}
