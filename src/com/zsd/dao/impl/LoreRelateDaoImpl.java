package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.LoreRelateDao;
import com.zsd.module.LoreRelateInfo;

public class LoreRelateDaoImpl implements LoreRelateDao{

	@Override
	public LoreRelateInfo get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (LoreRelateInfo) sess.load(LoreRelateInfo.class, id);
	}

	@Override
	public void save(Session sess, LoreRelateInfo lr) {
		// TODO Auto-generated method stub
		sess.save(lr);
	}

	@Override
	public void delete(Session sess, LoreRelateInfo lr) {
		// TODO Auto-generated method stub
		sess.delete(lr);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, LoreRelateInfo lr) {
		// TODO Auto-generated method stub
		sess.update(lr);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoreRelateInfo> findIndoByLoreId(Session sess, Integer loreId,
			Integer loreInUse) {
		// TODO Auto-generated method stub
		String hql = " from LoreRelateInfo as lr where lr.loreInfo.id = "+loreId;
		if(!loreInUse.equals(-1)){
			hql += " and lr.loreInfo.inUse = "+loreInUse;
		}
		return sess.createQuery(hql).list();
	}

}
