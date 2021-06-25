package com.hu.controllers;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


import com.hu.entity.Course;
import com.hu.service.CourseService;
import com.hu.service.InstructorService;

@Controller
@RequestMapping("/instructors")
@SessionAttributes("username")
public class InstructorController {
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private InstructorService instructorService;
	
	@GetMapping("/")
	public String showInstructorsHome(Model theModel, HttpServletRequest request, ModelMap modelMap) {
		String userName = (String) request.getSession().getAttribute("username");

		Set<Course> theCourses = courseService.getCourses(instructorService.findInstructorByUserName(userName)) ;
		
		theModel.addAttribute("courses", theCourses);
		modelMap.addAttribute("username", userName);
		
		return "instructors-home";
	}
	
	@GetMapping(value = "/showFormForAddCourseForInstructor")
	public String showFormForAddCourseForInstructors(Model theModel) {
		
		Course theCourse = new Course();
		theModel.addAttribute("course", theCourse);
		
		return "add-course-for-instructor";
	}
	
	@PostMapping("/saveCourse")
	public String saveCustomer(@ModelAttribute("course") Course theCourse, ModelMap modelMap) {
		
		String useName = (String) modelMap.get("username");
		theCourse.setInstructor(instructorService.findInstructorByUserName(useName));
		courseService.save(theCourse);
		
		return "redirect:/instructors/";
	}
	
	@GetMapping("/delete")
	public String deleteCourse(@RequestParam("courseId") int theId) {
		courseService.deleteCourse(theId);
		return "redirect:/instructors/";
	}
	
	@GetMapping("/search")
	public String searchCourse(@RequestParam("theSearchName") String searchName, Model theModel, ModelMap modelMap) {
		Set<Course> courses = courseService.searchCourses(searchName);
		Set<Course> myCourses = new HashSet<>();
		for(Course c : courses)
			if(c.getInstructorEmail().equals(modelMap.getAttribute("username")))
				myCourses.add(c);
		theModel.addAttribute("courses", myCourses);
		return "instructors-home";
	}
}
