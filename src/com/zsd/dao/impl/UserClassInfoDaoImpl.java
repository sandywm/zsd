package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.UserClassInfoDao;
import com.zsd.module.UserClassInfo;

public class UserClassInfoDaoImpl implements UserClassInfoDao {

	@Override
	public UserClassInfo get(Session sess, int id) {
		return (UserClassInfo) sess.load(UserClassInfo.class, id);
	}

	@Override
	public void save(Session sess, UserClassInfo userClassInfo) {
		sess.save(userClassInfo);

	}

	@Override
	public void delete(Session sess, UserClassInfo userClassInfo) {
		sess.delete(userClassInfo);

	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));

	}

	@Override
	public void update(Session sess, UserClassInfo userClassInfo) {
		sess.update(userClassInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserClassInfo> findClassInfo(Session sess) {
		String hql = " from UserClassInfo as uci";
		return sess.createQuery(hql).list();
	}

}
