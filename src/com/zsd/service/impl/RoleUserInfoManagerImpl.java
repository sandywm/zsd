package com.zsd.service.impl;

import java.util.List;

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
	public Integer addRoleUserInfo(Integer userId, Integer roleId, String prov, String city,
			String county, String town, Integer schoolType, Integer schoolId,
			Integer gradeNo, Integer classId)
			throws WEBException {
		try {
			roleinfoDao = (RoleInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_INFO);
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			ruinfoDao = (RoleUserInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, userId);
			RoleInfo roleInfo = roleinfoDao.get(sess, roleId);
			RoleUserInfo  ruInfo = new RoleUserInfo(user, roleInfo,prov,city,county,town,schoolType,schoolId,gradeNo,classId);
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

	@Override
	public List<RoleUserInfo> listUserRoleInfoByPosition(String prov,
			String city, String county,Integer schoolType,
			Integer schoolId,Integer gradeNo,Integer classId) throws WEBException {
		try {
			ruinfoDao = (RoleUserInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return ruinfoDao.findUserRoleInfoByPosition(sess, prov, city, county, schoolType, schoolId, gradeNo, classId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据省市县获取角色信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<RoleUserInfo> listUserRoleInfoByoption(String accName,
			String realName,Integer schoolId, Integer roleId, String prov,
			String city, String county, Integer schoolType,
			Integer gradeNo, Integer classId, Integer pageNo, Integer pageSize)
			throws WEBException {
			try {
				ruinfoDao = (RoleUserInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_USER_INFO);
				Session sess  = HibernateUtil.currentSession();
				return ruinfoDao.findUserRoleInfoByoption(sess, accName, realName, schoolId, roleId, prov, city, county, schoolType, gradeNo, classId, pageNo, pageSize);
			} catch (Exception e) {
				e.printStackTrace();
				throw new WEBException("根据账户,学校名称,角色名,省,市,县,学段,学校名称,年级,班级获取角色信息列表时出现异常!");
			} finally{
				HibernateUtil.closeSession();
			}
	}

	@Override
	public Integer listRuInfoByoption(String accName, String realName,
			Integer schoolId, Integer roleId, String prov, String city,
			String county, Integer schoolType, Integer gradeNo, Integer classId)
			throws WEBException {
		try {
			ruinfoDao = (RoleUserInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ROLE_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return ruinfoDao.findRuInfoByoptionCount(sess, accName, realName, schoolId, roleId, prov, city, county, schoolType, gradeNo, classId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据账户,学校名称,角色名,省,市,县,学段,学校名称,年级,班级获取记录数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
