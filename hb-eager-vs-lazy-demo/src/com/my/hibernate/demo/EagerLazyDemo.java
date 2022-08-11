package com.my.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.my.hibernate.demo.entity.Course;
import com.my.hibernate.demo.entity.Instructor;
import com.my.hibernate.demo.entity.InstructorDetail;
import com.my.hibernate.demo.entity.Student;

public class EagerLazyDemo {
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
			
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			
			System.out.println("IRB_Code : Instructor : " + tempInstructor);
			
			System.out.println("IRB_Code : Courses : " + tempInstructor.getCourses());
			
			session.getTransaction().commit();
			
			session.close();
			
			System.out.println("IRB_Code : The session is now closed!\n");
			
			System.out.println("IRB_Code : Courses : " + tempInstructor.getCourses());
					
			System.out.println("IRB_Code : Done!");
		}
		finally {
			session.close();
			
			factory.close();
		}
		
	}
}
