package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.GuideStudyDao;
import com.zsd.module.GuideStudyInfo;

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
		
		return null;
	}

	@Override
	public Integer getCountByOpt(Session sess, Integer stuId,
			Integer guideStatus, Integer guideUserId, String sDate, String eDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
