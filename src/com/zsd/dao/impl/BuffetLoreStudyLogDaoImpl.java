package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.BuffetLoreStudyLogDao;
import com.zsd.module.BuffetLoreStudyLogInfo;

@SuppressWarnings("unchecked")
public class BuffetLoreStudyLogDaoImpl implements BuffetLoreStudyLogDao{

	@Override
	public BuffetLoreStudyLogInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql =" from BuffetLoreStudyLogInfo as blsl where blsl.id = "+id;
		List<BuffetLoreStudyLogInfo> list = sess.createQuery(hql).list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, BuffetLoreStudyLogInfo blsLog) {
		// TODO Auto-generated method stub
		sess.save(blsLog);
	}

	@Override
	public void update(Session sess, BuffetLoreStudyLogInfo blsLog) {
		// TODO Auto-generated method stub
		sess.update(blsLog);
	}

	@Override
	public BuffetLoreStudyLogInfo getInfoByBsdId(Session sess, Integer bsdId) {
		// TODO Auto-generated method stub
		String hql =" from BuffetLoreStudyLogInfo as blsl where blsl.buffetStudyDetailInfo.id = "+bsdId;
		List<BuffetLoreStudyLogInfo> list = sess.createQuery(hql).list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
