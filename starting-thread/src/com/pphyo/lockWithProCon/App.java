package com.pphyo.lockWithProCon;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
	
	private static Lock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();
	
	public void produce() throws InterruptedException {
		lock.lock();
		System.out.println("Produce method call...");
		condition.await();
		System.out.println("Produce method call again...");
	}
	
	public void consume() throws InterruptedException {
		lock.lock();
		Thread.sleep(2000);
		System.out.println("Consume method call...");
		Thread.sleep(3000);
		condition.signal();
		lock.unlock();
	}
}

public class App {

	public static void main(String[] args) {
		Worker worker = new Worker();
		
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					worker.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					worker.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		t1.start();
		t2.start();

	}

}
