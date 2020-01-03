package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.TownDao;
import com.zsd.module.TownInfo;

@SuppressWarnings("unchecked")
public class TownDaoImpl implements TownDao{

	@Override
	public List<TownInfo> findInfoByCountyCode(Session sess, String countyCode) {
		// TODO Auto-generated method stub
		String hql = " from TownInfo as t where t.countyCode = '"+countyCode+"'";
		return sess.createQuery(hql).list();
	}

	@Override
	public void save(Session sess, TownInfo town) {
		// TODO Auto-generated method stub
		sess.save(town);
	}

	@Override
	public List<TownInfo> findInfoByCountyName(Session sess, String countyName) {
		// TODO Auto-generated method stub
		String hql = " from TownInfo as t where t.countyName = '"+countyName+"'";
		return sess.createQuery(hql).list();
	}

}
