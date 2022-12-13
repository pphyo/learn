package com.jdc.bean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameNotAdminValidator implements ConstraintValidator<NameNotAdmin, String>{

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return !name.equalsIgnoreCase("admin");
	}

	
	
}
