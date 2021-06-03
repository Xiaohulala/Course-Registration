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
	public void save(Course theCourse) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(theCourse);
	}

	@Override
	public void deleteCourse(int courseId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = currentSession.createQuery("delete from Course where id=:theId");
		theQuery.setParameter("theId", courseId);
		theQuery.executeUpdate();
		
	}

	@Override
	public List<Course> searchCourses(String searchName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = null;
		if(searchName != null && searchName.trim().length() > 0) {
			theQuery = currentSession.createQuery("from Course where lower(name) like :searchName", Course.class);
			theQuery.setParameter("searchName", "%" + searchName.toLowerCase() + "%");
		} else {
			theQuery = currentSession.createQuery("from Course", Course.class);
		}
		List<Course> res = theQuery.getResultList();
		return res;
	}

	@Override
	public List<Course> getStudentCourse(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

}
