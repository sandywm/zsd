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
		String hq =" from StudyDetailInfo as sd where sd.studyLogInfo.id = "+studyLogId;
		return sess.createQuery(hq).list();
	}

}
