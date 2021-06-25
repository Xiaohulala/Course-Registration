package com.hu.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hu.entity.Course;
import com.hu.entity.Instructor;
import com.hu.entity.Student;

@Repository
public class CourseDaoImpl implements CourseDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Set<Course> getCourses(Instructor theInstructor) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Course> theQuery = currentSession.createQuery("from Course c where instructor=:ins", Course.class);
		theQuery.setParameter("ins", theInstructor);	
		Set<Course> sets = new HashSet<>();
		try {
			List<Course> courses = theQuery.getResultList();
			for(Course c : courses) 
				sets.add(c);
			
		} catch(Exception e) {
		}
		return sets;
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
	public Set<Course> searchCourses(String searchName) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query theQuery = null;
		if(searchName != null && searchName.trim().length() > 0) {
			theQuery = currentSession.createQuery("from Course where lower(name) like :searchName", Course.class);
			theQuery.setParameter("searchName", "%" + searchName.toLowerCase() + "%");
		} else {
			theQuery = currentSession.createQuery("from Course", Course.class);
		}
		List<Course> courses = theQuery.getResultList();
		Set<Course> sets = new HashSet<>();
		for(Course c : courses) 
			sets.add(c);
		return sets;
	}

	@Override
	public Set<Course> getStudentCourse(int id) {
		Session currentSession = sessionFactory.getCurrentSession();
		Student student = currentSession.get(Student.class, id);
		return student.getCourses();
	}

	@Override
	public Course getCoursebyId(int courseId) {
		Session currentSession = sessionFactory.getCurrentSession();
		return currentSession.get(Course.class, courseId);
	}
	
	@Override
	public void addCourseByStudent(Course course, Student student) {
		Session currentSession = sessionFactory.getCurrentSession();
		course.addStudent(student);
		currentSession.saveOrUpdate(course);
	}

	@Override
	public void deleteStudentFromCourse(Student student, Course course) {
		Session currentSession = sessionFactory.getCurrentSession();
		course.deleteStudent(student);
		currentSession.saveOrUpdate(course);
	}

}
