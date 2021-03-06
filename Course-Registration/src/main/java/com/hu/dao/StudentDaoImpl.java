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
	public void save(Student theStudent) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theStudent);
	}

	@Override
	public int findIdByUsername(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Student> theQuery = currentSession.createQuery("from Student where email=:username", Student.class);
		theQuery.setParameter("username", username);
		Student student = theQuery.getSingleResult();
		return student.getId();
	}

	@Override
	public Student findStudentByUsername(String username) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Student> theQuery = currentSession.createQuery("from Student where email=:username", Student.class);
		theQuery.setParameter("username", username);
		return theQuery.getSingleResult();
	}



}
