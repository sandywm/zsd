package com.zsd.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.InviteCodeInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.InviteCodeInfo;
import com.zsd.service.InviteCodeInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

/** 
 * @author zong
 * @version 2019-5-6 上午10:01:51
 */
public class InviteCodeInfoManagerImpl implements InviteCodeInfoManager {
	InviteCodeInfoDao icDao = null;
	Transaction tran = null;
	@Override
	public List<InviteCodeInfo> listIcInfoByOpt(String inviteCode,String inviteType) throws WEBException {
		try {
			icDao = (InviteCodeInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_INVITE_CODE_INFO);
			Session sess  = HibernateUtil.currentSession();
			return icDao.findIcInfoByOpt(sess, inviteCode, inviteType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据邀请码编号获取邀请码信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public Integer addInviteCodeInfo(Integer inviteId, String inviteType,
			String inviteCode, Timestamp createDate) throws WEBException {
			try {
				icDao = (InviteCodeInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_INVITE_CODE_INFO);
				Session sess  = HibernateUtil.currentSession();
				tran = sess.beginTransaction();
				InviteCodeInfo icInfo = new InviteCodeInfo(inviteId, inviteType, inviteCode, createDate);
				icDao.save(sess, icInfo);
				tran.commit();
				return icInfo.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加邀请码信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<InviteCodeInfo> listIcInfoByOption(Integer inviteId,
			String inviteType) throws WEBException {
		try {
			icDao = (InviteCodeInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_INVITE_CODE_INFO);
			Session sess  = HibernateUtil.currentSession();
			return icDao.findIcInfoByOption(sess, inviteId, inviteType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据邀请编号,邀请码类型获取邀请码信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
}
