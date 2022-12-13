package com.json.converter.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.json.converter.domain.Init;
import com.json.converter.domain.JSON;
import com.json.converter.domain.JSONField;

public class JSONConverter<T> {

	public String convertObjectToJson(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		checkObject(t);
		initializeObject(t);
		return getJSON(t);
	}
	
	private void checkObject(T t) {
		if(Objects.isNull(t))
			throw new JSONException("Null object cann't convert to json!");
		
		if(!t.getClass().isAnnotationPresent(JSON.class))
			throw new JSONException(t.getClass().getName().concat(" not annotated with json annotation!"));
	}
	
	private void initializeObject(T t) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for(Method method : t.getClass().getDeclaredMethods()) {
			if(method.isAnnotationPresent(Init.class)) {
				method.setAccessible(true);
				method.invoke(t);
			}
		}
	}
	
	private String getJSON(T t) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> map = new HashMap<>();
		
		for(Field field : t.getClass().getDeclaredFields()) {
			if(field.isAnnotationPresent(JSONField.class)) {
				field.setAccessible(true);
				map.put(getKey(field), field.get(t));
			}
		}
		
		String jsonString = map.entrySet().stream()
		.map(entry -> "\"" + entry.getKey() + "\" : \"" + entry.getValue() + "\"")
		.collect(Collectors.joining(",\n"));
		
		return "{\n" + jsonString + "\n}";
	}
	
	private String getKey(Field field) {
		String name = field.getAnnotation(JSONField.class).name();
		return name.isEmpty() ? field.getName() : name;
	}
}