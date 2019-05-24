package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.UserClassInfoDao;
import com.zsd.module.UserClassInfo;

@SuppressWarnings("unchecked")
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

	@Override
	public List<UserClassInfo> findClassInfo(Session sess) {
		String hql = " from UserClassInfo as uci";
		return sess.createQuery(hql).list();
	}

	@Override
	public List<UserClassInfo> findUcInfoByUserId(Session sess, Integer userId) {
		String hql = " from UserClassInfo as uci where uci.user.id="+userId;
		return sess.createQuery(hql).list();
	}

	@Override
	public UserClassInfo getEntityByOpt(Session sess, Integer userId,
			Integer roleId) {
		// TODO Auto-generated method stub
		String hql = " from UserClassInfo as uci where uci.user.id="+userId;
		hql += " and uci.roleInfo.id = "+roleId;
		List<UserClassInfo> ucList = sess.createQuery(hql).list();
		if(ucList.size() > 0){
			return ucList.get(0);
		}
		return null;
	}



}
