package com.zsd.dao.impl;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherInfoDao;
import com.zsd.module.NetTeacherInfo;

public class NetTeacherInfoDaoImpl implements NetTeacherInfoDao {

	@Override
	public NetTeacherInfo get(Session sess, int id) {
		
		return (NetTeacherInfo) sess.load(NetTeacherInfo.class, id);
	}

	@Override
	public void save(Session sess, NetTeacherInfo nt) {
		sess.save(nt);

	}

	@Override
	public void delete(Session sess, NetTeacherInfo nt) {
		sess.delete(nt);

	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, NetTeacherInfo nt) {
		sess.update(nt);

	}

}
