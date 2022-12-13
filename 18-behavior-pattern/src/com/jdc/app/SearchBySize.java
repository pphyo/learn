package com.jdc.app;

import java.util.List;

public class SearchBySize implements ISearch {

	@Override
	public List<Apple> search(List<Apple> list, Object param) {
		return list.stream().filter(a -> a.getSize() == ((int)param)).toList();
	}

}
