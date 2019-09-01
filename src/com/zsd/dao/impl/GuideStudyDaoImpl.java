package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.GuideStudyDao;
import com.zsd.module.GuideStudyInfo;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class GuideStudyDaoImpl implements GuideStudyDao{

	@Override
	public GuideStudyInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from GuideStudyInfo as gs where gs.id = "+id;
		List<GuideStudyInfo> l = sess.createQuery(hql).list();
		if(l.size() > 0){
			return l.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, GuideStudyInfo gs) {
		// TODO Auto-generated method stub
		sess.save(gs);
	}

	@Override
	public void update(Session sess, GuideStudyInfo gs) {
		// TODO Auto-generated method stub
		sess.update(gs);
	}

	@Override
	public List<GuideStudyInfo> findPageInfoByOpt(Session sess, Integer stuId,
			Integer guideStatus, Integer guideUserId, String sDate,
			String eDate, boolean pageFlag, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from GuideStudyInfo as gs where 1=1";
		if(stuId > 0){
			hql += " and gs.studyLogInfo.user.id = "+stuId;
		}
		if(guideStatus > 0){
			hql += " and gs.guideStatus = "+guideStatus;
		}
		if(guideUserId > 0){
			hql += " and gs.guideUserId = "+guideUserId;
		}
		if(!sDate.equals("") && !eDate.equals("")){
			hql += "  and substring(gs.addTaskDate,1,10) >= '"+sDate+"' and substring(gs.addTaskDate,1,10) <= '"+eDate+"'";
		}
		if(pageFlag){
			int offset = (pageNo - 1) * pageSize;
			if (offset < 0) {
				offset = 0;
			}
			return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, Integer stuId,
			Integer guideStatus, Integer guideUserId, String sDate, String eDate) {
		// TODO Auto-generated method stub
		String hql = "select count(gs.id) from GuideStudyInfo as gs where 1=1";
		if(stuId > 0){
			hql += " and gs.studyLogInfo.user.id = "+stuId;
		}
		if(guideStatus > 0){
			hql += " and gs.guideStatus = "+guideStatus;
		}
		if(guideUserId > 0){
			hql += " and gs.guideUserId = "+guideUserId;
		}
		if(!sDate.equals("") && !eDate.equals("")){
			hql += "  and substring(gs.addTaskDate,1,10) >= '"+sDate+"' and substring(gs.addTaskDate,1,10) <= '"+eDate+"'";
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

}
