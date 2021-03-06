package com.hu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hu.dao.StudentDao;
import com.hu.entity.Course;
import com.hu.entity.Student;

@Service
public class StudentServiceImpl implements StudentService{
	 
	@Autowired
	private StudentDao studentDao;
	
	@Override
	@Transactional
	public int findIdByUsername(String username) {
		return studentDao.findIdByUsername(username);
	}

	@Override
	@Transactional
	public void save(Student student) {
		studentDao.save(student);
	}

	@Override
	@Transactional
	public Student findStudentByUsername(String username) {
		return studentDao.findStudentByUsername(username);
	}



}
