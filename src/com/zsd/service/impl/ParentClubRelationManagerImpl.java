package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ParentClubDao;
import com.zsd.dao.ParentClubRelationDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.ParentClubInfo;
import com.zsd.module.ParentClubRelationInfo;
import com.zsd.module.User;
import com.zsd.service.ParentClubRelationManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class ParentClubRelationManagerImpl implements ParentClubRelationManager {
	ParentClubRelationDao pcrDao = null;
	ParentClubDao pcDao = null;
	Transaction tran = null;
	UserDao userDao = null;
	@Override
	public Integer addParentClubRelation(Integer userId, Integer pcId,
			String addTime, String outTime) throws WEBException {
		try {
			pcrDao = (ParentClubRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_PARENT_CLUB_RELATION);
			pcDao = (ParentClubDao) DaoFactory.instance(null).getDao(Constants.DAO_PARENT_CLUB);
			userDao =(UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			ParentClubRelationInfo pcrInfo = new ParentClubRelationInfo();
			User user = userDao.get(sess, userId);
			pcrInfo.setUser(user);
			ParentClubInfo parentClubInfo=pcDao.get(sess, pcId);
			pcrInfo.setParentClubInfo(parentClubInfo);
			pcrInfo.setAddTime(addTime);
			pcrInfo.setOutTime(outTime);
			tran.commit();
			return pcrInfo.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加家长群列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<ParentClubRelationInfo> listInfoByParentCludId(Integer pcId)
			throws WEBException {
		try {
			pcrDao = (ParentClubRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_PARENT_CLUB_RELATION);
			Session sess  = HibernateUtil.currentSession();
			return pcrDao.findInfoByPcrId(sess, pcId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据家长群列表获取家长群列表信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<ParentClubRelationInfo> listInfoByUserId(Integer userId)
			throws WEBException {
		try {
			pcrDao = (ParentClubRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_PARENT_CLUB_RELATION);
			Session sess  = HibernateUtil.currentSession();
			return pcrDao.findInfoByUserId(sess, userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户编号获取家长群列表信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
