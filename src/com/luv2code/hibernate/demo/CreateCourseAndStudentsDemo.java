package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Review;
import com.luv2code.hibernate.demo.entity.Student;

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
		
		// use the session object to save Java object
		try {
			
			// start a transaction
			session.beginTransaction();
			
			// create a course
			Course tempCourse = new Course("Mythology & Archaeology");
			
			// create students
			Student studentA = new Student("John", "Thomas", "jt@email.com");
			Student studentB = new Student("Kelly", "Moss", "km@email.com");
			Student studentC = new Student("Jamil", "Wright", "jw@email.com");
			
			// add students to the course
			tempCourse.addStudent(studentA);
			tempCourse.addStudent(studentB);
			tempCourse.addStudent(studentC);
			
			// save the students
			System.out.println("\nSaving students...");
			session.save(studentA);
			session.save(studentB);
			session.save(studentC);

			session.save(tempCourse);
			
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
