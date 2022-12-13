package com.jdc.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Composite extends Component {

	private List<Component> components; 
	
	public Composite(String name) {
		super(name);
		components = new ArrayList<>();
	}

	public void addComponent(Component com) {
		Objects.nonNull(com);
		components.add(com);
	}
	
	@Override
	public void printTree() {
		System.out.println(getName());
		print();
	}
	
	public void print() {
		components.stream().forEach(com -> com.printTree());
	}

}
