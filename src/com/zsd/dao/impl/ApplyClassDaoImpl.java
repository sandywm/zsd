package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.ApplyClassDao;
import com.zsd.module.ApplyClassInfo;

@SuppressWarnings("unchecked")
public class ApplyClassDaoImpl implements ApplyClassDao{

	@Override
	public ApplyClassInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from ApplyClassInfo as ac where ac.id = "+id;
		List<ApplyClassInfo> acList = sess.createQuery(hql).list();
		if(acList.size() > 0){
			return acList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, ApplyClassInfo ac) {
		// TODO Auto-generated method stub
		sess.save(ac);
	}

	@Override
	public void update(Session sess, ApplyClassInfo ac) {
		// TODO Auto-generated method stub
		sess.update(ac);
	}

	@Override
	public List<ApplyClassInfo> findInfoByOpt(Session sess, Integer userId,
			Integer validStatus) {
		// TODO Auto-generated method stub
		String hql = " from ApplyClassInfo as ac where ac.user.id = "+userId;
		if(validStatus.equals(0)){
			hql  += " and ac.cancelTime = ''";
		}else{
			hql  += " and ac.cancelTime != ''";
		}
		return sess.createQuery(hql).list();
	}

}
