package com.hu.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "username")
	private User user;
	
	@Column(name = "authority")
	private String role;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Student student;


	// constructors
	public Authority() {}
	// only need for role
	public Authority(String role) {
		this.role = role;
	}
	
	// getters and setters
	
	
	public String getRole() {
		return role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setRole(String role) {
		this.role = role;
	}
	

	public Instructor getInstructor() {
		return instructor;
	}
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	// toString
	@Override
	public String toString() {
		return "Authority [id=" + id + ", user=" + user + ", role=" + role + ", instructor=" + instructor + ", student="
				+ student + "]";
	}
	
	
	
	
	
}
