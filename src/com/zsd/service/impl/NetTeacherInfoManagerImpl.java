package com.zsd.service.impl;

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

}
