package com.zsd.dao.impl;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherStudentDao;
import com.zsd.module.NetTeacherStudent;

public class NetTeacherStudentDaoImpl implements NetTeacherStudentDao {

	@Override
	public NetTeacherStudent get(Session sess, int id) {
		return (NetTeacherStudent) sess.load(NetTeacherStudent.class, id);
	}

	@Override
	public void save(Session sess, NetTeacherStudent nts) {
		sess.save(nts);
	}

	@Override
	public void delete(Session sess, NetTeacherStudent nts) {
		sess.delete(nts);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, NetTeacherStudent nts) {
		sess.update(nts);
	}

}
