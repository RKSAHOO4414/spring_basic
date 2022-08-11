package com.my.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.my.hibernate.demo.entity.Course;
import com.my.hibernate.demo.entity.Instructor;
import com.my.hibernate.demo.entity.InstructorDetail;
import com.my.hibernate.demo.entity.Student;

public class DeleteCourseDemo {
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
				
		try {	
					
			// start a transaction
			session.beginTransaction();
			
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);
			
			System.out.println("Deleteing course : "+tempCourse);
			
			session.delete(tempCourse);
			
			// commit transaction
			session.getTransaction().commit();
					
			System.out.println("Done!");
		}
		finally {
			session.close();
			
			factory.close();
		}
		
	}
}
