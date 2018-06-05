package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteACourseDemo {

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
		
		// use the session object to save Java object
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// get a course from the db
			int id = 11;
			Course tempCourse = session.get(Course.class, id);
			
			System.out.println("Loaded course: " + 
								tempCourse.getTitle() + "\n" +
								tempCourse);
			
			// delete course
			System.out.println("Deleting course...");
			
			session.delete(tempCourse);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done");
			
		} finally {
			
			// clean up code
			session.close();
			factory.close();
		}
		
	}

}
