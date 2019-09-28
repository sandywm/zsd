package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.ParentClubDao;
import com.zsd.module.ParentClubInfo;

public class ParentClubDaoImpl implements ParentClubDao {

	@Override
	public ParentClubInfo get(Session sess, int id) {
		
		return (ParentClubInfo) sess.load(ParentClubInfo.class, id);
	}

	@Override
	public void save(Session sess, ParentClubInfo parenClubInfo) {
		sess.save(parenClubInfo);

	}

	@Override
	public void delete(Session sess, ParentClubInfo parenClubInfo) {
		sess.delete(parenClubInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, ParentClubInfo parenClubInfo) {
		sess.update(parenClubInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentClubInfo> findParentClubByuId(Session sess, Integer userId) {
		String hql=" from ParentClubInfo as pc  where pc.user.id =" + userId;
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentClubInfo> findParentClubBypcCode(Session sess,
			String clubCode) {
		String hql=" from  ParentClubInfo as pc  where pc.clubCode = '"+clubCode+"'";
		return sess.createQuery(hql).list();
	}

}
