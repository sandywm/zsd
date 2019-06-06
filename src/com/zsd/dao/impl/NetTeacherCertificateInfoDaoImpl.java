package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherCertificateInfoDao;
import com.zsd.module.NetTeacherCertificateInfo;

/** 
 * @author zong
 * @version 2019-5-13 下午05:27:55
 */
public class NetTeacherCertificateInfoDaoImpl implements
		NetTeacherCertificateInfoDao {

	@Override
	public NetTeacherCertificateInfo get(Session sess, int id) {
	
		return (NetTeacherCertificateInfo) sess.load(NetTeacherCertificateInfo.class, id);
	}

	@Override
	public void save(Session sess, NetTeacherCertificateInfo ntcInfo) {
		sess.save(ntcInfo);

	}

	@Override
	public void delete(Session sess, NetTeacherCertificateInfo ntcInfo) {
		sess.delete(ntcInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, NetTeacherCertificateInfo ntcInfo) {
		sess.update(ntcInfo);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherCertificateInfo> getEntityByid(Session sess,
			Integer id) {
		String hql ="from NetTeacherCertificateInfo as ntc where ntc.id = "+id;
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherCertificateInfo> getNtcByTeaId(Session sess,
			Integer teaId) {
		String hql ="from NetTeacherCertificateInfo as ntc where ntc.netTeacherInfo.id = "+teaId;
		return sess.createQuery(hql).list();
	}
}
