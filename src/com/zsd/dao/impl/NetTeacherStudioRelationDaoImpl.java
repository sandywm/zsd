package com.zsd.dao.impl;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherStudioRelationInfo> findInfoByNtStudioId(
			Session sess, Integer ntStudioId) {
		String hql=" from  NetTeacherStudioRelationInfo as ntsr where  ntsr.netTeacherStudioInfo.id="+ntStudioId;
		return  sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherStudioRelationInfo> findInfoByTeaId(Session sess,
			Integer ntId) {
		String hql=" from  NetTeacherStudioRelationInfo as ntsr where  ntsr.teaId="+ntId;
		return  sess.createQuery(hql).list();
	}

}
