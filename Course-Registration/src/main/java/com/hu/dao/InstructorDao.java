package com.hu.dao;

import com.hu.entity.Instructor;

public interface InstructorDao {

	public Instructor findInstructorByUserName(String theuserName);
	void save(Instructor theInstructor);
}
