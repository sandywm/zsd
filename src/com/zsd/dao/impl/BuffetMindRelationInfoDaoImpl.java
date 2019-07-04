package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.BuffetMindRelationInfoDao;
import com.zsd.module.BuffetMindRelationInfo;

public class BuffetMindRelationInfoDaoImpl implements BuffetMindRelationInfoDao {

	@Override
	public BuffetMindRelationInfo get(Session sess, int id) {
		
		return (BuffetMindRelationInfo) sess.load(BuffetMindRelationInfo.class, id);
	}

	@Override
	public void save(Session sess, BuffetMindRelationInfo bmrInfo) {
		sess.save(bmrInfo);
	}

	@Override
	public void delete(Session sess, BuffetMindRelationInfo bmrInfo) {
		sess.delete(bmrInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));

	}

	@Override
	public void update(Session sess, BuffetMindRelationInfo bmrInfo) {
		sess.update(bmrInfo);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuffetMindRelationInfo> findBMRInfoByBuffetQueId(Session sess,
			Integer bqId) {
		String hql=" from  BuffetMindRelationInfo as bmr where bmr.buffetQueInfo.id ="+bqId;
		return sess.createQuery(hql).list();
	}

}
