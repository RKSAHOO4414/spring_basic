package com.my.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.my.hibernate.demo.entity.Course;
import com.my.hibernate.demo.entity.Instructor;
import com.my.hibernate.demo.entity.InstructorDetail;
import com.my.hibernate.demo.entity.Review;
import com.my.hibernate.demo.entity.Student;

public class CreateCourseAndStudentsDemo {
	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Instructor.class)
										.addAnnotatedClass(InstructorDetail.class)
										.addAnnotatedClass(Course.class)
										.addAnnotatedClass(Review.class)
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();
				
		// create session
		Session session = factory.getCurrentSession();
				
		try {	
					
			// start a transaction
			session.beginTransaction();
			
			Course tempCourse = new Course("Pacman - How to score One Million Points");
			
			System.out.println("\nSaving the course ... ");
			session.save(tempCourse);
			System.out.println("Saved the course : "+tempCourse);
			
			Student tempStudent1 = new Student(1, "John", "Doe", "john@irb.com");
			Student tempStudent2 = new Student(2, "Mary", "Public", "Mary@irb.com");
			
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			System.out.println("\nSaving students ...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Saved students ..."+tempCourse.getStudents());
			
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
