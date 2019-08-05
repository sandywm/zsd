package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherStudioDao;
import com.zsd.module.NetTeacherStudioInfo;

public class NetTeacherStudioDaoImpl implements NetTeacherStudioDao {

	@Override
	public NetTeacherStudioInfo get(Session sess, int id) {
		return (NetTeacherStudioInfo) sess.load(NetTeacherStudioInfo.class, id);
	}

	@Override
	public void save(Session sess, NetTeacherStudioInfo ntStudio) {
		sess.save(ntStudio);

	}

	@Override
	public void delete(Session sess, NetTeacherStudioInfo ntStudio) {
		sess.delete(ntStudio);

	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));

	}

	@Override
	public void update(Session sess, NetTeacherStudioInfo ntStudio) {
		sess.update(ntStudio);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherStudioInfo> findNTStudioByuId(Session sess,Integer userId) {
		String hql=" from NetTeacherStudioInfo as nts where nts.netTeacherInfo.user.id ="+ userId;
		return  sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherStudioInfo> findNTStudioBystudioCode(Session sess,
			String studioCode) {
		String hql=" from NetTeacherStudioInfo as nts where nts.studioCode = '"+ studioCode+"'";
		return  sess.createQuery(hql).list();
	}

}
