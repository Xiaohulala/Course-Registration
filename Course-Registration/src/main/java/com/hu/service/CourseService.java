package com.hu.service;

import java.util.List;

import com.hu.entity.Course;
import com.hu.entity.Instructor;

public interface CourseService {
	public List<Course> getCourses(Instructor theInstructor);
	void save(Course theCourse);
	void deleteCourse(int courseId);
	public List<Course> searchCourses(String searchName);
	public List<Course> getStudentCourse(String userName);
}