package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.HwMindRelationDao;
import com.zsd.module.HwMindRelationInfo;

@SuppressWarnings("unchecked")
public class HwMindRelationDaoImpl implements HwMindRelationDao{

	@Override
	public HwMindRelationInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from HwMindRelationInfo hmr hq where hmr.id = "+id;
		List<HwMindRelationInfo> l = sess.createQuery(hql).list();
		if(l.size() > 0){
			return l.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, HwMindRelationInfo hmr) {
		// TODO Auto-generated method stub
		sess.save(hmr);
	}

	@Override
	public void update(Session sess, HwMindRelationInfo hmr) {
		// TODO Auto-generated method stub
		sess.update(hmr);
	}

	@Override
	public List<HwMindRelationInfo> findInfoByOpt(Session sess, Integer hwId,
			Integer bmtId) {
		// TODO Auto-generated method stub
		String hql = " from HwMindRelationInfo hmr hq where 1 = 1";
		if(hwId > 0){
			hql += " and hmr.hwQueInfo.id = "+hwId;
		}
		if(bmtId > 0){
			hql += " and hmr.buffetMindTypeInfo.id = "+bmtId;
		}
		return sess.createQuery(hql).list();
	}
}
