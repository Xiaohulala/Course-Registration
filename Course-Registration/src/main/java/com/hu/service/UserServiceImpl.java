package com.hu.service;

import java.awt.List;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hu.dao.InstructorDao;
import com.hu.dao.RoleDao;
import com.hu.dao.StudentDao;
import com.hu.dao.UserDao;
import com.hu.entity.Authority;
import com.hu.entity.Instructor;
import com.hu.entity.Student;
import com.hu.entity.User;
import com.hu.user.CrmUser;

@Service
public class UserServiceImpl implements UserService{
	
	// need to inject user DAO
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private InstructorDao instructorDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public User findByUserName(String userName) {
		// check the database if the user already exists
		return userDao.findByUserName(userName);
	}

	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		//assign user details to the user object
		User user = new User();
		user.setUserName(crmUser.getUserName());
		user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
		userDao.save(user);
		
		Instructor instructor = new Instructor();
		Student student = new Student();

		// instructor
		if(crmUser.getRole().equals("ROLE_INSTRUCTOR")) {
			instructor.setFirstName(crmUser.getFirstName());
			instructor.setLastName(crmUser.getLastName());
			instructor.setEmail(crmUser.getUserName());
			instructorDao.save(instructor);
		}
		else {
			student.setFirstName(crmUser.getFirstName());
			student.setLastName(crmUser.getLastName());
			student.setEmail(crmUser.getUserName());
			studentDao.save(student);
		}
		//role
		Authority role = new Authority();
		role.setRole(crmUser.getRole());
		role.setUser(user);
		if(crmUser.getRole().equals("ROLE_INSTRUCTOR"))
			role.setInstructor(instructor);
		else role.setStudent(student);
		roleDao.save(role);
		
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userDao.findByUserName(userName);
		Authority role = roleDao.findRoleByName(userName);
		Collection<Authority> roles = (Collection<Authority>) new List ();
		roles.add(role);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), mapRolesToAuthorities(roles));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

}
