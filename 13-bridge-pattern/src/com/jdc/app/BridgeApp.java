package com.jdc.app;

public class BridgeApp {

	public static void main(String[] args) {
		
		CarAbstraction crown = new Crown(new Bose());
		crown.playSound();

	}

}
