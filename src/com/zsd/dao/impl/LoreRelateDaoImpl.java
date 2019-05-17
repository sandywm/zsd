package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.LoreRelateDao;
import com.zsd.module.LoreRelateInfo;

@SuppressWarnings("unchecked")
public class LoreRelateDaoImpl implements LoreRelateDao{

	@Override
	public LoreRelateInfo get(Session sess, int id) {
		// TODO Auto-generated method stub
		String hql = " from LoreRelateInfo as lr where lr.id = "+id; 
		List<LoreRelateInfo> lrList = sess.createQuery(hql).list();
		if(lrList.size() > 0){
			return lrList.get(0);
		}
		return null;
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

	@Override
	public List<LoreRelateInfo> findIndoByLoreId(Session sess, Integer loreId,Integer rootLoreId,
			Integer loreInUse,String orderOpt) {
		// TODO Auto-generated method stub
		String hql = " from LoreRelateInfo as lr where 1=1";
		if(loreId > 0){
			hql += " and lr.loreInfo.id = "+loreId;
		}
		if(rootLoreId > 0){
			hql += " and lr.rootLoreInfo.id = "+rootLoreId;
		}
		if(!loreInUse.equals(-1)){
			hql += " and lr.loreInfo.inUse = "+loreInUse;
		}
		if(orderOpt.equals("asc")){
			hql += " order by lr.rootLoreInfo.loreCode asc";
		}else if(orderOpt.equals("desc")){
			hql += " order by lr.rootLoreInfo.loreCode desc";
		}
		return sess.createQuery(hql).list();
	}

}
