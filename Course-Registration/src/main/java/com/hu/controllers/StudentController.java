package com.hu.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hu.entity.Course;
import com.hu.entity.Review;
import com.hu.entity.Student;
import com.hu.service.CourseService;
import com.hu.service.ReviewService;
import com.hu.service.StudentService;

@Controller
@RequestMapping("/students")
@SessionAttributes("username")
public class StudentController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/")
	public String showStudentsHome(Model theModel, HttpServletRequest request, ModelMap modelMap) {
			String userName = (String) request.getSession().getAttribute("username");
			Set<Course> allCourses = courseService.searchCourses(null);
			theModel.addAttribute("courses", allCourses);
			modelMap.addAttribute("username", userName);
		return "students-home";
	}
	
	@GetMapping("/search")
	public String searchCourse(@RequestParam("theSearchName") String searchName, Model theModel) {
		Set<Course> courses = courseService.searchCourses(searchName);
		theModel.addAttribute("courses", courses);
		return "students-home";
	}
	
	@GetMapping("/myCourse")
	public String showMyCourse(Model theModel, ModelMap modelMap) {
		int id = studentService.findIdByUsername((String) modelMap.getAttribute("username"));
		Set<Course> theCourses = courseService.getStudentCourse(id);
		theModel.addAttribute("courses", theCourses);
		return "students-course";
	}
	
	@GetMapping("/dropout")
	public String dropout(@RequestParam("courseId") int courseId, ModelMap modelMap) {
		String username = (String) modelMap.getAttribute("username");
		Student student = studentService.findStudentByUsername(username);
		Course course = courseService.getCoursebyId(courseId);
		courseService.deleteStudentFromCourse(student, course);
		return "redirect:/students/myCourse";
	}
	
	@GetMapping("/enroll")
	public String enroll(@RequestParam("courseId") int courseId, ModelMap modelMap) {
		String username = (String) modelMap.getAttribute("username");
		Student student = studentService.findStudentByUsername(username);
		Course course = courseService.getCoursebyId(courseId);
		if(course.getStudents().contains(student))
			return "redirect:/students/";		
		courseService.addCourseByStudent(course, student);
		return "redirect:/students/";
	}
	
	@GetMapping("/mySearch")
	public String searchMyCourse(@RequestParam("theSearchName") String searchName, Model theModel, ModelMap modelMap) {
		Set<Course> courses =  courseService.searchCourses(searchName);
		Set<Course> myCourses = new HashSet<>();
		
		boolean add = false;
		for(Course c : courses) {
			for(Student s : c.getStudents()) {
				if(s.getEmail().equals(modelMap.getAttribute("username"))) {
					add = true;
					break;
				}
			}
			if(add == true) {
				myCourses.add(c);
				add = false;
			}
		}
		theModel.addAttribute("courses", myCourses);
		return "students-course";
	}
	
	@GetMapping("/rate")
	public String showRateForm(@RequestParam("courseId") int courseId, Model model) {
		model.addAttribute("review", new Review());	
		model.addAttribute("courseId", courseId);
		model.addAttribute("reviews", reviewService.findReviewByCourseId(courseId));
		return "student-rate-course";
	}
	
	@GetMapping("/reviews")
	public String showReviewFrom(@RequestParam("courseId") int courseId, Model model) {
		Set<Review> res = reviewService.findReviewByCourseId(courseId);
		model.addAttribute("reviews", res);
		model.addAttribute("courseId", courseId);
		return "student-course-review";
	}
	
	@GetMapping("/checkReviews")
	public String showCheckReviewFrom(@RequestParam("courseId") int courseId, Model model) {
		Set<Review> res = reviewService.findReviewByCourseId(courseId);
		model.addAttribute("reviews", res);
		model.addAttribute("courseId", courseId);
		return "student-course-check-review";
	}
	
	@PostMapping("/processReview")
	public String showReviewConfirm(@Valid @ModelAttribute("review") Review review, BindingResult theBindingResult, 
									Model model, ModelMap modelMap
									) {
		if(theBindingResult.hasErrors())  
			return "student-rate-course";
		
		Student student = studentService.findStudentByUsername((String) modelMap.getAttribute("username"));
		
		Set<Review> reviews = reviewService.findReviewByCourseId(review.getCourseId());
		if(reviews != null) {
			for(Review r : reviews) {
				if(r.getStudent().getId() == student.getId()) {
					model.addAttribute("reviewError", "You already rated this course");
					model.addAttribute("courseId", review.getCourseId());
					return "student-rate-course";
				}
			}
		}
		review.setStudent(student);
		reviewService.save(review);
		return "redirect:/students/myCourse";
	}
}
