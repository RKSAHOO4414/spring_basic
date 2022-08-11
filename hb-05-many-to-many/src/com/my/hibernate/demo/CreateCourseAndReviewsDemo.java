package com.my.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.my.hibernate.demo.entity.Course;
import com.my.hibernate.demo.entity.Instructor;
import com.my.hibernate.demo.entity.InstructorDetail;
import com.my.hibernate.demo.entity.Student;

public class CreateCourseAndReviewsDemo {
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
			
			Course tempCourse1 = new Course("Air Guiter - The Utimate Guide");
			Course tempCourse2 = new Course("The Pinball Masterclass");
			
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			
			session.save(tempCourse1);
			session.save(tempCourse2);
					
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
