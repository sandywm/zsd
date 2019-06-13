package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.StudyDetailDao;
import com.zsd.module.StudyDetailInfo;

@SuppressWarnings("unchecked")
public class StudyDetailDaoImpl implements StudyDetailDao{

	@Override
	public StudyDetailInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hq =" from StudyDetailInfo as sd where sd.id = "+id;
		List<StudyDetailInfo> sdList = sess.createQuery(hq).list();
		if(sdList.size() > 0){
			return sdList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, StudyDetailInfo sd) {
		// TODO Auto-generated method stub
		sess.save(sd);
	}

	@Override
	public void delete(Session sess, StudyDetailInfo sd) {
		// TODO Auto-generated method stub
		sess.delete(sd);
	}

	@Override
	public void update(Session sess, StudyDetailInfo sd) {
		// TODO Auto-generated method stub
		sess.update(sd);
	}

	@Override
	public List<StudyDetailInfo> findInfoByLogId(Session sess,
			Integer studyLogId) {
		// TODO Auto-generated method stub
		String hql =" from StudyDetailInfo as sd where sd.studyLogInfo.id = "+studyLogId;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<StudyDetailInfo> findCurrentRightInfoByLogId(Session sess,
			Integer studyLogId, Integer loreId, String loreTypeName) {
		// TODO Auto-generated method stub
		String hql = " from StudyDetailInfo as sd where sd.studyLogInfo.id = "+studyLogId + " and sd.loreQuestion.loreTypeName = '"+loreTypeName+"' and sd.result = 1 and sd.loreInfo.id = "+loreId;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<StudyDetailInfo> findLastInfoByLogId(Session sess,
			Integer studyLogId, Integer loreId, String loreTypeName) {
		// TODO Auto-generated method stub
		String hql = " from StudyDetailInfo as sd where sd.studyLogInfo.id = "+studyLogId;
		if(loreId > 0){
			hql += " and sd.loreInfo.id = "+loreId; 
		}
		if(!loreTypeName.equals("")){
			hql += " and sd.loreQuestion.loreTypeName = '"+loreTypeName+"'";
		}
		hql += " order by sd.id desc";
		return sess.createQuery(hql).setFirstResult(0).setMaxResults(1).list();
	}

	@Override
	public List<StudyDetailInfo> findPretRightInfoByLogId(Session sess,
			Integer studyLogId, Integer loreId, String loreTypeName,
			Integer completeTimes) {
		// TODO Auto-generated method stub
		String hql = " from StudyDetailInfo as sd where sd.studyLogInfo.id = "+studyLogId + " and sd.loreQuestion.loreTypeName = '"+loreTypeName+"' and sd.result = 1 and sd.loreInfo.id = "+loreId + " and sd.completeTimes != "+completeTimes;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<StudyDetailInfo> findInfoByOpt(Session sess,
			Integer studyLogId, Integer loreId, String loreTypeName) {
		// TODO Auto-generated method stub
		String hql = " from StudyDetailInfo as sd where sd.studyLogInfo.id = "+studyLogId;
		if(loreId > 0){
			hql += " and sd.loreInfo.id = "+loreId; 
		}
		if(!loreTypeName.equals("")){
			hql += " and sd.loreQuestion.loreTypeName = '"+loreTypeName+"'";
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public List<StudyDetailInfo> findLastInfoByOpt(Session sess,
			Integer studyLogId, Integer loreId, String loreTypeName,
			Integer completeTimes) {
		// TODO Auto-generated method stub
		String hql = " from StudyDetailInfo as sd where sd.studyLogInfo.id = "+studyLogId + " and sd.loreQuestion.loreTypeName = '"+loreTypeName+"' and sd.loreInfo.id = "+loreId + " and sd.completeTimes = "+completeTimes;
		return sess.createQuery(hql).list();
	}

	@Override
	public boolean checkSuccCompleteFlag(Session sess, Integer studyLogId,
			Integer lqId, String currDate) {
		// TODO Auto-generated method stub
		String hql = " from StudyDetailInfo as sd where sd.studyLogInfo.id = "+studyLogId + " and sd.loreQuestion.id = "+lqId+" and sd.result = 1 and substring(sd.addTime,1,10) = '"+currDate+"'";
		List<StudyDetailInfo> l = sess.createQuery(hql).list();
		if(l.size() == 0){//没做过或者错误
			return false;
		}
		return true;
	}

	@Override
	public List<StudyDetailInfo> findInfoByOpt(Session sess,
			Integer studyLogId, Integer lqId) {
		// TODO Auto-generated method stub
		String hql = " from StudyDetailInfo as sd where sd.studyLogInfo.id = "+studyLogId + " and sd.loreQuestion.id = " + lqId;
		return sess.createQuery(hql).list();
	}

}
