package com.jdc.bean;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class BeanValMain {

	public static void main(String[] args) {
		
		Student stu1 = new Student("S001", "admin", 19, "Myo Ma", LocalDate.of(200, 3, 20));
				
		
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
		Validator validator = vf.getValidator();
		Set<ConstraintViolation<Student>> violations = null;
		
		violations = validator.validate(stu1);
		
		if(violations.size() > 0) {
			for(ConstraintViolation<Student> vio : violations) {
				System.out.println(vio.getRootBeanClass().getSimpleName()
								+ " " + vio.getPropertyPath()
								+ " " + vio.getInvalidValue()
								+ " " + vio.getMessage());
			}
		}

	}

}
