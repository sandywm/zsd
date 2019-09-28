package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.InviteCodeInfoDao;
import com.zsd.module.InviteCodeInfo;

/** 
 * @author zong
 * @version 2019-5-6 上午09:52:36
 */
public class InviteCodeInfoDaoImpl implements InviteCodeInfoDao {

	@Override
	public InviteCodeInfo get(Session sess, int id) {
		
		return (InviteCodeInfo) sess.load(InviteCodeInfo.class, id);
	}

	@Override
	public void save(Session sess, InviteCodeInfo invitecInfo) {
		sess.save(invitecInfo);

	}
	@Override
	public void delete(Session sess, InviteCodeInfo invitecInfo) {
		sess.delete(invitecInfo);

	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));

	}

	@Override
	public void update(Session sess, InviteCodeInfo invitecInfo) {
		sess.update(invitecInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InviteCodeInfo> findInviteCodeInfo(Session sess) {
		String hql = " from InviteCodeInfo as ic";
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InviteCodeInfo> findIcInfoByOpt(Session sess,String inviteCode,String inviteType) {
		String hql = " from InviteCodeInfo as ic where ic.inviteCode='"+inviteCode+"' and ic.inviteType = '"+inviteType+"'";
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InviteCodeInfo> findIcInfoByOption(Session sess,
			Integer inviteId, String inviteType) {
		String hql = " from InviteCodeInfo as ic where ic.inviteId="+inviteId+"  and ic.inviteType= '"+inviteType+"'";
		return sess.createQuery(hql).list();
	}

}
