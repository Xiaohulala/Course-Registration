package com.hu.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hu.entity.User;
import com.hu.service.UserService;
import com.hu.user.CrmUser;


@Controller
@RequestMapping("/register")
public class RegController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/showRegistrationForm")
	public String showRegPage(Model theModel) {
		theModel.addAttribute("crmUser", new CrmUser());
		return "registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("crmUser") CrmUser theCrmUser,
										  BindingResult theBindingResult,
										  Model theModel) {
		
		// form validation
		if(theBindingResult.hasErrors()) return "registration-form";
		
		
		// check the database if user already exists
		User existing = userService.findByUserName(theCrmUser.getUserName());
		if(existing != null) {
			theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("registrationError", "User name already exists.");
			return "registration-form";
		}
		
		// create user account
		userService.save(theCrmUser);
		return "registration-confirmation";
	}

}
