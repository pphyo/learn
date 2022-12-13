package com.jdc.app;

import static com.jdc.app.util.CommonUtil.*;
import static com.jdc.app.util.FileUtil.*;

import java.io.File;

import com.jdc.app.entity.Student.Gender;
import com.jdc.app.entity.Student.Grade;
import com.jdc.app.entity.Student;
import com.jdc.app.entity.User;
import com.jdc.app.repo.StudentRepo;
import com.jdc.app.repo.UserRepo;
import com.jdc.app.util.FileUtil;

public class Runner {
	
	private static final String[] loginPage = new String[3];
	private static final String[] appPage = new String[7];
	private static final String[] changeLanguage = new String[3];
	
	private UserRepo userRepo;
	private StudentRepo stuRepo;
	
	{
		// add data to arrays
		addDataToArrays();
		
	}
	
	public Runner() {
		userRepo = UserRepo.getInstance();
		stuRepo = StudentRepo.getInstance();
	}
	
	public static void addDataToArrays() {
		loginPage[0] = getMessage("app.log_in");
		loginPage[1] = getMessage("app.sign_up");
		loginPage[2] = getMessage("app.exit");
		
		appPage[0] = getMessage("app.add_new");
		appPage[1] = getMessage("app.edit");
		appPage[2] = getMessage("app.delete");
		appPage[3] = getMessage("app.find_all");
		appPage[4] = getMessage("app.find_by_id");
		appPage[5] = getMessage("app.change_language");
		appPage[6] = getMessage("app.log_out");
		
		changeLanguage[0] = getMessage("app.lang.eng");
		changeLanguage[1] = getMessage("app.lang.myan");
		changeLanguage[2] = getMessage("app.lang.jpn");
	}
	
	public void run() {
		// show title
		printHeaderFooter(getMessage("app.welcome"));
		
		breakEmptyLine();	
		setOrder(loginPage);
			
		int code = chooseOpt();			
		chooseLoginOpt(code);

		breakEmptyLine();
		printHeaderFooter(getMessage("app.thank_you"));
		
	}
	
	private void chooseLoginOpt(int code) {
		switch (code) {
			case 1:
				logIn();
				break;
			case 2:
				signUp();
				break;
			case 3:
				breakEmptyLine();
				printHeaderFooter(getMessage("app.thank_you"));
				System.exit(0);
				break;
			default:
				System.out.println(getMessage("app.error.wrong_opt"));
		}
			
	}
	
	private void logIn() {
		
		if(userRepo.getRepo().isEmpty()) {
			breakEmptyLine();
			System.out.println(getMessage("app.error.no_register_user"));
			
			signUp();
			
		} else {
		
			breakEmptyLine();
			printHeaderFooter(getMessage("app.log_in"));
			
			User user = getUser();
			
			if(userRepo.checkUser(user)) {
				// go to app
				doBusiness();
				
			} else {
				
				breakEmptyLine();
				System.out.println(getMessage("app.error.user_not_found"));
				logIn();
			}
		}
		
	}
	
	private void signUp() {
		
		breakEmptyLine();
		printHeaderFooter(getMessage("app.sign_up"));
		
		User user = getUser();
		
		if(!isNull(user)) {
			userRepo.add(user);
			
			breakEmptyLine();
			System.out.println(getMessage("app.register_success"));
			
			logIn();
		}
		
	}
	
	private void doBusiness() {
		breakEmptyLine();
		printHeaderFooter(getMessage("app.welcome_title"));
		
		do {
			breakEmptyLine();
			setOrder(appPage);
			
			int code = chooseOpt();
			
			chooseAppOpt(code);
			
		} while (wantToContinue().equalsIgnoreCase("y"));
		
	}
	
	private String wantToContinue() {
		breakEmptyLine();
		return getString(getMessage("app.want_to_continue"));
	}
	
	private int chooseOpt() {
		breakEmptyLine();
		return getInt(getMessage("app.choose"));
	}
	
	private User getUser() {
		breakEmptyLine();
		String username = getString(getMessage("app.enter_username"));
		String password = getString(getMessage("app.enter_password"));
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		return user;
	}
	
	private void chooseAppOpt(int code) {
		switch (code) {
			case 1:
				add();
				break;
			case 2:
				update();
				break;
			case 3:
				delete();
				break;
			case 4:
				findById();
				break;
			case 5:
				findAll();
				break;
			case 6:
				changeLanguage();
				break;
			case 7:
				logOut();
				break;
			default:
				System.out.println(getMessage("app.error.wrong_opt"));
			
		}
	}
	
	private void breakEmptyLine() {
		System.out.println();
	}
	
	private void add() {
		breakEmptyLine();
		
		try {
			String name = getString(getMessage("app.enter_name"));
			if(isEmpty(name))
				throw new RuntimeException(getMessage("app.error.name"));
			
			Gender gender = getGender();
			if(isNull(gender))
				throw new RuntimeException(getMessage("app.error.gender"));
			
			int age = getInt(getMessage("app.enter_age"));
			if(age <= 0)
				throw new RuntimeException(getMessage("app.error.age1"));
			
			Grade grade = getGrade();
			if(isNull(grade))
				throw new RuntimeException(getMessage("app.error.grade"));
				
			String phone = getString(getMessage("app.enter_phone"));
			if(isEmpty(phone))
				throw new RuntimeException(getMessage("app.error.phone"));
			
			Student stu = new Student();
			stu.setName(name);
			stu.setGender(gender);
			stu.setAge(age);
			stu.setGrade(grade);
			stu.setPhone(phone);
			
			stuRepo.add(stu);
			
			breakEmptyLine();
			System.out.println(getMessage("app.stu_add_success"));
		} catch (NumberFormatException e) {
			breakEmptyLine();
			System.out.println(getMessage("app.error.age2"));
			add();
		} catch (Exception e) {
			breakEmptyLine();
			System.out.println(e.getMessage());
			add();
		}
		
	}
	
	private void update() {
		
	}
	
	private void delete() {
		
	}
	
	private void findById() {
		
	}
	
	private void findAll() {
		
	}
	
	private void changeLanguage() {
		breakEmptyLine();
		printHeaderFooter(getMessage("app.lang.title"));
		breakEmptyLine();
		
		setOrder(changeLanguage);
		
		int code = chooseOpt();
		
		switch(code) {
			case 1:
				changeLangFile("eng.properties");
				break;
			case 2:
				changeLangFile("myan.properties");
				break;
			case 3:
				changeLangFile("jpn.properties");
				break;
			default:
				System.out.println(getMessage("app.error.select_wrong_opt"));
				changeLanguage();
		}
	}
	
	public void changeLangFile(String file) {
		FileUtil.setPropFile(new File(file));
		addDataToArrays();
	}
	
	private void logOut() {
		breakEmptyLine();
		System.out.println(getMessage("app.log_out_success"));
		breakEmptyLine();
		run();
	}
	
	private Gender getGender() {
		
		System.out.println(getMessage("app.select_gender") );
		Gender[] genders = Gender.values();
		
		setOrder(genders);
		
		int code = chooseOpt();
		
		Gender gender = null;
		
		switch(code) {
			case 1:
				gender = Gender.Male;
				break;
			case 2:
				gender = Gender.Female;
				break;
			case 3:
				gender = Gender.Other;
				break;
			default:
				System.out.println(getMessage("app.error.select_wrong_opt"));
				getGender();
		}
		
		return gender;
	}
	
	private Grade getGrade() {
		System.out.println(getMessage("app.select_grade"));
		Grade[] grades = Grade.values();
		
		setOrder(grades);
		
		int code = chooseOpt();
		
		Grade grade = null;
		
		switch(code) {
			case 1:
				grade = Grade.KG;
				break;
			case 2:
				grade = Grade.G1;
				break;
			case 3:
				grade = Grade.G2;
				break;
			case 4:
				grade = Grade.G3;
				break;
			case 5:
				grade = Grade.G4;
				break;
			case 6:
				grade = Grade.G5;
				break;
			case 7:
				grade = Grade.G6;
				break;
			case 8:
				grade = Grade.G7;
				break;
			case 9:
				grade = Grade.G8;
				break;
			case 10:
				grade = Grade.G9;
				break;
			case 11:
				grade = Grade.G10;
				break;
			default:
				System.out.println(getMessage("app.error.select_wrong_opt"));
				getGender();
		}
		
		return grade;
		
	}

}
