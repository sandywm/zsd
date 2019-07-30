package com.zsd.dao.impl;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherStudioRelationDao;
import com.zsd.module.NetTeacherStudioRelationInfo;

public class NetTeacherStudioRelationDaoImpl implements
		NetTeacherStudioRelationDao {

	@Override
	public NetTeacherStudioRelationInfo get(Session sess, int id) {
		return (NetTeacherStudioRelationInfo) sess.load(NetTeacherStudioRelationInfo.class, id);
	}

	@Override
	public void save(Session sess, NetTeacherStudioRelationInfo ntsr) {
			sess.save(ntsr);
	}

	@Override
	public void delete(Session sess, NetTeacherStudioRelationInfo ntsr) {
		sess.delete(ntsr);

	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));

	}

	@Override
	public void update(Session sess, NetTeacherStudioRelationInfo ntsr) {
		sess.update(ntsr);
	}

}
