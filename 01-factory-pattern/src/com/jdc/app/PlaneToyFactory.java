package com.jdc.app;

public class PlaneToyFactory extends AbstractToyFactory {

	@Override
	public IToy makeToy() {
		return new PlaneToy();
	}

}
