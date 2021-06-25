package com.hu.dao;

import com.hu.entity.Course;
import com.hu.entity.Student;

public interface StudentDao {
	public int findIdByUsername(String theFirstName);
	void save(Student student);
	public Student findStudentByUsername(String username);

}
