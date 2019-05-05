package com.zsd.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.RoleInfoDao;
import com.zsd.dao.RoleUserInfoDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.RoleInfo;
import com.zsd.module.RoleUserInfo;
import com.zsd.module.User;
import com.zsd.service.RoleUserInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class RoleUserInfoManagerImpl implements RoleUserInfoManager {
	RoleInfoDao roleinfoDao = null;
	UserDao userDao = null;
	RoleUserInfoDao ruinfoDao = null;
	Transaction tran = null;
	@Override
	public Integer addRoleUserInfo(Integer userId, Integer roleId)
			throws WEBException {
		try {
			roleinfoDao = (RoleInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_INFO);
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			ruinfoDao = (RoleUserInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, userId);
			RoleInfo roleInfo = roleinfoDao.get(sess, roleId);
			RoleUserInfo  ruInfo = new RoleUserInfo(user, roleInfo);
			ruinfoDao.save(sess, ruInfo);
			tran.commit();
			return ruInfo.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加角色用户信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateRoleUserById(Integer id, Integer userId, Integer roleId)
			throws WEBException {
		try {
			roleinfoDao = (RoleInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_INFO);
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			ruinfoDao = (RoleUserInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			RoleUserInfo  ruInfo = ruinfoDao.get(sess, id);
			if(ruInfo != null){
				User user = userDao.get(sess, userId);
				RoleInfo roleInfo = roleinfoDao.get(sess, roleId);
				ruInfo.setUser(user);
				ruInfo.setRoleInfo(roleInfo);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定角色用户基本信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
