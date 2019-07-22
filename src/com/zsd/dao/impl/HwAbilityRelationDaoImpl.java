package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.HwAbilityRelationDao;
import com.zsd.module.HwAbilityRelationInfo;

@SuppressWarnings("unchecked")
public class HwAbilityRelationDaoImpl implements HwAbilityRelationDao{

	@Override
	public HwAbilityRelationInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from HwAbilityRelationInfo har hq where har.id = "+id;
		List<HwAbilityRelationInfo> l = sess.createQuery(hql).list();
		if(l.size() > 0){
			return l.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, HwAbilityRelationInfo hmr) {
		// TODO Auto-generated method stub
		sess.save(hmr);
	}

	@Override
	public void update(Session sess, HwAbilityRelationInfo hmr) {
		// TODO Auto-generated method stub
		sess.update(hmr);
	}

	@Override
	public List<HwAbilityRelationInfo> findInfoByOpt(Session sess,
			Integer hwId, Integer batId) {
		// TODO Auto-generated method stub
		String hql = " from HwAbilityRelationInfo har hq where 1 = 1";
		if(hwId > 0){
			hql += " and har.hwQueInfo.id = "+hwId;
		}
		if(batId > 0){
			hql += " and hmr.buffetAbilityTypeInfo.id = "+batId;
		}
		return sess.createQuery(hql).list();
	}
}
