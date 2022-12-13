package com.solt.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MainTest {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		
//		em.getTransaction().begin();
//		
//		em.getTransaction().commit();
		em.close();
		emf.close();

	}

}
