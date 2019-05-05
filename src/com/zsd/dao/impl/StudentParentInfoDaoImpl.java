package com.zsd.dao.impl;

import org.hibernate.Session;

import com.zsd.dao.StudentParentInfoDao;
import com.zsd.module.StudentParentInfo;

public class StudentParentInfoDaoImpl implements StudentParentInfoDao {

	@Override
	public StudentParentInfo get(Session sess, int id) {
		return (StudentParentInfo) sess.load(StudentParentInfo.class, id);
	}

	@Override
	public void save(Session sess, StudentParentInfo spInfo) {
		sess.save(spInfo);
	}

	@Override
	public void delete(Session sess, StudentParentInfo spInfo) {
		sess.delete(spInfo);

	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, StudentParentInfo spInfo) {
	sess.update(spInfo);

	}

}
