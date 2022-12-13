package com.jdc.app;

public class FacadeApp {

	public static void main(String[] args) {
		
		LocalFacadeItem itemFacade = new ItemFacade();
		itemFacade.create(new Item());

	}

}
