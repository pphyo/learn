package com.pos.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.util.StringConverter;

public class LocalDateConverter extends StringConverter<LocalDate> {
	
	private DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	@Override
	public String toString(LocalDate object) {
		if(null != object) 
			return object.format(df);
		return null;
	}

	@Override
	public LocalDate fromString(String string) {
		
		if(null != string)
			return LocalDate.parse(string, df);
		
		return null;
	}

}
