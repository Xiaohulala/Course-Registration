package com.hu.dao;

import com.hu.entity.Instructor;

public interface InstructorDao {
	public Instructor findInstructorByFirstName(String theFirstName);
	public Instructor findInstructorByLastName(String theLastName);
	void save(Instructor theInstructor);
}