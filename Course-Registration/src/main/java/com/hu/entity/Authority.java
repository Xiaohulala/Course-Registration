package com.hu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {
	@Id
	@Column(name = "username")
	private String userName;
	
	@Column(name = "authority")
	private String role;
	
	@Column(name = "instructor_id")
	private Integer instructorId;
	
	@Column(name = "student_id")
	private Integer studentId;


	// constructors
	public Authority() {}
	// only need for role
	public Authority(String role) {
		this.role = role;
	}
	
	// getters and setters
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getInstructorId() {
		return instructorId;
	}
	public void setInstructorId(Integer instructorId) {
		this.instructorId = instructorId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	// toString
	@Override
	public String toString() {
		return "Authority [userName=" + userName + ", role=" + role + ", instructorId=" + instructorId + ", studentId="
				+ studentId + "]";
	}
	
	
}