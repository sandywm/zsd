package com.zsd.dao;

import javax.jms.Session;

import com.zsd.module.HwTraceStudyLogInfo;

public interface HwTraceStudyLogDao {

	HwTraceStudyLogInfo getEntityById(Session sess,Integer id);
	
	void save(Session sess,HwTraceStudyLogInfo sl);
	
	void update(Session sess,HwTraceStudyLogInfo sl);
	
	
}
