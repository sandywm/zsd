package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ClassInfoDao;
import com.zsd.dao.SchoolDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.ClassInfo;
import com.zsd.module.School;
import com.zsd.service.ClassInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class ClassInfoManagerImpl implements ClassInfoManager {
	ClassInfoDao ciDao = null;
	SchoolDao sDao = null;
	Transaction tran = null;
	@Override
	public List<ClassInfo> listClassInfoByOption(Integer gradeId,
			String currentTime, Integer schoolId, String className)
			throws WEBException {
		try {
			ciDao = (ClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_CLASS_INFO);
			sDao = (SchoolDao) DaoFactory.instance(null).getDao(Constants.DAO_SCHOOL_INFO);
			Session sess  = HibernateUtil.currentSession();
			return ciDao.findClassInfoByOption(sess, gradeId, currentTime, schoolId, className);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据条件分页获取学校信息列表信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public Integer addClassInfo(Integer scId, String className,
			String buildeClassDate) throws WEBException {
		try {
			ciDao = (ClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_CLASS_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			School school = sDao.get(sess, scId);
			ClassInfo cInfo =  new ClassInfo(school, className, buildeClassDate);
			ciDao.save(sess, cInfo);
			tran.commit();
			return cInfo.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加班级时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<ClassInfo> listClassInfoById(Integer cId) throws WEBException {
		try {
			ciDao = (ClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_CLASS_INFO);
			Session sess  = HibernateUtil.currentSession();
			return ciDao.findClassInfoById(sess, cId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据主键获取学校信息列表信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
