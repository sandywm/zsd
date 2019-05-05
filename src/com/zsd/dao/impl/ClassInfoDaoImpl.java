package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.ClassInfoDao;
import com.zsd.module.ClassInfo;

public class ClassInfoDaoImpl implements ClassInfoDao {

	@Override
	public ClassInfo get(Session sess, int id) {
		return (ClassInfo) sess.load(ClassInfo.class, id);
	}

	@Override
	public void save(Session sess, ClassInfo classInfo) {
		sess.save(classInfo);
	}

	@Override
	public void delete(Session sess, ClassInfo classInfo) {
		sess.delete(classInfo);

	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));

	}

	@Override
	public void update(Session sess, ClassInfo classInfo) {
		sess.update(classInfo);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassInfo> findClassInfo(Session sess) {
		String hql = " from ClassInfo as ci";
		return sess.createQuery(hql).list();
	}

}
