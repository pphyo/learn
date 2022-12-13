package com.pphyo.procon;

import java.util.ArrayList;
import java.util.List;

class Processor {
	
	private List<Integer> list = new ArrayList<>();
	private final int LIMIT = 10;
	private Object lock = new Object();
	
	public void produce() throws InterruptedException {
		int value = 0;
		
		while(true) {
			synchronized (lock) {
				while(list.size() == LIMIT) {
					lock.wait();
				}
				list.add(value);
				System.out.println("Produce method added " + value);
				value++;
				lock.notify();
			}
		}
	}
	
	public void consume() throws InterruptedException {
		
		while(true) {
			synchronized (lock) {
				while(list.size() == 0) {
					lock.wait();
				}
				
				int value = list.remove(0);
				System.out.println("Consume method removed " + value);
				lock.notify();
			}
			Thread.sleep(1000);
		}
		
	}
	
}

public class ProducerConsumer {
	
	private static Processor processor = new Processor();

	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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

}
