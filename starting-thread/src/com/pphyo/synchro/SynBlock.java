package com.pphyo.synchro;

public class SynBlock {
	
	private static int count1;
	private static int count2;
	private static Object lock1 = new Object();
	private static Object lock2 = new Object();
	
	private static void add() {
		synchronized (lock1) {
			count1++;
		}
	}
	
	private synchronized static void addAgain() {
		synchronized (lock2) {
			count2++;
		}
	}
	
	private static void compute() {
		for(int i = 0; i < 1000; i++) {
			add();
			addAgain();
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				compute();				
			}
		});		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				compute();				
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
		
		System.out.println("Count 1: " + count1 + ", Count 2: " + count2);
		System.out.println("Program Terminated...");

	}

}
