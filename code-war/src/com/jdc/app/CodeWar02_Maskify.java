package com.jdc.app;

public class CodeWar02_Maskify {

	public static void main(String[] args) {
		
		System.out.println(maskify("4556364607935616"));
		System.out.println(maskify("1"));
		System.out.println(maskify("Skippy"));

	}
	
	public static String maskify(String str) {
		
		if(null != str) {
			
			if(str.isEmpty() && str.isBlank()) {
				return str.trim();
			} else if(str.length() <= 4) {
				return str;
			} else {
				
				int endIndex = str.length() - 4;
				
				String result = "";
				
				for(int i = 0; i < endIndex; i++) {
					result += "#";
				}
				
				return result.concat(str.substring(endIndex));
				
			}
			
		}
		
		return null;
	}

}
