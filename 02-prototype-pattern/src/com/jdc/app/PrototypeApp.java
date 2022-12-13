package com.jdc.app;

public class PrototypeApp {

	public static void main(String[] args) {
		
		Shape c1 = new Circle();
		Shape r1 = new Rectangle();
		
		Shape c2 = c1.cloneObj();
		Shape r2 = r1.cloneObj();
		
		System.out.println("%s\n%s\n%s\n%s\n".formatted(c1, c2, r1, r2));
		System.out.println(c1 == c2);
		System.out.println(r1 == r2);

	}

}
