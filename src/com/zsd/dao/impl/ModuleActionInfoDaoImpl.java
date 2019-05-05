package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.ModuleActionInfoDao;
import com.zsd.module.ModuleActionInfo;

@SuppressWarnings("unchecked")
public class ModuleActionInfoDaoImpl implements ModuleActionInfoDao{

	@Override
	public ModuleActionInfo get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (ModuleActionInfo) sess.load(ModuleActionInfo.class, id);
	}

	@Override
	public void save(Session sess, ModuleActionInfo mact) {
		// TODO Auto-generated method stub
		sess.save(mact);
	}

	@Override
	public void delete(Session sess, ModuleActionInfo mact) {
		// TODO Auto-generated method stub
		sess.delete(mact);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public List<ModuleActionInfo> findInfoByModId(Session sess, Integer modId) {
		// TODO Auto-generated method stub
		String hql = " from ModuleActionInfo as ma where ma.moduleInfo.id = "+modId + " order by ma.actOrder asc";
		return sess.createQuery(hql).list();
	}

}
