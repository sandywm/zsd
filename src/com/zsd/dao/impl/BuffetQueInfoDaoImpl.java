package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.BuffetQueInfoDao;
import com.zsd.module.BuffetQueInfo;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class BuffetQueInfoDaoImpl implements BuffetQueInfoDao{

	@Override
	public BuffetQueInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from BuffetQueInfo as bq where bq.id = "+id;
		List<BuffetQueInfo> bqList = sess.createQuery(hql).list();
		if(bqList.size() > 0){
			return bqList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, BuffetQueInfo bq) {
		// TODO Auto-generated method stub
		sess.save(bq);
	}

	@Override
	public void delete(Session sess, BuffetQueInfo bq) {
		// TODO Auto-generated method stub
		sess.delete(bq);
	}

	@Override
	public List<BuffetQueInfo> findPageInfoByLoreId(Session sess,
			Integer loreId, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from BuffetQueInfo as bq where bq.loreInfo.id = "+loreId + " order by bq.queOrder asc";
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByLoreId(Session sess, Integer loreId) {
		// TODO Auto-generated method stub
		String hql = "select count(bq.id) from BuffetQueInfo as bq where bq.loreInfo.id = "+loreId;
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<BuffetQueInfo> findInfoByOpt(Session sess, Integer loreId,
			Integer btId, Integer inUse, boolean currNumFlag) {
		// TODO Auto-generated method stub
		String hql = " from BuffetQueInfo as bq where bq.loreInfo.id = "+loreId;
		if(btId > 0){
			hql += " and bq.buffetTypeInfo.id = "+btId;
		}
		if(inUse >= 0){
			hql += " and bq.inUse = "+inUse;
		}
		if(currNumFlag){
			hql += " order by bq.buffetNum desc";
			return sess.createQuery(hql).setFirstResult(0).setMaxResults(1).list();
		}else{
			hql += " order by bq.queOrder asc";
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public void update(Session sess, BuffetQueInfo bq) {
		// TODO Auto-generated method stub
		sess.update(bq);
	}

	@Override
	public List<BuffetQueInfo> findInfoByOption(Session sess, Integer loreId,
			Integer inUse) {
		String hql = " from BuffetQueInfo as bq where bq.loreInfo.id = "+loreId;
		if(inUse >= 0){
			hql += " and bq.inUse = "+inUse;
		}
		hql += " order by bq.queOrder";
		
		return sess.createQuery(hql).list();
	}

	@Override
	public List<BuffetQueInfo> findInfoByLoreAndBuffetType(Session sess,
			Integer loreId, String buffetTypeName) {
		String hql = " from BuffetQueInfo as bq where bq.loreInfo.id = "+loreId;
		 hql+=" and bq.buffetTypeInfo.types='"+buffetTypeName+"'";
		 return sess.createQuery(hql).list();
	}

}
