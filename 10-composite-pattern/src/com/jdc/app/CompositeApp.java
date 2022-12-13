package com.jdc.app;

public class CompositeApp {

	public static void main(String[] args) {
		
//		Composite ceo = new Composite("CEO");
//		Composite hr = new Composite("HR");
//		Composite markManager = new Composite("Marketing Manager");
//		Composite saleManager = new Composite("Sales Manager");
//		
//		Leaf markAttend1 = new Leaf("Marketing Attendent 1");
//		Leaf markAttend2 = new Leaf("Marketing Attendent 2");
//		Leaf salesAttend1 = new Leaf("Sales Attendent 1");
//		Leaf salesAttend2 = new Leaf("Sales Attendent 2");
//		
//		ceo.addComponent(hr);
//		ceo.addComponent(markManager);
//		ceo.addComponent(saleManager);
//		
//		markManager.addComponent(markAttend1);
//		markManager.addComponent(markAttend2);
//		
//		saleManager.addComponent(salesAttend1);
//		saleManager.addComponent(salesAttend2);
//		
//		ceo.printTree();
		
		printDir();

	}
	
	private static void printDir() {
		Composite cDrive = new Composite("C");
		Composite media = new Root("Media");
		
		Composite pictures = new Branch("Pictures");
		Composite videos = new Branch("Videos");
		
		Leaf photo1 = new Leaf("Aung San Su Kyi.jpg");
		Leaf photo2 = new Leaf("Kyaw Kyaw Htay Lwin.jpg");
		
		Leaf vid1 = new Leaf("Record.mp4");
		Leaf vid2 = new Leaf("Facebook.mp4");
		
		cDrive.addComponent(media);
		media.addComponent(pictures);
		media.addComponent(videos);
		
		pictures.addComponent(photo1);
		pictures.addComponent(photo2);
		
		videos.addComponent(vid1);
		videos.addComponent(vid2);
		
		cDrive.printTree();
		
		System.out.println();
		
		AuditVisitor visitor = new AuditVisitor();
		
		media.accept(visitor);
	}

}
