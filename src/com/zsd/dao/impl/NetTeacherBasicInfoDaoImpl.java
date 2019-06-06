package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherBasicInfoDao;
import com.zsd.module.NetTeacherBasicInfo;

/** 
 * @author zong
 * @version 2019-5-11 下午06:00:37
 */
public class NetTeacherBasicInfoDaoImpl implements NetTeacherBasicInfoDao {

	@Override
	public NetTeacherBasicInfo get(Session sess, int id) {
		return (NetTeacherBasicInfo) sess.load(NetTeacherBasicInfo.class, id);
	}

	@Override
	public void save(Session sess, NetTeacherBasicInfo ntbInfo) {
		sess.save(ntbInfo);

	}

	@Override
	public void delete(Session sess, NetTeacherBasicInfo ntbInfo) {
		sess.delete(ntbInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, NetTeacherBasicInfo ntbInfo) {
		sess.update(ntbInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherBasicInfo> findNtbByTeaId(Session sess, Integer teaId) {
		String hql="from NetTeacherBasicInfo as ntb where ntb.netTeacherInfo.id="+teaId;
		return sess.createQuery(hql).list();
	}

}
