package com.jdc.app;

public abstract class AbstractFacade<T> {

	private Class<T> entity;
	
	public AbstractFacade(Class<T> entity) {
		this.entity = entity;
	}
	
	public void create(T t) {
		System.out.println(t.getClass().getName() + " created.");
	}
	
	public void delete(T t) {
		System.out.println(t.getClass().getName() + " deleted.");
	}
	
	public void edit(T t) {
		System.out.println(t.getClass().getName() + " edited.");
	}

	public Class<T> getEntity() {
		return entity;
	}
	
}
