package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.BuffetLoreStudyDetailDao;
import com.zsd.module.BuffetLoreStudyDetailInfo;

@SuppressWarnings("unchecked")
public class BuffetLoreStudyDetailDaoImpl implements BuffetLoreStudyDetailDao{

	@Override
	public BuffetLoreStudyDetailInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from BuffetLoreStudyDetailInfo as blsd where blsd.id = "+id;
		List<BuffetLoreStudyDetailInfo> list = sess.createQuery(hql).list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, BuffetLoreStudyDetailInfo blsd) {
		// TODO Auto-generated method stub
		sess.save(blsd);
	}

	@Override
	public void update(Session sess, BuffetLoreStudyDetailInfo blsd) {
		// TODO Auto-generated method stub
		sess.update(blsd);
	}

	@Override
	public List<BuffetLoreStudyDetailInfo> findInfoByLogId(Session sess,
			Integer studyLogId) {
		// TODO Auto-generated method stub
		String hql = " from BuffetLoreStudyDetailInfo as blsd where blsd.buffetLoreStudyLogInfo.id = "+studyLogId;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<BuffetLoreStudyDetailInfo> findCurrentRightInfoByLogId(
			Session sess, Integer studyLogId, Integer loreId,String loreTypeName) {
		// TODO Auto-generated method stub
		String hql = " from BuffetLoreStudyDetailInfo as blsd where blsd.buffetLoreStudyLogInfo.id = "+studyLogId;
		hql += " and blsd.loreQuestion.loreTypeName = '"+loreTypeName+"' and blsd.result = 1 and blsd.loreInfo.id = "+loreId;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<BuffetLoreStudyDetailInfo> findPretRightInfoByLogId(
			Session sess, Integer studyLogId, Integer loreId,
			String loreTypeName, Integer completeTimes) {
		// TODO Auto-generated method stub
		String hql = " from BuffetLoreStudyDetailInfo as blsd where blsd.buffetLoreStudyLogInfo.id = "+studyLogId;
		hql += " and blsd.loreQuestion.loreTypeName = '"+loreTypeName+"' and sd.result = 1 and blsd.loreInfo.id = "+loreId + " and blsd.completeTimes != "+completeTimes;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<BuffetLoreStudyDetailInfo> findLastInfoByOpt(Session sess,
			Integer studyLogId, Integer loreId, String loreTypeName,
			Integer completeTimes) {
		// TODO Auto-generated method stub
		String hql = " from BuffetLoreStudyDetailInfo as blsd where blsd.buffetLoreStudyLogInfo.id = "+studyLogId;
		hql += " and blsd.loreInfo.id = "+loreId + " and sd.loreQuestion.loreTypeName='"+loreTypeName+"' and blsd.completeTimes = "+completeTimes;
		return sess.createQuery(hql).setFirstResult(0).setMaxResults(1).list();
	}

	@Override
	public List<BuffetLoreStudyDetailInfo> findLastInfoByLogId(Session sess,
			Integer studyLogId) {
		// TODO Auto-generated method stub
		String hql = " from BuffetLoreStudyDetailInfo as blsd where blsd.buffetLoreStudyLogInfo.id = "+studyLogId;
		return sess.createQuery(hql).setFirstResult(0).setMaxResults(1).list();
	}

	@Override
	public List<BuffetLoreStudyDetailInfo> findExistInfoByLogId(Session sess,
			Integer studyLogId, Integer loreId, String loreTypeName) {
		// TODO Auto-generated method stub
		String hql = " from BuffetLoreStudyDetailInfo as blsd where blsd.buffetLoreStudyLogInfo.id = "+studyLogId ;
		hql += " and blsd.loreQuestion.loreTypeName = '"+loreTypeName+"' and blsd.loreInfo.id = "+loreId;
		return sess.createQuery(hql).list();
	}

}
