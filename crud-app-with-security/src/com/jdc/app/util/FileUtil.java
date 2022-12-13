package com.jdc.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtil {
	
	private static File propFile = new File("eng.properties");
	private static Properties props = new Properties();
	
	static {
		try {
			props.load(new FileInputStream(propFile));			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static File getPropFile() {
		return propFile;
	}
	
	public static void setPropFile(File propFile) {
		FileUtil.propFile = propFile;
		try {
			props.load(new FileInputStream(propFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getMessage(String key) {
		return props.getProperty(key);
	}

}
