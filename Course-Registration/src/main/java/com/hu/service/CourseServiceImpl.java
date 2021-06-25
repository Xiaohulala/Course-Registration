package com.hu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hu.dao.CourseDao;
import com.hu.entity.Course;
import com.hu.entity.Instructor;
import com.hu.entity.Student;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	private CourseDao courseDao;
	
	@Transactional
	@Override
	public Set<Course> getCourses(Instructor theInstructor) {
		return courseDao.getCourses(theInstructor);
	}

	@Override
	@Transactional
	public void save(Course theCourse) {
		courseDao.save(theCourse);
	}

	@Override
	@Transactional
	public void deleteCourse(int courseId) {
		courseDao.deleteCourse(courseId);
	}

	@Override
	@Transactional
	public Set<Course> searchCourses(String searchName) {
		return courseDao.searchCourses(searchName);
	}

	@Override
	@Transactional
	public Set<Course> getStudentCourse(int id) {
		return courseDao.getStudentCourse(id);
	}

	@Override
	@Transactional
	public Course getCoursebyId(int courseId) {
		return courseDao.getCoursebyId(courseId);
	}

	@Override
	@Transactional
	public void addCourseByStudent(Course course, Student student) {
		courseDao.addCourseByStudent(course, student);
	}

	@Override
	@Transactional
	public void deleteStudentFromCourse(Student student, Course course) {
		courseDao.deleteStudentFromCourse(student, course);		
	}
	

}
