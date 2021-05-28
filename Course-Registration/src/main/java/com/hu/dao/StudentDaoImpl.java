package com.hu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hu.entity.Student;

@Repository
public class StudentDaoImpl implements StudentDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Student findStudentByFirstName(String theFirstName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Student> theQuery = currentSession.createQuery("from Student where firstName=:fName", Student.class);
		theQuery.setParameter("fName", theFirstName);
		Student theStudent = null;
		try {
			theStudent = theQuery.getSingleResult();
		} catch(Exception e) {
			theStudent = null;
		}
		
		return theStudent;
	}

	@Override
	public Student findStudentByLastName(String theLastName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Student> theQuery = currentSession.createQuery("from Student where lastName=:lName", Student.class);
		theQuery.setParameter("lName", theLastName);
		Student theStudent = null;
		try {
			theStudent = theQuery.getSingleResult();
		} catch(Exception e) {
			theStudent = null;
		}
		return theStudent;
	}

	@Override
	public void save(Student theStudent) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theStudent);
	}

}
