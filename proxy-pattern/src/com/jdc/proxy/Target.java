package com.jdc.proxy;

public class Target implements IProxy {

	@Override
	public String doSomething() {
		return "Target Object do something.\n";
	}

}
