package com.hu.controllers;

import java.util.List;

import javax.servlet.http.Cookie;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hu.dao.CourseDao;
import com.hu.dao.InstructorDao;
import com.hu.entity.Course;
import com.hu.entity.Instructor;

@Controller
@RequestMapping("/instructors")
@SessionAttributes("username")
public class InstructorController {
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private InstructorDao instructorDao;
	
	@GetMapping("/")
	public String showInstructorsHome(Model theModel, HttpServletRequest request, ModelMap modelMap, @ModelAttribute String userName) {
		Cookie[] arr = request.getCookies();
		userName = null;
		for(Cookie c : arr)
			if(c.getName().equals("username"))
				userName = c.getValue();
		List<Course> theCourses = courseDao.getCourses(instructorDao.findInstructorByUserName(userName)) ;
		
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
		theCourse.setInstructor(instructorDao.findInstructorByUserName(useName));
		System.out.println("SDADSDADASDASDASDASDASDA: " + theCourse);
		courseDao.save(theCourse);
		
		return "redirect:/instructors/";
	}
	
	@GetMapping("/delete")
	public String deleteCourse(@RequestParam("courseId") int theId) {
		courseDao.deleteCourse(theId);
		return "redirect:/instructors/";
	}
}