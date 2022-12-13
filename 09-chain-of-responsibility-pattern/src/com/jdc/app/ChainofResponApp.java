package com.jdc.app;

public class ChainofResponApp {

	public static void main(String[] args) {
		
		BackEndLayer back = new BackEndLayer();
		MiddleLayer middle = new MiddleLayer(back);
		FrontEndLayer front = new FrontEndLayer(middle);
		
		front.help(1);

	}

}