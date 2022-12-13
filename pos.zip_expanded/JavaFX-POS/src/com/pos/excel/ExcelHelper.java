package com.pos.excel;

import java.util.List;

public interface ExcelHelper<T>{
	
	static <T> ExcelHelper<T> getInstance(Class<?> type) {
		return new ExcelHelper<T>() {
			
			HeaderProcessor headers;
			ValueProcessor<T> values;
			
			{
				headers = HeaderProcessor.getInstance();
				values = ValueProcessor.getInstance();
			}
			
			@Override
			public List<Object> getValues(T t) {
				return values.getValues(t);
			}
			
			@Override
			public List<String> getHeaders() {
				return headers.getHeaders(type);
			}
		};
	}

	List<String> getHeaders();
	List<Object> getValues(T t);
}
