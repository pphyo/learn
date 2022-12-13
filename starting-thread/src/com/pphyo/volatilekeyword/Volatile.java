package com.pphyo.volatilekeyword;

class Worker implements Runnable {

	private boolean isTerminated = false;
	
	@Override
	public void run() {
		while(!isTerminated) {
			System.out.println("Hello from worker class...");
		
			try {
				Thread.sleep(300);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setIsTerminated(boolean isTerminated) {
		this.isTerminated = isTerminated;
	}
	
}

public class Volatile {

	public static void main(String[] args) throws InterruptedException {
		
		Worker worker = new Worker();
		
		Thread t1 = new Thread(worker);

		t1.start();
		
		Thread.sleep(3000);
		
		worker.setIsTerminated(true);
		
		System.out.println("Program Terminated...");
	}

}
