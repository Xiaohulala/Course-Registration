package com.hu.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hu.entity.Review;

@Repository
public class ReviewDaoImpl implements ReviewDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Set<Review> findReviewByCourseId(int courseId) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query<Review> theQuery = currentSession.createQuery("from Review where courseId=:id", Review.class);
		theQuery.setParameter("id", courseId);
		List<Review> reviews = new ArrayList<>();
		Set<Review> res = new HashSet<>();
		try {
			reviews = theQuery.getResultList();
			for(Review e : reviews)
				res.add(e);
		}catch(Exception e) {

		}
		return res;
	}

	@Override
	public void save(Review review) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.save(review);
	}

}
