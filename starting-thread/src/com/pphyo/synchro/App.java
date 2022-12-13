package com.pphyo.synchro;

public class App {
	
	private static int count;
	
	private static synchronized void increament() {
		for(int i = 0; i < 1000; i++) {
			count++;
		}
	}
	
	private static void process() {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				increament();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				increament();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		process();
		System.out.println(count);
		System.out.println("Program Terminated...");
	}

}
