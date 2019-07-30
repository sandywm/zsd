package com.zsd.dao.impl;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherStudioNewsDao;
import com.zsd.module.NetTeacherStudioNewsInfo;

public class NetTeacherStudioNewsDaoImpl implements
		NetTeacherStudioNewsDao {

	@Override
	public NetTeacherStudioNewsInfo get(Session sess, int id) {
		
		return (NetTeacherStudioNewsInfo) sess.load(NetTeacherStudioNewsInfo.class, id);
	}

	@Override
	public void save(Session sess, NetTeacherStudioNewsInfo ntsNews) {
		sess.save(ntsNews);

	}

	@Override
	public void delete(Session sess, NetTeacherStudioNewsInfo ntsNews) {
		sess.delete(ntsNews);

	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, NetTeacherStudioNewsInfo ntsNews) {
		sess.update(ntsNews);

	}

}
