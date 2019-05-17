package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.BuffetLoreRelateInfoDao;
import com.zsd.module.BuffetLoreRelateInfo;

@SuppressWarnings("unchecked")
public class BuffetLoreRelateInfoDaoImpl implements BuffetLoreRelateInfoDao{

	@Override
	public BuffetLoreRelateInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from BuffetLoreRelateInfo as blr where blr.id = "+id;
		List<BuffetLoreRelateInfo> blrList = sess.createQuery(hql).list();
		if(blrList.size() > 0){
			return blrList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, BuffetLoreRelateInfo blr) {
		// TODO Auto-generated method stub
		sess.save(blr);
	}

	@Override
	public void delete(Session sess, BuffetLoreRelateInfo blr) {
		// TODO Auto-generated method stub
		sess.delete(blr);
	}

	@Override
	public void update(Session sess, BuffetLoreRelateInfo blr) {
		// TODO Auto-generated method stub
		sess.update(blr);
	}

	@Override
	public List<BuffetLoreRelateInfo> findInfoByOpt(Session sess,
			Integer buffetId, Integer relateLoreId) {
		// TODO Auto-generated method stub
		String hql = " from BuffetLoreRelateInfo as blr where blr.buffetQueInfo.id = "+buffetId;
		if(relateLoreId > 0){
			hql += " and blr.loreInfoByLoreId.id = "+relateLoreId;
		}
		hql += " order by blr.loreInfoByLoreId.loreCode desc";//知识点编码降序排列
		return sess.createQuery(hql).list();
	}

}
