package com.hu.dao;

import com.hu.entity.Authority;

public interface RoleDao {
	public Authority findRoleByName(String theRoleName);
	void save(Authority role);
}
