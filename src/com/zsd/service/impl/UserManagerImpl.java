package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.User;
import com.zsd.service.UserManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class UserManagerImpl implements UserManager {
	UserDao userDao = null;
	Transaction tran = null;

	@Override
	public Integer addUser(String userAccount, String realName,
			 String password,  String mobile,String lastLoginDate,
			 String lastLoginIp, String signDate,
			 Integer schoolId, String endDate, 
			 Integer yearSystem, 
			 String prov, String city) throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = new User(userAccount, "", realName, "", password, "", mobile, "", "", "", "", lastLoginDate, lastLoginIp, signDate, 0, 0, 1, 0, schoolId, endDate, 0, 0, yearSystem, "", 0, 0.0, prov, city);
			userDao.save(sess, user);
			tran.commit();
			return user.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加用户时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateUser(Integer id , String nickName, String realName,
			String sex, String password, String email, String mobile,
			String portrait, String birthday, String qq) throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, id);
			if(user != null){
				user.setNickName(nickName);
				user.setRealName(realName);
				user.setSex(sex);
				user.setPassword(password);
				user.setEmail(email);
				user.setMobile(mobile);
				user.setPortrait(portrait);
				user.setBirthday(birthday);
				user.setQq(qq);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定用户基本信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateUser(Integer id, String lastLoginDate,
			String lastLoginIp, Integer loginTimes, Integer loginStatus)
			throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, id);
			if(user != null){
				user.setLastLoginDate(lastLoginDate);
				user.setLastLoginDate(lastLoginDate);
				user.setLoginTimes(loginTimes);
				user.setLoginStatus(loginStatus);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定用户登录信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<User> listInfoByAccount(String account) throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return userDao.findInfoByAccount(sess, account);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户账户获取用户信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<User> listInfoByAccount(String account, String password)
			throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return userDao.findInfoByAccPwd(sess, account, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户账户获取用户信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}


}
