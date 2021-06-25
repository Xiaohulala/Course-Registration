package com.hu.service;

import com.hu.entity.Student;

public interface StudentService {
	public int findIdByUsername(String username);
	void save(Student student);
	public Student findStudentByUsername(String username);
}
