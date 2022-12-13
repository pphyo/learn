package com.jdc.app;

public class ItemFacade extends AbstractFacade<Item> implements LocalFacadeItem {

	public ItemFacade() {
		super(Item.class);
	}

//	@Override
//	public void create(Item item) {
//		System.out.println(item.getClass().getName() + " item facade created.");
//	}
//	
//	@Override
//	public void edit(Item item) {
//		System.out.println(item.getClass().getName() + " item facade edited.");
//	}
//	
//	@Override
//	public void delete(Item item) {
//		System.out.println(item.getClass().getName() + " item facade deleted.");
//	}

}
