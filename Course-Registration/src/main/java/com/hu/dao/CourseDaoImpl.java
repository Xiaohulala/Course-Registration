package com.hu.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hu.entity.Course;
import com.hu.entity.Instructor;

@Repository
public class CourseDaoImpl implements CourseDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Course> getCourses(Instructor theInstructor) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Course> theQuery = currentSession.createQuery("from Course c where instructor=:ins", Course.class);
		theQuery.setParameter("ins", theInstructor);	
		List<Course> courses = null;
		try {
			courses = theQuery.getResultList();
		} catch(Exception e) {
			courses = null;
		}
		return courses;
	}

	@Override
	@Transactional
	public void save(Course theCourse) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(theCourse);
	}

	@Override
	@Transactional
	public void deleteCourse(int courseId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Course where id=:theId");
		theQuery.setParameter("theId", courseId);
		theQuery.executeUpdate();
		
	}

}