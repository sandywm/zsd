package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.JoinLoreRelationDao;
import com.zsd.module.JoinLoreRelation;

@SuppressWarnings("unchecked")
public class JoinLoreRelationDaoImpl implements JoinLoreRelationDao{

	@Override
	public JoinLoreRelation getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from JoinLoreRelation as jlr where jlr.id = "+id;
		List<JoinLoreRelation> jlrList = sess.createQuery(hql).list();
		if(jlrList.size() > 0){
			return jlrList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, JoinLoreRelation jlr) {
		// TODO Auto-generated method stub
		sess.save(jlr);
	}

	@Override
	public void delete(Session sess, JoinLoreRelation jlr) {
		// TODO Auto-generated method stub
		sess.delete(jlr);
	}

	@Override
	public void update(Session sess, JoinLoreRelation jlr) {
		// TODO Auto-generated method stub
		sess.update(jlr);
	}

	@Override
	public JoinLoreRelation getInfoByLoreId(Session sess, Integer loreId) {
		// TODO Auto-generated method stub
		String hql = " from JoinLoreRelation as jlr where find_in_set("+loreId+",jlr.loreIdArray) > 0";
		List<JoinLoreRelation> jlrList = sess.createQuery(hql).list();
		if(jlrList.size() > 0){
			return jlrList.get(0);
		}
		return null;
	}

}
