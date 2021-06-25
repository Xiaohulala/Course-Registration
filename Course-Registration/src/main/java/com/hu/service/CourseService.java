package com.hu.service;

import java.util.Set;

import com.hu.entity.Course;
import com.hu.entity.Instructor;
import com.hu.entity.Student;

public interface CourseService {
	public Set<Course> getCourses(Instructor theInstructor);
	void save(Course theCourse);
	void deleteCourse(int courseId);
	public Set<Course> searchCourses(String searchName);
	public Set<Course> getStudentCourse(int id);
	public Course getCoursebyId(int courseId);
	public void addCourseByStudent(Course course, Student student);
	public void deleteStudentFromCourse(Student student, Course course);

}
