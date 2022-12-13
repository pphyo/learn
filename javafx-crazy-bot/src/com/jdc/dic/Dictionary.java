package com.jdc.dic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {

	private Map<String, String> memory;
	
	@SuppressWarnings("unchecked")
	public Dictionary() {
		memory = new HashMap<>();
		try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("bot.obj"))) {
			memory = (Map<String, String>) in.readObject();
		} catch (Exception e) {
			System.err.print("First Time Load...");
		}
	}
	
	public String find(String keyword) {
		return memory.get(keyword);
	}
	
	public void addToMemory(String keyword, String answer) {
		memory.put(keyword, answer);
		saveToFile();
	}
	
	public void saveToFile() {
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("bot.obj"))) {
			out.writeObject(memory);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
