package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.BuffetAbilityRelationInfoDao;
import com.zsd.module.BuffetAbilityRelationInfo;

public class BuffetAbilityRelationInfoDaoImpl implements
		BuffetAbilityRelationInfoDao {

	@Override
	public BuffetAbilityRelationInfo get(Session sess, int id) {
		return (BuffetAbilityRelationInfo) sess.load(BuffetAbilityRelationInfo.class, id);
	}

	@Override
	public void save(Session sess, BuffetAbilityRelationInfo barInfo) {
		sess.save(barInfo);
	}

	@Override
	public void delete(Session sess, BuffetAbilityRelationInfo barInfo) {
		sess.delete(barInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, BuffetAbilityRelationInfo barInfo) {
		sess.update(barInfo);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BuffetAbilityRelationInfo> findBARInfo(Session sess,
			Integer bqId) {
		String hql=" from BuffetAbilityRelationInfo as bar where bar.buffetQueInfo.id ="+bqId;
		return sess.createQuery(hql).list();
	}

}
