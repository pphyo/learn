package com.jdc.app;

public class ObserverApp {

	public static void main(String[] args) {
		
		BossObserver boss = new BossObserver();
		ManagerObserver manager = new ManagerObserver();
		ProgrammerObserver programmer = new ProgrammerObserver();
		
		Database db = new Database();
		
		db.addObserver(boss);
		db.addObserver(manager);
		db.addObserver(programmer);
		
		db.removeObserver(manager);
		db.editData("update", "row 2");

	}

}
