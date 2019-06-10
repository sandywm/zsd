package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.RoleInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.RoleInfo;
import com.zsd.service.RoleInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class RoleInfoManagerImpl implements RoleInfoManager {
	RoleInfoDao roleinfoDao = null;
	Transaction tran = null;
	@Override
	public Integer addRoleInfo(String roleName) throws WEBException {
		try {
			roleinfoDao = (RoleInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			RoleInfo roleInfo = new RoleInfo(roleName);
			roleinfoDao.save(sess, roleInfo);
			tran.commit();
			return roleInfo.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加角色时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateUser(Integer id, String roleName) throws WEBException {
		try {
			roleinfoDao = (RoleInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			RoleInfo roleInfo = roleinfoDao.get(sess, id);
			if(roleInfo != null){
				roleInfo.setRoleName(roleName);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定角色基本信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<RoleInfo> listRoleInfo(String roleName) throws WEBException {
		try {
			roleinfoDao = (RoleInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_INFO);
			Session sess  = HibernateUtil.currentSession();
			return roleinfoDao.findRoleInfo(sess, roleName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据角色名称获取角色信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<RoleInfo> listAllRoleInfo() throws WEBException {
		try {
			roleinfoDao = (RoleInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_INFO);
			Session sess  = HibernateUtil.currentSession();
			return roleinfoDao.findRoleInfo(sess);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("获取角色信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
