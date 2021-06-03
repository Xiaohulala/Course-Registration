package com.hu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hu.dao.InstructorDao;
import com.hu.entity.Instructor;

@Service
public class InstructorServiceImpl implements InstructorService{

	@Autowired
	InstructorDao instructorDao;
	
	@Override
	@Transactional
	public Instructor findInstructorByUserName(String theUserName) {
		return instructorDao.findInstructorByUserName(theUserName);
		}

	@Override
	@Transactional
	public void save(Instructor theInstructor) {
		instructorDao.save(theInstructor);
	}


}
