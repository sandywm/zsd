package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.UserDao;
import com.zsd.module.Edition;
import com.zsd.module.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User get(Session sess, int id) {
		return  (User) sess.load(Edition.class, id);
	}

	@Override
	public void save(Session sess, User user) {
		sess.save(user);
	}

	@Override
	public void update(Session sess, User user) {
		sess.update(user);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findInfoByAccount(Session sess, String account) {
		String hql = " from User as u where u.userAccount='"+account+"'";
		return sess.createQuery(hql).list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findInfoByAccPwd(Session sess, String account,
			String password) {
		String hql = " from User as u where u.userAccount='"+account+"' and password='"+password+"'";
		return sess.createQuery(hql).list();
	}

}
