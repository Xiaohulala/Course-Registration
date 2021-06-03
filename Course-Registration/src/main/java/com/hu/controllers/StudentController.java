package com.hu.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hu.dao.CourseDao;
import com.hu.entity.Course;
import com.hu.service.CourseService;

@Controller
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping("/")
	public String showStudentsHome(Model theModel, HttpServletRequest request) {
			String userName = (String) request.getSession().getAttribute("username");
			List<Course> allCourses = courseService.searchCourses(null);
			theModel.addAttribute("courses", allCourses);
			theModel.addAttribute("username", userName);
		return "students-home";
	}
	
	@GetMapping("/search")
	public String searchCourse(@RequestParam("theSearchName") String searchName, Model theModel) {
		List<Course> courses = courseService.searchCourses(searchName);
		theModel.addAttribute("courses", courses);
		return "students-home";
	}
	
	@GetMapping("/myCourse")
	public String showMyCourse(Model theModel, @RequestParam("username") String userName) {
		List<Course> theCourses = courseService.getStudentCourse(userName);
		theModel.addAttribute("courses", theCourses);
		return "students-course";
	}
}
