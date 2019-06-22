package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.EditionDao;
import com.zsd.module.Edition;

@SuppressWarnings("unchecked")
public class EditionDaoImpl implements EditionDao{

	@Override
	public Edition get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (Edition) sess.load(Edition.class, id);
	}

	@Override
	public void save(Session sess, Edition edi) {
		// TODO Auto-generated method stub
		sess.save(edi);
	}

	@Override
	public void delete(Session sess, Edition edi) {
		// TODO Auto-generated method stub
		sess.delete(edi);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, Edition edi) {
		// TODO Auto-generated method stub
		sess.update(edi);
	}

	@Override
	public List<Edition> findInfoByShowStatus(Session sess, Integer ediId,Integer showStatus) {
		// TODO Auto-generated method stub
		String hql = " from Edition as edi where 1=1";
		if(ediId > 0){
			hql += " and edi.id = "+ediId;
		}
		if(showStatus >= 0){
			hql += " and edi.showStatus = "+showStatus;
		}
		hql += " order by edi.ediOrder";
		return sess.createQuery(hql).list();
	}

}
