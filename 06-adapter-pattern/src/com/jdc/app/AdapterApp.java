package com.jdc.app;

public class AdapterApp {

	public static void main(String[] args) {
		
		TVAdapter adapter = new TVAdapter(new TVAdaptee());
		adapter.switchOn();

	}

}
