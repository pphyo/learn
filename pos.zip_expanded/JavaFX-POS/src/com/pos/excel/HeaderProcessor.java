package com.pos.excel;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface HeaderProcessor {
	
	static HeaderProcessor getInstance() {
		return new HeaderProcessor() {
			@Override
			public List<String> getHeaders(Class<?> type) {
				return Arrays.stream(type.getDeclaredFields())
						.filter(field -> field.isAnnotationPresent(Column.class)).map(field -> {
							Column column = field.getAnnotation(Column.class);
							String value = column.value();
							return value.isEmpty() ? field.getName().toUpperCase() : value.toUpperCase();
						}).collect(Collectors.toList());
			}
		};
	}

	List<String> getHeaders(Class<?> type);
}
