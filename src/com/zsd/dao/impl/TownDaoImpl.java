package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.TownDao;
import com.zsd.module.TownInfo;

public class TownDaoImpl implements TownDao{

	@SuppressWarnings("unchecked")
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

}
