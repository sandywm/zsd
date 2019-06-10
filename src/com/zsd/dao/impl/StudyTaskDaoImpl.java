package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.StudyTaskDao;
import com.zsd.module.StudyTaskInfo;

@SuppressWarnings("unchecked")
public class StudyTaskDaoImpl implements StudyTaskDao{

	@Override
	public StudyTaskInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from StudyTaskInfo as st where st.id = "+id;
		List<StudyTaskInfo> stList = sess.createQuery(hql).list();
		if(stList.size() > 0){
			return stList.get(0);
		}
		return null;
	}
	
	@Override
	public void save(Session sess, StudyTaskInfo st) {
		// TODO Auto-generated method stub
		sess.save(st);
	}

	@Override
	public void update(Session sess, StudyTaskInfo st) {
		// TODO Auto-generated method stub
		sess.update(st);
	}

	@Override
	public List<StudyTaskInfo> findInfoByOpt(Session sess, Integer sLogId,
			String taskName) {
		// TODO Auto-generated method stub
		String hql = " from StudyTaskInfo as st where st.studyLogInfo.id = "+sLogId;
		if(!taskName.equals("")){
			hql += " and st.taskName = '"+taskName+"'";
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public StudyTaskInfo getLastInfoByLogId(Session sess, Integer sLogId) {
		// TODO Auto-generated method stub
		String hql = " from StudyTaskInfo as st where st.studyLogInfo.id = "+sLogId + " order by st.taskNum desc";
		List<StudyTaskInfo> stList = sess.createQuery(hql).setFirstResult(0).setMaxResults(1).list();
		if(stList.size() > 0){
			return stList.get(0);
		}
		return null;
	}


}
