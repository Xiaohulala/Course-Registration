package com.hu.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hu.entity.User;
import com.hu.user.CrmUser;

public interface UserService extends UserDetailsService{
	User findByUserName(String userName);
	void save(CrmUser crmUser);
}
