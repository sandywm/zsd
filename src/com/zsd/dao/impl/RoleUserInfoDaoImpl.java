package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.RoleUserInfoDao;
import com.zsd.module.RoleUserInfo;

public class RoleUserInfoDaoImpl implements RoleUserInfoDao {

	@Override
	public RoleUserInfo get(Session sess, int id) {
		return (RoleUserInfo) sess.load(RoleUserInfo.class, id);
	}

	@Override
	public void save(Session sess, RoleUserInfo ruInfo) {
		sess.save(ruInfo);
	}

	@Override
	public void delete(Session sess, RoleUserInfo ruInfo) {
		sess.delete(ruInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
		
	}

	@Override
	public void update(Session sess, RoleUserInfo ruInfo) {
		sess.update(ruInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleUserInfo> findUserRoleInfo(Session sess, Integer userId) {
		String hql = " from RoleUserInfo as ru where ru.user.id ="+ userId;
		return sess.createQuery(hql).list();
	}

}
