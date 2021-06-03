package com.hu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hu.dao.CourseDao;
import com.hu.entity.Course;
import com.hu.entity.Instructor;

@Service
public class CourseServiceImpl implements CourseService{
	@Autowired
	private CourseDao courseDao;
	
	@Transactional
	@Override
	public List<Course> getCourses(Instructor theInstructor) {
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
	public List<Course> searchCourses(String searchName) {
		return courseDao.searchCourses(searchName);
	}

	@Override
	@Transactional
	public List<Course> getStudentCourse(String userName) {
		return courseDao.getStudentCourse(userName);
	}

}
