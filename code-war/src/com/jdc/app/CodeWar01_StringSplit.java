package com.jdc.app;

import java.util.Arrays;

public class CodeWar01_StringSplit {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution("MyLoveCat")));

	}
	
	public static String[] solution(String s) {
		
		String[] result = new String[0];
		
		if(null != s && !s.isEmpty() && !s.isBlank()) {
			
			for(int i = 0; i < s.length(); i += 2) {
				
				String[] temp = new String[result.length + 1];
				
				for(int j = 0; j < result.length; j++) {
					temp[j] = result[j];
				}
				
				int lastIndex = temp.length - 1;
				
				// last one char
				if((s.length() - i) == 1) {
					temp[lastIndex] = s.substring(i).concat("_");
				} else {
					temp[lastIndex] = s.substring(i, i + 2);
				}
				
				result = temp;
			}
			
		}
		
		return result;
	}

}
