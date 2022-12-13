package com.pos.util;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

public class CommonUtil {
	
	public static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("###,###");
	public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss a");
	
	private CommonUtil() {}
	
	public static int convertStringToInt(String str) {
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	public static String encodePassword(String rawPassword) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			return Base64.getEncoder().encodeToString(digest.digest(rawPassword.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
