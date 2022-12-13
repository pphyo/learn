package com.pos.excel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface ValueProcessor<T> {
	
	static <T> ValueProcessor<T> getInstance(){
		return new ValueProcessor<T>() {
			@Override
			public List<Object> getValues(T t) {
				return Arrays.stream(t.getClass().getDeclaredFields())
						.filter(field -> field.isAnnotationPresent(Column.class))
						.map(field -> {
							try {
								field.setAccessible(true);
								return field.get(t);
							} catch (IllegalArgumentException | IllegalAccessException e) {
								e.printStackTrace();
							}
							return "";
						}).collect(Collectors.toList());
			}
		};
	}

	List<Object> getValues(T t);
}
