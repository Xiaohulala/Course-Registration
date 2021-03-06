package com.hu.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hu.dao.ReviewDao;
import com.hu.entity.Review;

@Service
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	ReviewDao reviewDao;
	
	@Override
	@Transactional
	public Set<Review> findReviewByCourseId(int courseId) {
		return reviewDao.findReviewByCourseId(courseId);
	}

	@Override
	@Transactional
	public void save(Review review) {
		reviewDao.save(review);

	}

}
