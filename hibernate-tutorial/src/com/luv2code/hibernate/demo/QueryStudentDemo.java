package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		//create a session
		Session session = factory.getCurrentSession();
		
		try {			
			//start a transaction
			session.beginTransaction();
			
			// query students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(theStudents);
			
			// query students: firstName = 'Sandra'
			theStudents = session.createQuery("from Student s where s.firstName='Sandra'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents with the first name of Sandra");
			displayStudents(theStudents);
			
			// query students: firstName='Idalia' OR firstName='Lupe'
			theStudents = session.createQuery("from Student s where s.firstName='Idalia' OR s.firstName='Lupe'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents with the first name of Idalia OR Lupe");
			displayStudents(theStudents);
			
			// query students where email LIKE %luv2code.com
			theStudents = session.createQuery("from Student s where s.email LIKE '%luv2code.com'").getResultList();
			
			// display the students
			System.out.println("\n\nStudents with a email that ends with luv2code.com");
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}finally {
			factory.close();
		}

	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
