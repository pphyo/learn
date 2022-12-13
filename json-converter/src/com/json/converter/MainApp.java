package com.json.converter;

import java.lang.reflect.InvocationTargetException;

import com.json.converter.domain.Student;
import com.json.converter.util.JSONConverter;

public class MainApp {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		JSONConverter<Student> converter = new JSONConverter<>();
		System.out.println(converter.convertObjectToJson(
				new Student("kyaw lin tun", "kyawlin@gmail.com", "No.02 Padauk 2 Street")));

	}

}
