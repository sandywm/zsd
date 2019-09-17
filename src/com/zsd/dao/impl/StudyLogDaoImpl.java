package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.StudyLogDao;
import com.zsd.module.StudyLogInfo;

@SuppressWarnings("unchecked")
public class StudyLogDaoImpl implements StudyLogDao{

	@Override
	public StudyLogInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from StudyLogInfo as sl where sl.id = "+id;
		List<StudyLogInfo> slList = sess.createQuery(hql).list();
		if(slList.size() > 0){
			return slList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, StudyLogInfo sl) {
		// TODO Auto-generated method stub
		sess.save(sl);
	}

	@Override
	public void update(Session sess, StudyLogInfo sl) {
		// TODO Auto-generated method stub
		sess.update(sl);
	}

	@Override
	public void delete(Session sess, StudyLogInfo sl) {
		// TODO Auto-generated method stub
		sess.delete(sl);
	}

	@Override
	public List<StudyLogInfo> findStuLogByOpt(Session sess, Integer userId,
			Integer subId, Integer logType, String sDate, String eDate) {
		// TODO Auto-generated method stub
		String hql = " from StudyLogInfo as sl where sl.user.id = "+userId;
		hql += " and sl.subject.id = "+subId + " and sl.logType = "+logType;
		hql += " and substring(sl.addTime,1,10) >= '"+sDate+"' and substring(sl.addTime,1,10) <= '"+eDate+"'";
		return sess.createQuery(hql).list();
	}

	@Override
	public List<StudyLogInfo> findLastStudyInfoByOpt(Session sess,
			Integer userId, Integer loreId,Integer logType) {
		// TODO Auto-generated method stub
		String hql = " from StudyLogInfo as sl where sl.user.id = "+userId;
		hql += " and sl.loreInfo.id = "+loreId+ " and sl.logType = "+logType;
		hql += " order by sl.id desc";
		return sess.createQuery(hql).setFirstResult(0).setMaxResults(1).list();
	}

	@Override
	public List<StudyLogInfo> findStuLogByOption(Session sess, Integer userId,
			Integer subId, Integer isfinish, Integer logType, String sDate,
			String eDate) {
		String hql = " from StudyLogInfo as sl where sl.user.id = "+userId;
		if(!subId.equals(0)){
			hql += " and sl.subject.id = "+subId ;
		}
		if(logType.equals(1) || logType.equals(2)){
			hql+=" and sl.logType = "+logType;
		}
		if(!isfinish.equals(0)){
			hql +=" and sl.isFinish="+isfinish;
		}
		if(!sDate.equals("")&& !eDate.equals("")){
			hql += " and substring(sl.addTime,1,10) >= '"+sDate+"' and substring(sl.addTime,1,10) <= '"+eDate+"'";
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public List<StudyLogInfo> findStuLogByOption(Session sess, Integer userId,Integer subId,
			String sDate, String eDate) {
		String hql = " from StudyLogInfo as sl where sl.isFinish = 2 and sl.subject.id="+subId;
			   if(!userId.equals(0)){
				   hql +=" and sl.user.id = "+userId;
			   }
			   if(!sDate.equals("")&& !eDate.equals("")){
				    hql += " and substring(sl.addTime,1,10) >= '"+sDate+"' and substring(sl.addTime,1,10) <= '"+eDate+"'";
			   }
		return sess.createQuery(hql).list();
	}

	@Override
	public List<StudyLogInfo> findStuLogByStu(Session sess,Integer stuId,Integer subId,Integer guideSta,String sDate, String eDate) {
	
		 String hql = " from StudyLogInfo as sl where sl.subject.id="+subId;
		
		   if(!stuId.equals(0)){
			   hql +=" and sl.user.id = "+stuId;
		   }
		   
		   if(guideSta.equals(1)){//未指导
			  hql +=" and sl.teaAssess=''"; 
		   }else if(guideSta.equals(2)){
			   hql+=" and sl.teaAssess != ''";
		   }
		   if(!sDate.equals("")&& !eDate.equals("")){
			    hql += " and substring(sl.addTime,1,10) >= '"+sDate+"' and substring(sl.addTime,1,10) <= '"+eDate+"'";
		   }
			return sess.createQuery(hql).list();
	}

}
