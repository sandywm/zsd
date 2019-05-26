package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.StuSubjectEduDao;
import com.zsd.module.StuSubjectEduInfo;

@SuppressWarnings("unchecked")
public class StuSubjectEduDaoImpl implements StuSubjectEduDao{

	@Override
	public StuSubjectEduInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from StuSubjectEduInfo as sse where sse.id = "+id;
		List<StuSubjectEduInfo> sseList = sess.createQuery(hql).list();
		if(sseList.size() > 0){
			return sseList.get(0);
		}
		return null;
	}

	@Override
	public void add(Session sess, StuSubjectEduInfo sse) {
		// TODO Auto-generated method stub
		sess.save(sse);
	}

	@Override
	public void update(Session sess, StuSubjectEduInfo sse) {
		// TODO Auto-generated method stub
		sess.update(sse);
	}

	@Override
	public List<StuSubjectEduInfo> findInfoByOpt(Session sess, Integer stuId,
			Integer subId) {
		// TODO Auto-generated method stub
		String hql = " from StuSubjectEduInfo as sse where sse.user.id = "+stuId;
		hql += " and sse.subject.id = "+subId + " order by sse.education.id asc";
		return sess.createQuery(hql).list();
	}

}
