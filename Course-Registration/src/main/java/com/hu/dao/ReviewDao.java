package com.hu.dao;

import java.util.Set;

import com.hu.entity.Review;
import com.hu.entity.Student;

public interface ReviewDao {
	public Set<Review> findReviewByCourseId(int courseId);
	public void save(Review review);
}
