package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.HwQueDao;
import com.zsd.module.HwQueInfo;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class HwQueDaoImpl implements HwQueDao{

	@Override
	public HwQueInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from HwQueInfo as hq where hq.id = "+id;
		List<HwQueInfo> l = sess.createQuery(hql).list();
		if(l.size() > 0){
			return l.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, HwQueInfo hq) {
		// TODO Auto-generated method stub
		sess.save(hq);
	}

	@Override
	public void update(Session sess, HwQueInfo hq) {
		// TODO Auto-generated method stub
		sess.update(hq);
	}

	@Override
	public List<HwQueInfo> findPageInfoByOpt(Session sess, Integer loreId,
			Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from HwQueInfo as hq where hq.loreInfo.id = "+loreId + " order by hq.orders desc";
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByLoreId(Session sess, Integer loreId) {
		// TODO Auto-generated method stub
		String hql = "select count(hq.id) from HwQueInfo as hq where hq.loreInfo.id = "+loreId;
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<HwQueInfo> findInfoByOpt(Session sess, Integer loreId,
			Integer btId, Integer inUse, boolean currNumFlag) {
		// TODO Auto-generated method stub
		String hql = " from HwQueInfo as hq where hq.loreInfo.id = "+loreId;
		if(btId > 0){
			hql += " and hq.buffetTypeInfo.id = "+btId;
		}
		if(!inUse.equals(-1)){
			hql += " and hq.inUse = "+inUse;
		}
		if(currNumFlag){
			hql += " order by hq.num desc";
			return sess.createQuery(hql).setFirstResult(0).setMaxResults(1).list();
		}else{
			hql += " order by hq.orders asc";
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public List<HwQueInfo> findInfoByLoreAndBuffetType(Session sess,
			Integer loreId, String buffetTypeName) {
		// TODO Auto-generated method stub
		String hql = " from HwQueInfo as hq where hq.loreInfo.id = "+loreId + " and hq.buffetTypeInfo.types = '"+buffetTypeName+"'";
		return sess.createQuery(hql).list();
	}

}
