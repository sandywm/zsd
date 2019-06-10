package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.StudyMapDao;
import com.zsd.module.StudyMapInfo;

@SuppressWarnings("unchecked")
public class StudyMapDaoImpl implements StudyMapDao{

	@Override
	public StudyMapInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from StudyMapInfo as sm where sm.id = "+id;
		List<StudyMapInfo> smList = sess.createQuery(hql).list();
		if(smList.size() > 0){
			return smList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, StudyMapInfo sm) {
		// TODO Auto-generated method stub
		sess.save(sm);
	}

	@Override
	public void update(Session sess, StudyMapInfo sm) {
		// TODO Auto-generated method stub
		sess.delete(sm);
	}

	@Override
	public List<StudyMapInfo> findInfoByOptions(Session sess, Integer loreId,
			Integer userId) {
		// TODO Auto-generated method stub
		String hql = " from StudyMapInfo as sm where sm.user.id = "+userId;
		if(loreId > 0){
			hql += " and sm.loreInfo.id = "+loreId;
		}
		return sess.createQuery(hql).list();
	}

}
