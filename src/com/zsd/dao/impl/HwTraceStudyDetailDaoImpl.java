package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.HwTraceStudyDetailDao;
import com.zsd.module.HwTraceStudyDetailInfo;

@SuppressWarnings("unchecked")
public class HwTraceStudyDetailDaoImpl implements HwTraceStudyDetailDao{

	@Override
	public HwTraceStudyDetailInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from HwTraceStudyDetailInfo as sd where sd.id = "+id;
		List<HwTraceStudyDetailInfo> l = sess.createQuery(hql).list();
		if(l.size() > 0){
			return l.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, HwTraceStudyDetailInfo sd) {
		// TODO Auto-generated method stub
		sess.save(sd);
	}

	@Override
	public List<HwTraceStudyDetailInfo> findInfoByLogId(Session sess,
			Integer logId) {
		// TODO Auto-generated method stub
		String hql = " from HwTraceStudyDetailInfo as sd where sd.hwTraceStudyLogInfoid = "+logId;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<HwTraceStudyDetailInfo> findCurrentRightInfoByLogId(
			Session sess, Integer logId, Integer loreId, String loreTypeName) {
		// TODO Auto-generated method stub
		String hql = " from HwTraceStudyDetailInfo as sd where sd.hwTraceStudyLogInfo.id = "+logId + " and sd.result = 1";
		if(loreId > 0){
			hql += " and sd.loreInfo.id = "+loreId;
		}
		if(!loreTypeName.equals("")){
			hql += " and sd.loreQuestion.loreTypeName = '"+loreTypeName+"'";
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public List<HwTraceStudyDetailInfo> findLastInfoByLogId(Session sess,
			Integer logId) {
		// TODO Auto-generated method stub
		String hql = " from HwTraceStudyDetailInfo as sd where sd.hwTraceStudyLogInfo.id = "+logId + " order by sd.id desc";
		return sess.createQuery(hql).setFirstResult(0).setMaxResults(1).list();
	}

	@Override
	public List<HwTraceStudyDetailInfo> findLastInfoByOpt(Session sess,
			Integer logId, Integer loreId, String loreTypeName,
			Integer completeTimes) {
		// TODO Auto-generated method stub
		String hql = " from HwTraceStudyDetailInfo as sd where sd.hwTraceStudyLogInfo.id = "+logId;
		hql += " and sd.loreInfo.id = "+loreId + " and sd.loreQuestion.loreTypeName = '"+loreTypeName+"'";
		hql += " and sd.completeTimes = "+completeTimes;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<HwTraceStudyDetailInfo> findPretRightInfoByLogId(Session sess,
			Integer logId, Integer loreId, String loreTypeName,
			Integer completeTimes) {
		// TODO Auto-generated method stub
		String hql = " from HwTraceStudyDetailInfo as sd where sd.hwTraceStudyLogInfo.id = "+logId;
		hql += " and sd.loreInfo.id = "+loreId + " and sd.loreQuestion.loreTypeName = '"+loreTypeName+"'";
		hql += " and sd.completeTimes != "+completeTimes + " and sd.result = 1";
		return sess.createQuery(hql).list();
	}

	@Override
	public List<HwTraceStudyDetailInfo> findExistInfoByLogId(Session sess,
			Integer logId, Integer loreId, String loreTypeName) {
		// TODO Auto-generated method stub
		String hql = " from HwTraceStudyDetailInfo as sd where sd.hwTraceStudyLogInfo.id = "+logId;
		hql += " and sd.loreInfo.id = "+loreId + " and sd.loreQuestion.loreTypeName = '"+loreTypeName+"'";
		return sess.createQuery(hql).list();
	}

	@Override
	public Integer getQuestionNumberByOption(Session sess, Integer logId,
			Integer lqId) {
		// TODO Auto-generated method stub
		String hql = " from HwTraceStudyDetailInfo as sd where sd.hwTraceStudyLogInfo.id = "+logId;
		hql += " and sd.loreQuestion.id = "+lqId;
		return sess.createQuery(hql).list().size();
	}

}
