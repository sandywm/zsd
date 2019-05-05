package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.ActRoleInfoDao;
import com.zsd.module.ActRoleInfo;

@SuppressWarnings("unchecked")
public class ActRoleInfoDaoImpl implements ActRoleInfoDao{

	@Override
	public ActRoleInfo get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (ActRoleInfo) sess.load(ActRoleInfo.class, id);
	}

	@Override
	public void save(Session sess, ActRoleInfo ar) {
		// TODO Auto-generated method stub
		sess.save(ar);
	}

	@Override
	public void delete(Session sess, ActRoleInfo ar) {
		// TODO Auto-generated method stub
		sess.delete(ar);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public List<ActRoleInfo> findInfoByOpt(Session sess, Integer roleId,
			Integer actId) {
		// TODO Auto-generated method stub
		String hql =" from ActRoleInfo as ar where 1=1";
		if(roleId > 0){
			hql += " and ar.roleInfo.id = "+roleId;
		}
		if(actId > 0){
			hql += " and ar.moduleActionInfo.id = "+actId;
		}
		return sess.createQuery(hql).list();
	}

}
