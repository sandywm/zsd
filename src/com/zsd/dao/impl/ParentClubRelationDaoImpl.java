package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.ParentClubRelationDao;
import com.zsd.module.ParentClubRelationInfo;

public class ParentClubRelationDaoImpl implements ParentClubRelationDao {

	@Override
	public ParentClubRelationInfo get(Session sess, int id) {
		
		return (ParentClubRelationInfo) sess.load(ParentClubRelationInfo.class, id);
	}

	@Override
	public void save(Session sess, ParentClubRelationInfo pcrInfo) {
		 sess.save(pcrInfo);
	}

	@Override
	public void delete(Session sess, ParentClubRelationInfo pcrInfo) {
		sess.delete(pcrInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, ParentClubRelationInfo pcrInfo) {
		sess.update(pcrInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentClubRelationInfo> findInfoByPcrId(Session sess,
			Integer pcrId) {
		String hql=" from ParentClubRelationInfo as pcr where pcr.parentClubInfo.id= "+pcrId;
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ParentClubRelationInfo> findInfoByUserId(Session sess,
			Integer userId) {
		String hql=" from ParentClubRelationInfo as pcr where pcr.user.id="+userId;
		return sess.createQuery(hql).list();
	}

}
