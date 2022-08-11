package com.my.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.my.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {
	
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
						
		// create session
		Session session = factory.getCurrentSession();
						
		try {			
			// create a student object
			System.out.println("Creating 3 student object...");
			Student tempStudent1 = new Student(1, "John", "Doe", "john@luv2code.com");
			Student tempStudent2 = new Student(2, "Mary", "Public", "mary@luv2code.com");
			Student tempStudent3 = new Student(3, "Bonita", "Applebaum", "bonita@luv2code.com");
							
			// start a transaction
			session.beginTransaction();
							
			// save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
							
			// commit transaction
			session.getTransaction().commit();
							
			System.out.println("Done!");
		}finally {
			factory.close();
		}
		
	}
	
}
