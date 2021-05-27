package com.hu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hu.user.CrmUser;


@Controller
@RequestMapping("/register")
public class RegController {
	
	@GetMapping("/showRegistrationForm")
	public String showRegPage(Model theModel) {
		theModel.addAttribute("crmUser", new CrmUser());
		return "registration-form";
	}
	

}