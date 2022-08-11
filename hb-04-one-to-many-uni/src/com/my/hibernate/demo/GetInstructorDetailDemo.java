package com.my.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.my.hibernate.demo.entity.Instructor;
import com.my.hibernate.demo.entity.InstructorDetail;
import com.my.hibernate.demo.entity.Student;

public class GetInstructorDetailDemo {
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
				
		try {			
		
			session.beginTransaction();

			int theId = 2999;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			System.out.println("tempInstructorDetail : " + tempInstructorDetail);
			
			System.out.println("the associated instructor : " + tempInstructorDetail.getInstructor());
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			//handle connection leak issue
			session.close();
			
			factory.close();
		}
		
	}
}
