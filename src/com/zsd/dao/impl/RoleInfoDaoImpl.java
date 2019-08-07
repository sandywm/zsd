package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.RoleInfoDao;
import com.zsd.module.RoleInfo;

@SuppressWarnings("unchecked")
public class RoleInfoDaoImpl implements	RoleInfoDao {

	@Override
	public RoleInfo get(Session sess, int id) {
		
		return (RoleInfo) sess.load(RoleInfo.class, id);
	}

	@Override
	public void save(Session sess, RoleInfo roleInfo) {
		sess.save(roleInfo);
	}

	@Override
	public void delete(Session sess, RoleInfo roleInfo) {
		sess.delete(roleInfo);
		
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
		
	}

	@Override
	public void update(Session sess, RoleInfo roleInfo) {
		sess.update(roleInfo);
		
	}

	@Override
	public List<RoleInfo> findRoleInfo(Session sess) {
		String hql = " from RoleInfo as r";
		return sess.createQuery(hql).list();
	}

	@Override
	public List<RoleInfo> findRoleInfo(Session sess, String roleName) {
		String hql = " from RoleInfo as r where r.roleName = '"+roleName+"'";
		return sess.createQuery(hql).list();
	}

	@Override
	public RoleInfo getEntityById(Session sess, int roleId) {
		// TODO Auto-generated method stub
		String hql = " from RoleInfo as r where r.id = " + roleId;
		List<RoleInfo> rList = sess.createQuery(hql).list();
		if(rList.size() > 0){
			return rList.get(0);
		}
		return null;
	}

}
