package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.User;
import com.zsd.service.UserManager;
import com.zsd.tools.CurrentTime;
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
			User user = new User(userAccount, "", realName, "", password, "", mobile, "Module/commonJs/ueditor/jsp/head/defaultHead.jpg", "", "", "", lastLoginDate, 
					lastLoginIp, signDate, 0, 0, 1, 0, schoolId, endDate, 0, 0, yearSystem, "", 0, 0, prov, city);
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
	public boolean updateUserStu(Integer id , String nickName, String realName,
			String sex,String birthday, String qq) throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, id);
			if(user != null){
				if(!nickName.equals("")){
				  user.setNickName(nickName);
				}
				if(!realName.equals("")){
				  user.setRealName(realName);
				}
				if(!sex.equals("")){
				  user.setSex(sex);
				}
				if(!birthday.equals("")){
				  user.setBirthday(birthday);
				}
				if(!qq.equals("")){
				  user.setQq(qq);
				}
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
	public boolean updateUserLogin(Integer id, String lastLoginDate,
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

	@Override
	public boolean userLogin(String account, String password)
			throws WEBException {
		boolean flag = false;
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			List<User> ulist =userDao.findInfoByAccount(sess, account);
			if(!ulist.isEmpty()){
				if (ulist.get(0).getPassword().equalsIgnoreCase(password)) {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户账户获取用户信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
		return flag;
	}

	@Override
	public boolean updateUser(Integer id, Integer accStatus,Integer freeSta, String endDate)
			throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, id);
			if(user != null){
				user.setAccountStatus(accStatus);
				if(!endDate.equals("")){
					user.setEndDate(endDate);
				}
				if(freeSta!=-1){
					user.setFreeStatus(freeSta);
				}
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定用户截止时间,账户状态时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateUser(Integer id, Integer coin, Integer exp,
			Integer zsdCoin, Integer accMoney) throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, id);
			if(user != null){
				if(!coin.equals(0)){
					user.setCoin(coin+user.getCoin());
				}
				if(!exp.equals(0)){
					user.setExperience(exp+user.getExperience());
				}
				if(!zsdCoin.equals(0)){
					user.setCoinZsd(zsdCoin+user.getCoinZsd());
				}
				if(!accMoney.equals(0)){
					user.setAccountMoney(accMoney+user.getAccountMoney());
				}
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定用户金币数,经验,知识典币,账号余额时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateUserByEmail(Integer id, String email)
			throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, id);
			if(user!=null){
				user.setEmail(email);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定用户电子邮箱地址时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateUserByMobile(Integer id, String mobile)
			throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, id);
			if(user!=null){
				user.setMobile(mobile);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定电话号码时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateUserBypwd(Integer id, String password)
			throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, id);
			if(user!=null){
				if(password==user.getPassword()||password.equals("")){
					
				}else{
					user.setPassword(password);
				}
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定用户密码时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<User> listEntityById(Integer id)throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return userDao.getEntityById(sess, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户编号获取用户信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean checkCurrpwd(Integer id, String password)
			throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			User user  = userDao.get(sess, id);
			boolean flag = true;
			if(user!=null){
				if (user.getPassword().equalsIgnoreCase(password)) {
					flag = false;
				}
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户编号是否当前密码信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<User> listUserInfoByoption(String accName, String realName,
			Integer schoolId, Integer roleId, String prov, String city,
			String county, Integer schoolType, Integer gradeNo,
			Integer classId, Integer pageNo, Integer pageSize)
			throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return userDao.findUserInfoByoption(sess, accName, realName, schoolId, roleId, prov, city, county, schoolType, gradeNo, classId, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据账户名,真实姓名,学校编号,角色编号,省市县,班级,年级获取用户信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getUserByoptionCount(String accName, String realName,
			Integer schoolId, Integer roleId, String prov, String city,
			String county, Integer schoolType, Integer gradeNo, Integer classId)
			throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return userDao.getUserByoptionCount(sess, accName, realName, schoolId, roleId, prov, city, county, schoolType, gradeNo, classId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据账户名,真实姓名,学校编号,角色编号,省市县,班级,年级获取用户信息记录数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateUserByHead(Integer id, String portrait)
			throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, id);
			if(user != null){
				user.setPortrait(portrait);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定用户头像信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean checkUserMobile(String mobile) throws WEBException {
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			List<User> ulist = userDao.checkUserMobile(sess, mobile);
			boolean flag = false;
			if(ulist.isEmpty()){
				flag = true;
			}
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据手机号码判断是否注册时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateSchoolInfo(Integer userId, Integer schoolId,
			Integer yearSystem) throws WEBException {
		// TODO Auto-generated method stub
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			List<User> uList = userDao.getEntityById(sess, userId);
			if(uList.size() > 0){
				User user = uList.get(0);
				user.setSchoolId(schoolId);
				user.setYearSystem(yearSystem);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改学生的学校信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateStuDateFlagById(Integer userId, String dateFlag)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			List<User> uList = userDao.getEntityById(sess, userId);
			if(uList.size() > 0){
				User user = uList.get(0);
				user.setDateFlag(CurrentTime.getSpecInfo("year") + "-09-01");
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改学生的升学标记时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}


}
