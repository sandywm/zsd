package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.SysFeeDao;
import com.zsd.module.SysFeeInfo;

@SuppressWarnings("unchecked")
public class SysFeeDaoImpl implements SysFeeDao{

	SysFeeDao sfDao = null;
	@Override
	public SysFeeInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from SysFeeInfo as sf where sf.id = "+id;
		List<SysFeeInfo> l = sess.createQuery(hql).list();
		if(l.size() > 0){
			return l.get(0);
		}
		return null;
	}

	@Override
	public List<SysFeeInfo> findInfoByOpt(Session sess, Integer feeType,
			Integer schoolType, Integer showStatus) {
		// TODO Auto-generated method stub
		String hql = " from SysFeeInfo as sf where 1 = 1";
		if(feeType > 0){
			hql += " and sf.feeType = "+feeType;
		}
		if(schoolType > 0){
			hql += " and sf.schoolType = "+schoolType;
		}
		if(showStatus > 0){
			hql += " and sf.showStatus = "+showStatus;
		}
		return sess.createQuery(hql).list();
	}

}
