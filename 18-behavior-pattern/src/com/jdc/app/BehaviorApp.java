package com.jdc.app;

import java.util.List;

public class BehaviorApp {

	public static void main(String[] args) {
		List<Apple> list = List.of(
					new Apple("Red", 1),
					new Apple("Red", 2),
					new Apple("Red", 3),
					new Apple("Green", 1),
					new Apple("Green", 2),
					new Apple("Green", 3)
				);
		
		searchStrategy(list, 3, new SearchBySize()).forEach(System.out::println);
		
		
	}
	
	private static List<Apple> searchStrategy(List<Apple> list, Object color, ISearch search) {
		return search.search(list, color);
	}

}
