package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ParentClubDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.ParentClubInfo;
import com.zsd.module.User;
import com.zsd.service.ParentClubManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class ParentClubManagerImpl implements ParentClubManager {
	ParentClubDao pcDao = null;
	UserDao userDao = null;
	Transaction tran = null;
	@Override
	public Integer addParentClub(Integer userId, String clubName,
			String clubCode, Integer maxNum, String clubProfile)
			throws WEBException {
		try {
			pcDao = (ParentClubDao) DaoFactory.instance(null).getDao(Constants.DAO_PARENT_CLUB);
			userDao =(UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			ParentClubInfo pcInfo = new ParentClubInfo();
			User user = userDao.get(sess, userId);
			pcInfo.setUser(user);
			pcInfo.setClubName(clubName);
			pcInfo.setClubCode(clubCode);
			pcInfo.setMaxNum(maxNum);
			pcInfo.setClubProfile(clubProfile);
			pcDao.save(sess, pcInfo);
			tran.commit();
			return pcInfo.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加家长群时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateParentClub(Integer id, String clubName,
			String clubProfile) throws WEBException {

			try {
				pcDao = (ParentClubDao) DaoFactory.instance(null).getDao(Constants.DAO_PARENT_CLUB);
				
				Session sess  = HibernateUtil.currentSession();
				tran = sess.beginTransaction();
				ParentClubInfo pcInfo = pcDao.get(sess, id);
				if(pcInfo!=null){
					pcInfo.setClubName(clubName);
					pcInfo.setClubProfile(clubProfile);
					pcDao.update(sess, pcInfo);
					tran.commit();
					return true;
				}
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				throw new WEBException("根据家长群编号更新家长群 群名,简介时出现异常!");
			} finally{
				HibernateUtil.closeSession();
			}
	}

	@Override
	public List<ParentClubInfo> listParentClubByuId(Integer userId)
			throws WEBException {
		try {
			pcDao = (ParentClubDao) DaoFactory.instance(null).getDao(Constants.DAO_PARENT_CLUB);
			Session sess  = HibernateUtil.currentSession();
			return pcDao.findParentClubByuId(sess, userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户编号获取家长群信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<ParentClubInfo> listParentClubByClubCode(String clubCode)
			throws WEBException {
		try {
			pcDao = (ParentClubDao) DaoFactory.instance(null).getDao(Constants.DAO_PARENT_CLUB);
			Session sess  = HibernateUtil.currentSession();
			return pcDao.findParentClubBypcCode(sess, clubCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据家长群邀请码获取家长群信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
