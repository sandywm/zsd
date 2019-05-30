package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.RelationZdResultDao;
import com.zsd.module.RelationZdResult;

@SuppressWarnings("unchecked")
public class RelationZdResultDaoImpl implements RelationZdResultDao{

	@Override
	public RelationZdResult getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from RelationZdResult as rzr where rzr.id = "+id;
		List<RelationZdResult> rzrList = sess.createQuery(hql).list();
		if(rzrList.size() > 0){
			return rzrList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, RelationZdResult rz) {
		// TODO Auto-generated method stub
		sess.save(rz);
	}

	@Override
	public void update(Session sess, RelationZdResult rz) {
		// TODO Auto-generated method stub
		sess.update(rz);
	}

	@Override
	public List<RelationZdResult> findInfoByOpt(Session sess,
			Integer studyLogId, Integer loreId) {
		// TODO Auto-generated method stub
		String hql = " from RelationZdResult as rzr where rzr.studyLogInfo.id = "+studyLogId + " and rzr.loreInfo.id = "+loreId;
		return sess.createQuery(hql).list();
	}

}
