package com.pphyo.priority;

class Worker implements Runnable {

	private String name;

	public Worker(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println(this);
		}
	}

	@Override
	public String toString() {
		return name;
	}
	
}

public class App {

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Worker("Thread#1"));
		Thread t2 = new Thread(new Worker("Thread#2"));
		
//		t1.setPriority(Thread.MIN_PRIORITY);
//		t2.setPriority(Thread.MAX_PRIORITY);
		
		t1.start();
		t2.start();

	}
}