package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.LoreInfoDao;
import com.zsd.dao.StudyLogDao;
import com.zsd.dao.SubjectDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.StudyLogInfo;
import com.zsd.service.StudyLogManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class StudyLogManagerImpl implements StudyLogManager{

	UserDao uDao = null;
	LoreInfoDao lDao = null;
	SubjectDao sDao = null;
	StudyLogDao slDao = null;
	Transaction tran = null;
	@Override
	public Integer addStudyLog(Integer userId, Integer loreId, Integer subId,
			Integer step, Integer stepComplete, Integer isFinish,
			String sysAssess, Integer currentGold, Integer access,
			String addTime, Integer taskNumber, Integer logType)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			sDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyLogInfo sl = new StudyLogInfo(sDao.get(sess, subId), uDao.get(sess, userId), lDao.getEntityById(sess, loreId),
					access, addTime, step, stepComplete,currentGold, 0, isFinish,
					sysAssess, "", taskNumber,0, logType);
			slDao.save(sess, sl);
			tran.commit();
			return sl.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加学习记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean deleteStudyLog(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyLogInfo sl = slDao.getEntityById(sess, id);
			if(sl != null){
				slDao.delete(sess, sl);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("删除学习记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateStudyLog(Integer id, Integer step,
			Integer stepComplete, Integer isFinish, Integer currentGold,
			Integer access, String addTime) throws WEBException {
		// TODO Auto-generated method stub
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyLogInfo sl = slDao.getEntityById(sess, id);
			if(sl != null){
				sl.setStep(step);
				sl.setStepComplete(stepComplete);
				sl.setIsFinish(isFinish);
				sl.setCurrentGold(currentGold);
				sl.setAccess(access);
				sl.setAddTime(addTime);
				slDao.update(sess, sl);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改学习记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public StudyLogInfo getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return slDao.getEntityById(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取指定编号的学习记录实体时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyLogInfo> listStudyLogInfoByOpt(Integer userId,
			Integer subId, Integer logType, String sDate, String eDate)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return slDao.findStuLogByOpt(sess, userId, subId, logType, sDate, eDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件获取学习记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyLogInfo> listLastStudyInfoByOpt(Integer userId,
			Integer loreId, Integer logType) throws WEBException {
		// TODO Auto-generated method stub
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return slDao.findLastStudyInfoByOpt(sess, userId, loreId, logType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据学生编号、知识点编号获取最后一次学习记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
