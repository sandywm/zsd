package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.NetTeacherInfoDao;
import com.zsd.dao.SubjectDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.NetTeacherInfo;
import com.zsd.module.Subject;
import com.zsd.module.User;
import com.zsd.service.NetTeacherInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;
public class NetTeacherInfoManagerImpl implements NetTeacherInfoManager {
	NetTeacherInfoDao ntDao = null;
	UserDao userDao = null;
	SubjectDao sjDao =null;
	Transaction tran = null;
	@Override
	public Integer addNtInfo(Integer teaId, Integer subId, Integer schoolType,
			Integer baseMoney, String teacherIntro, String honorPic,
			String bankName, String bankNumber, String teaSign, String teaEdu,
			String graduateSchool, String major, Integer schoolAge,
			Integer checkStatus, Integer teaScore) throws WEBException {
		try {
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			sjDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, teaId);
			Subject subject = sjDao.get(sess, subId);
			NetTeacherInfo nt =  new NetTeacherInfo(user, subject, schoolType, baseMoney, teacherIntro, honorPic, bankName, bankNumber, teaSign, teaEdu, graduateSchool, major, schoolAge, checkStatus, teaScore);
				ntDao.save(sess, nt);
			tran.commit();
			return nt.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加网络导师时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateNtInfo(Integer id, Integer teaId, Integer subId,
			Integer schoolType, Integer baseMoney, String teacherIntro,
			String honorPic, String bankName, String bankNumber,
			String teaSign, String teaEdu, String graduateSchool, String major,
			Integer schoolAge, Integer checkStatus, Integer teaScore) throws WEBException {
		try {
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			sjDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherInfo nt = ntDao.get(sess, id);
			if(nt != null){
				User user = userDao.get(sess, teaId);
				Subject subject = sjDao.get(sess, subId);
				nt.setUser(user);
				nt.setSubject(subject);
				nt.setSchoolType(schoolType);
				nt.setBaseMoney(baseMoney);
				nt.setTeacherIntro(teacherIntro);
				nt.setHonorPic(honorPic);
				nt.setBankName(bankName);
				nt.setBankNumber(bankNumber);
				nt.setTeaSign(teaSign);
				nt.setTeaEdu(teaEdu);
				nt.setGraduateSchool(graduateSchool);
				nt.setMajor(major);
				nt.setSchoolAge(schoolAge);
				nt.setCheckStatus(checkStatus);
				nt.setTeaScore(teaScore);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定网络导师基本信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateNtBybasicInfo(Integer id, String realName,
			String nickName, String teaSign, String teaEdu,
			String graduateSchool, String major, Integer schoolAge, String sex,
			String birthday) throws WEBException {
		try {
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherInfo nt = ntDao.get(sess, id);
			if(nt != null){
				User user = userDao.get(sess, nt.getUser().getId());
				user.setRealName(realName);
				user.setNickName(nickName);
				user.setBirthday(birthday);
				user.setSex(sex);
				nt.setUser(user);
				nt.setTeaSign(teaSign);
				nt.setTeaEdu(teaEdu);
				nt.setGraduateSchool(graduateSchool);
				nt.setMajor(major);
				nt.setSchoolAge(schoolAge);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定网络导师基本信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<NetTeacherInfo> listntInfoByuserId(Integer uid)
			throws WEBException {
		try {
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return ntDao.findntInfoByuserId(sess, uid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户编号获取网络导师信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<NetTeacherInfo> listntInfoByTeaId(Integer Id)
			throws WEBException {
		try {
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return ntDao.findntInfoByTeaId(sess, Id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据主键编号获取网络导师信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public boolean updateNtInfoByCheckSta(Integer id, Integer checkStatus)
			throws WEBException {
		try {
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherInfo nt = ntDao.get(sess, id);
			if(nt != null){
				nt.setCheckStatus(checkStatus);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据主键修改指定网络导师审核状态时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateNtByBankCard(Integer uid, String bName, String bNum)
			throws WEBException {
		try {
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			List<NetTeacherInfo> ntList = ntDao.findntInfoByuserId(sess, uid);
			NetTeacherInfo nt = ntList.get(0);
			if(nt != null){
				nt.setBankName(bName);
				nt.setBankNumber(bNum);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户编号更新银行卡信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<NetTeacherInfo> listNtByOption(String accName,
			String realName, Integer checkSta,String sDate,String eDate, Integer pageNo,
			Integer pageSize) throws WEBException {
		try {
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return ntDao.getNtByOption(sess, accName, realName, checkSta, sDate, eDate, pageNo, pageSize);
		}catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据账户,真实姓名,审核状态,注册时间获取网络导师信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getNtByOptionCount(String accName, String realName,
			Integer checkSta, String sDate, String eDate) throws WEBException {
		try {
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return ntDao.getNtByOptionCount(sess, accName, realName, checkSta, sDate, eDate);
		}catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据账户,真实姓名,审核状态,注册时间获取网络导师记录数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean checkNtInfoByUserId(Integer userId) throws WEBException {
		try {
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return ntDao.checkNtInfoByUserId(sess, userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户编号判断网络导师是否审核通过时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}


}
