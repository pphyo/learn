package com.jdc.bean;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;

public class Student {

	@NotEmpty(message = "Id cannot be empty!")
	private String id;
	@NameNotAdmin
	@NotEmpty(message = "Name cannot be empty!")
	private String name;
	@Min(value = 6, message = "Age at least 6") @Max(value = 30, message = "Too old to school")
	private int age;
	@NotEmpty(message = "School cannot be empty!")
	private String school;
	@Past(message = "Date of birth must be past date!")
	private LocalDate dateOfBirth;

	public Student() {

	}

	public Student(String id, String name, int age, String school, LocalDate dateOfBirth) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.school = school;
		this.dateOfBirth = dateOfBirth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
