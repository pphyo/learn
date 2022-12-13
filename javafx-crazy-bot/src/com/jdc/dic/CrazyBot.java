package com.jdc.dic;

public class CrazyBot {

	private Dictionary dictionary;
	private String prevQuestion;
	
	public CrazyBot() {
		dictionary = new Dictionary();
	}
	
	public String ask(String question) {
		if(prevQuestion != null) {
			dictionary.addToMemory(prevQuestion, question);
			prevQuestion = null;
			return "Thanks for your help!";
		} else {
			String answer = dictionary.find(question);
			
			if(null == answer) {
				prevQuestion = question;
				return "I have no idea. Please teach me!";
			}
			return answer;
		}
	}
	
}