package com.zsd.dao.impl;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherStudent> findNTByStuId(Session sess, int stuId) {
		String hql = "from NetTeacherStudent as nts where nts.user.id="+stuId+"and nts.bindStatus!=2 and nts.bindStatus!=0 and nts.clearStatus=0";
		return  sess.createQuery(hql).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherStudent> findNTByntId(Session sess, int ntId) {
		String hql = "from NetTeacherStudent as nts where nts.netTeacherInfo.user.id="+ntId+"and nts.bindStatus!=2 and nts.bindStatus!=0 and nts.clearStatus=0";
		return  sess.createQuery(hql).list();
	}

}
