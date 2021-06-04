package com.hu.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hu.dao.CourseDao;
import com.hu.entity.Course;
import com.hu.service.CourseService;
import com.hu.service.StudentService;

@Controller
@RequestMapping("/students")
@SessionAttributes("username")
public class StudentController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/")
	public String showStudentsHome(Model theModel, HttpServletRequest request, ModelMap modelMap) {
			String userName = (String) request.getSession().getAttribute("username");
			List<Course> allCourses = courseService.searchCourses(null);
			theModel.addAttribute("courses", allCourses);
			modelMap.addAttribute("username", userName);
		return "students-home";
	}
	
	@GetMapping("/search")
	public String searchCourse(@RequestParam("theSearchName") String searchName, Model theModel) {
		List<Course> courses = courseService.searchCourses(searchName);
		theModel.addAttribute("courses", courses);
		return "students-home";
	}
	
	@GetMapping("/myCourse")
	public String showMyCourse(Model theModel, ModelMap modelMap) {
		int id = studentService.findIdByUsername((String) modelMap.getAttribute("username"));
		List<Course> theCourses = courseService.getStudentCourse(id);;
		theModel.addAttribute("courses", theCourses);
		return "students-course";
	}
	
	@GetMapping("/dropout")
	public String dropout() {
		return "redirect:/students/myCourse";
	}
}
