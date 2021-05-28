package com.hu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reviews")
public class Review {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="rating")
	private double rating;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="course_id")
	private Integer courseId;
	
	@Column(name="student_id")
	private Integer studentId;

}
