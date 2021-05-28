package com.hu.service;

import org.springframework.beans.factory.annotation.Autowired;
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
		
		//role
		Authority role = new Authority();
		role.setRole(crmUser.getRole());
		roleDao.save(role);
		
		// instructor
		if(crmUser.getRole().equals("Instructor")) {
			Instructor instructor = new Instructor();
			instructor.setFirstName(crmUser.getFirstName());
			instructor.setLastName(crmUser.getLastName());
			instructor.setEmail(crmUser.getUserName());
			instructorDao.save(instructor);
		}
		else {
			Student student = new Student();
			student.setFirstName(crmUser.getFirstName());
			student.setLastName(crmUser.getLastName());
			student.setEmail(crmUser.getUserName());
			studentDao.save(student);
		}
		
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}



}
