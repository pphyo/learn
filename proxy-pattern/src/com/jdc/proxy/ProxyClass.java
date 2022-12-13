package com.jdc.proxy;

public class ProxyClass implements IProxy {

	private Target target;
	
	public ProxyClass(Target target) {
		this.target = target;
	}

	@Override
	public String doSomething() {
		String str = target.doSomething() + doSomethingExtra();
		return str;
	}
	
	public String doSomethingExtra() {
		return "Proxy Do Something Extra.";
	}

}