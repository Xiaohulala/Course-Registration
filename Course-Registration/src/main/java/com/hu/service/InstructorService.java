package com.hu.service;

import com.hu.entity.Instructor;

public interface InstructorService {
	public Instructor findInstructorByUserName(String theuserName);
	void save(Instructor theInstructor);
}
