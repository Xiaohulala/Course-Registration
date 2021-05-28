package com.hu.dao;

import com.hu.entity.Student;

public interface StudentDao {
	public Student findStudentByFirstName(String theFirstName);
	public Student findStudentByLastName(String theLastName);
	void save(Student student);

}