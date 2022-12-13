package com.jdc.proxy;

public class ProxyMain {

	public static void main(String[] args) {
		
		IProxy proxy = new ProxyClass(new Target());
		System.out.println(proxy.doSomething());
		
	}

}
