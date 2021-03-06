package com.hu.entity;

import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="instructor_id")
	private Instructor instructor;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
	@JoinTable(
			name="course_student",
			joinColumns=@JoinColumn(name="course_id"),
			inverseJoinColumns=@JoinColumn(name="student_id")
			)
	private Set<Student> students;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="course_id")
	private Set<Review> reviews;
	
	// Constructor
	public Course() {}
	
	public Course(String name) {
		this.name = name;
	}

	// getters & setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstructor() {
		return instructor.getFirstName() + " " + instructor.getLastName();
	}
	public String getInstructorEmail() {
		return instructor.getEmail();
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	public double getRating() {
		if(reviews.size() == 0) return -1.0;
		double total = 0;
		for(Review r : reviews) {
			total += r.getRating();
		}
		System.out.print(total);	
		return Math.round((total / reviews.size()) * 100.0 / 100.0);
	}
	
	// add a convenience method
	public void addStudent(Student theStudent) {
		if(students == null) 
			students = new HashSet<>();
		students.add(theStudent);
	}
	
	public void deleteStudent(Student student) {
		System.out.println(students.remove(student));
	}
	
	public void addReview(Review review) {
		if(reviews == null) {
			reviews = new HashSet<>();
		}
		reviews.add(review);
	}

	//toString
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", instructor=" + instructor + ", students=" + students + "]";
	}

	

	
}
