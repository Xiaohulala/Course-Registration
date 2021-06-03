package com.hu.configs;

import org.springframework.stereotype.Component;

import com.hu.dao.InstructorDao;
import com.hu.entity.Instructor;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		HttpSession session = request.getSession();
		session.setAttribute("username", userPrincipal.getUsername());
		
		if(roles.contains("ROLE_INSTRUCTOR")) 
			response.sendRedirect("instructors/");
		
		else response.sendRedirect("students/");

	}

}
