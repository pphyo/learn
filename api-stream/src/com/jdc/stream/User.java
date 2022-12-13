package com.jdc.stream;

public class User {

	private String name;
	private String email;
	private String password;
	private int age;

	public User(String name, String email, String password, int age) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public int getAge() {
		return age;
	}

}
