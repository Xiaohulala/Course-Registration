package com.hu.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;

import com.hu.entity.Authority;


@Repository
public class RoleDaoImpl implements RoleDao{
	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Authority findRoleByName(String theRoleName) {
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using name
		Query<Authority> theQuery = currentSession.createQuery("from Authority where name=:roleName", Authority.class);
		theQuery.setParameter("roleName", theRoleName);
		Authority theRole = null;
		try {
			theRole = theQuery.getSingleResult();
		} catch(Exception e) {
			theRole = null;
		}
		return theRole;
	}

	@Override
	public void save(Authority role) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(role);
	}
	

}
