package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ClassInfoDao;
import com.zsd.dao.RoleInfoDao;
import com.zsd.dao.UserClassInfoDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.ClassInfo;
import com.zsd.module.RoleInfo;
import com.zsd.module.User;
import com.zsd.module.UserClassInfo;
import com.zsd.service.UserClassInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class UserClassInfoManagerImpl implements UserClassInfoManager {
	RoleInfoDao roleinfoDao = null;
	UserDao userDao = null;
	ClassInfoDao cDao = null;
	UserClassInfoDao ucDao = null;
	Transaction tran = null;
	@Override
	public Integer addUcInfo(Integer userId, Integer classId, Integer roleId)
			throws WEBException {
		try {
			roleinfoDao = (RoleInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_INFO);
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			cDao = (ClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_CLASS_INFO);
			ucDao = (UserClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_CLASS_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, userId);
			RoleInfo roleInfo = roleinfoDao.get(sess, roleId);
			ClassInfo classInfo = cDao.get(sess, classId);
			UserClassInfo  ucInfo = new  UserClassInfo(user, roleInfo, classInfo, 0, "", 0, "", 0);
			ucDao.save(sess, ucInfo);
			tran.commit();
			return ucInfo.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加用户班级信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<UserClassInfo> listUcInfoByUserId(Integer userId)
			throws WEBException {
		 try {
			ucDao = (UserClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			return ucDao.findUcInfoByUserId(sess, userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户编号获取用户班级信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public UserClassInfo getEntityByOpt(Integer userId, Integer roleId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			ucDao = (UserClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			return ucDao.getEntityByOpt(sess, userId, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户编号，角色编号获取用户班级信息实体时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<UserClassInfo> listUcInfoByOpt(Integer classId, Integer roleId,
			Integer pageNo, Integer pageSize) throws WEBException {
		 try {
			 ucDao = (UserClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			return ucDao.findUcInfoByOpt(sess, classId, roleId, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据班级编号,角色编号获取用户班级信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public Integer getUciByOpt(Integer classId, Integer roleId)
			throws WEBException {
		 try {
			 ucDao = (UserClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			return ucDao.getUciByOpt(sess, classId, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据班级编号,角色编号获取用户班级信息记录数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<UserClassInfo> listTeaInfoByOpt(Integer userId, Integer roleId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			 ucDao = (UserClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			return ucDao.findTeaInfoByOpt(sess, userId, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("获取指定老师的班级列表(包括临时、永久接班的老师)信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public boolean updateInfoByOpt(Integer id, Integer userId,
			Integer applyUserId, String applyUsreName, Integer status)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			ucDao = (UserClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_CLASS_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, userId);
			UserClassInfo  ucInfo = ucDao.get(sess, id);
			if(ucInfo != null){
				if(userId > 0 && status.equals(2)){//此处大于0肯定是永久接班
					ucInfo.setUser(user);
					ucInfo.setAppUserId(0);
					ucInfo.setAppUserInfo("");
					ucInfo.setStatus(status);
				}else if(userId.equals(0) && status.equals(1) && applyUserId > 0){
					ucInfo.setAppUserId(applyUserId);
					ucInfo.setAppUserInfo(applyUsreName);
					ucInfo.setStatus(status);
				}
				ucDao.update(sess, ucInfo);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据主键修改班级老师、接班老师编号、接班老师信息、接班状态信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public UserClassInfo getEntityByOpt(Integer userId, Integer classId,
			Integer roleId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			 ucDao = (UserClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			return ucDao.getEntityByOpt(sess, userId, classId, roleId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据老师编号、班级编号、角色编号获取老师班级信息表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
