package com.my.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.my.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
				
		try {
			int studentId = 1;
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("\nGetting Student with id : "+studentId);
			
			Student myStudent = session.get(Student.class, studentId);
			
			//System.out.println("Deleteing student : " + myStudent);
			//session.delete(myStudent);
			
			System.out.println("Deleteing student id=2");
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			session.getTransaction().commit();
			System.out.println("Done!");
		
		}finally {
			factory.close();
		}
	
	}
}
