package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.HwTraceStudyLogDao;
import com.zsd.module.HwTraceStudyLogInfo;

@SuppressWarnings("unchecked")
public class HwTraceStudyLogDaoImpl implements HwTraceStudyLogDao{

	@Override
	public HwTraceStudyLogInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from HwTraceStudyLogInfo as hwLog where hwLog.id = "+id;
		List<HwTraceStudyLogInfo> l = sess.createQuery(hql).list();
		if(l.size() > 0){
			return l.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, HwTraceStudyLogInfo sl) {
		// TODO Auto-generated method stub
		sess.save(sl);
	}

	@Override
	public void update(Session sess, HwTraceStudyLogInfo sl) {
		// TODO Auto-generated method stub
		sess.update(sl);
	}

	@Override
	public HwTraceStudyLogInfo getEntityByTjId(Session sess, Integer tjId) {
		// TODO Auto-generated method stub
		String hql = " from HwTraceStudyLogInfo as hwLog where hwLog.hwStudyTjInfo.id = "+tjId;
		List<HwTraceStudyLogInfo> l = sess.createQuery(hql).list();
		if(l.size() > 0){
			return l.get(0);
		}
		return null;
	}

}
