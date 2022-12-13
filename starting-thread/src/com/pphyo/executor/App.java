package com.pphyo.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker implements Runnable {
	@Override
	public void run() {
		for(int i = 0; i < 5; i++) {
			try {
				Thread.sleep(2000);
				System.out.println(i);
				System.out.println("From Worker");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
	}
}

public class App {
	
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		for(int i = 0; i < 5; i++) {
			executorService.execute(new Worker());
			System.out.println("From Main");
		}
		
		executorService.shutdown();
		
	}

}