package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.ModuleDao;
import com.zsd.module.ModuleInfo;

@SuppressWarnings("unchecked")
public class ModuleDaoImpl implements ModuleDao{

	@Override
	public ModuleInfo get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (ModuleInfo) sess.load(ModuleInfo.class, id);
	}

	@Override
	public void save(Session sess, ModuleInfo mod) {
		// TODO Auto-generated method stub
		sess.save(mod);
	}

	@Override
	public void delete(Session sess, ModuleInfo mod) {
		// TODO Auto-generated method stub
		sess.delete(mod);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public List<ModuleInfo> findAllInfo(Session sess) {
		// TODO Auto-generated method stub
		String hql = " from ModuleInfo as mod order by mod.modOrder asc";
		return sess.createQuery(hql).list();
	}

}
