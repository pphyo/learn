package com.json.converter.domain;

@JSON
public class Student {

	@JSONField(name = "student_name")
	private String name;
	private String email;
	
	@JSONField
	private String address;
	
	public Student(String name, String email, String address) {
		this.name = name;
		this.email = email;
		this.address = address;
	}
	
	@Init
	private void init() {
		this.name = name.toUpperCase();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
