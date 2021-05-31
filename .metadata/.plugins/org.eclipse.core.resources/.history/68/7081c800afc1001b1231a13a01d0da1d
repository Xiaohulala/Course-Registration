package com.hu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hu.entity.Instructor;

@Repository
public class InstructorDaoImpl implements InstructorDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Instructor findInstructorByFirstName(String theFirstName) {
		Session currentSession = sessionFactory.getCurrentSession();
			
		Query<Instructor> theQuery = currentSession.createQuery("from Instructor where firstName:=fName", Instructor.class);
		theQuery.setParameter("fName", theFirstName);
		Instructor theInstructor = null;
		try {
			theInstructor = theQuery.getSingleResult();
		} catch(Exception e) {
			theInstructor = null;
		}
		return theInstructor;
	}

	@Override
	public Instructor findInstructorByLastName(String theLastName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Instructor> theQuery = currentSession.createQuery("From Instructor where lastName:=lName", Instructor.class);
		Instructor theInstructor = null;
		try {
			theInstructor = theQuery.getSingleResult();
		} catch(Exception e) {
			theInstructor =  null;
		}
		return theInstructor;
	}

	@Override
	public void save(Instructor theInstructor) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theInstructor);
	}

}
