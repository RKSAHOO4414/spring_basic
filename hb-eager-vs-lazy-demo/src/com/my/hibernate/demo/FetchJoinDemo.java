package com.my.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.my.hibernate.demo.entity.Course;
import com.my.hibernate.demo.entity.Instructor;
import com.my.hibernate.demo.entity.InstructorDetail;
import com.my.hibernate.demo.entity.Student;

public class FetchJoinDemo {
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
			
			Query<Instructor> query = session.createQuery("select i from Instructor i " + "JOIN FETCH i.courses " + "where i.id=:theInstructorId", Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			Instructor tempInstructor = query.getSingleResult();
			
			System.out.println("IRB_Code : Instructor : " + tempInstructor);
			
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
