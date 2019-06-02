package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.BuffetAllDao;
import com.zsd.module.BuffetAbilityRelationInfo;
import com.zsd.module.BuffetAbilityTypeInfo;
import com.zsd.module.BuffetMindRelationInfo;
import com.zsd.module.BuffetMindTypeInfo;
import com.zsd.module.BuffetTypeInfo;

@SuppressWarnings("unchecked")
public class BuffetAllDaoImpl implements BuffetAllDao{

	@Override
	public BuffetTypeInfo getBTEntityById(Session sess, Integer btId) {
		// TODO Auto-generated method stub
		String hql = " from BuffetTypeInfo as bt where bt.id = "+btId;
		List<BuffetTypeInfo> btList = sess.createQuery(hql).list();
		if(btList.size() > 0){
			return btList.get(0);
		}
		return null;
	}

	@Override
	public List<BuffetTypeInfo> findBTInfo(Session sess) {
		// TODO Auto-generated method stub
		String hql = " from BuffetTypeInfo as bt";
		return sess.createQuery(hql).list();
	}

	@Override
	public BuffetMindTypeInfo getBMTEntityById(Session sess, Integer bmtId) {
		// TODO Auto-generated method stub
		String hql = " from BuffetMindTypeInfo as bmt where bmt.id = "+bmtId;
		List<BuffetMindTypeInfo> bmtList = sess.createQuery(hql).list();
		if(bmtList.size() > 0){
			return bmtList.get(0);
		}
		return null;
	}

	@Override
	public List<BuffetMindTypeInfo> findBMTInfo(Session sess) {
		// TODO Auto-generated method stub
		String hql = " from BuffetMindTypeInfo as bmt";
		return sess.createQuery(hql).list();
	}

	@Override
	public BuffetAbilityTypeInfo getBATEntityById(Session sess, Integer batId) {
		// TODO Auto-generated method stub
		String hql = " from BuffetAbilityTypeInfo as bat where bat.id = "+batId;
		List<BuffetAbilityTypeInfo> batList = sess.createQuery(hql).list();
		if(batList.size() > 0){
			return batList.get(0);
		}
		return null;
	}

	@Override
	public List<BuffetAbilityTypeInfo> findBATInfo(Session sess) {
		// TODO Auto-generated method stub
		String hql = " from BuffetAbilityTypeInfo as bat";
		return sess.createQuery(hql).list();
	}

	@Override
	public void saveBMR(Session sess, BuffetMindRelationInfo bmrInfo) {
		// TODO Auto-generated method stub
		sess.save(bmrInfo);
	}

	@Override
	public void delBMRById(Session sess, Integer bmrId) {
		// TODO Auto-generated method stub
		sess.delete((BuffetMindRelationInfo) sess.load(BuffetMindRelationInfo.class, bmrId));
	}

	@Override
	public List<BuffetMindRelationInfo> findBMRInfoByBuffetId(Session sess,
			Integer buffetId) {
		// TODO Auto-generated method stub
		String hql = " from BuffetMindRelationInfo as bmr where bmr.buffetQueInfo.id = "+buffetId + " order by bmr.buffetMindTypeInfo.id asc";
		return sess.createQuery(hql).list();
	}

	@Override
	public void saveBAR(Session sess, BuffetAbilityRelationInfo barInfo) {
		// TODO Auto-generated method stub
		sess.save(barInfo);
	}

	@Override
	public void delBARById(Session sess, Integer barId) {
		// TODO Auto-generated method stub
		sess.delete((BuffetAbilityRelationInfo) sess.load(BuffetAbilityRelationInfo.class, barId));
	}

	@Override
	public List<BuffetAbilityRelationInfo> findBARInfoByBuffetId(Session sess,
			Integer buffetId) {
		// TODO Auto-generated method stub
		String hql = " from BuffetAbilityRelationInfo as bar where bar.buffetQueInfo.id = "+buffetId + " order by bar.buffetAbilityTypeInfo.id asc";
		return sess.createQuery(hql).list();
	}

}
