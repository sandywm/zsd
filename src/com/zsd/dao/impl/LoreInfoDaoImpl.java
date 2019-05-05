package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.LoreInfoDao;
import com.zsd.module.LoreInfo;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class LoreInfoDaoImpl implements LoreInfoDao{

	@Override
	public LoreInfo get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (LoreInfo) sess.load(LoreInfo.class, id);
	}

	@Override
	public void save(Session sess, LoreInfo lore) {
		// TODO Auto-generated method stub
		sess.save(lore);
	}

	@Override
	public void delete(Session sess, LoreInfo lore) {
		// TODO Auto-generated method stub
		sess.delete(lore);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, LoreInfo lore) {
		// TODO Auto-generated method stub
		sess.update(lore);
	}

	@Override
	public List<LoreInfo> findPageInfoByCptId(Session sess, Integer cptId,
			Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from LoreInfo as lore where lore.chapter.id = "+cptId;
		hql += " order by lore.loreOrder";
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByCptId(Session sess, Integer cptId) {
		// TODO Auto-generated method stub
		String hql = "select count(lore.id) from LoreInfo as lore where lore.chapter.id = "+cptId;
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<LoreInfo> findInfoByCptId(Session sess, Integer cptId) {
		// TODO Auto-generated method stub
		String hql = " from LoreInfo as lore where lore.chapter.id = "+cptId + " and lore.inUse = 0";
		return sess.createQuery(hql).list();
	}

}
