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
import com.zsd.tools.CurrentTime;
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
				if(!step.equals(-1)){
					sl.setStep(step);
				}
				if(!stepComplete.equals(-1)){
					sl.setStepComplete(stepComplete);
				}
				if(!isFinish.equals(-1)){
					sl.setIsFinish(isFinish);
				}
				if(!currentGold.equals(-1)){
					sl.setCurrentGold(currentGold);
				}
				if(!access.equals(-1)){
					sl.setAccess(access);
				}
				if(!addTime.equals("")){
					sl.setAddTime(addTime);
				}
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

	@Override
	public boolean updateLogStatus(Integer id, Integer step,
			Integer stepComplete, Integer isFinish, Integer access,
			Integer taskNumber) throws WEBException {
		// TODO Auto-generated method stub
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyLogInfo sl = slDao.getEntityById(sess, id);
			if(sl != null){
				if(step > 0){
					sl.setStep(step);
				}
				if(taskNumber > 0){
					sl.setTaskNumber(taskNumber);
				}
				sl.setStepComplete(stepComplete);
				sl.setIsFinish(isFinish);
				sl.setAccess(access);
				sl.setAddTime(CurrentTime.getCurrentTime());
				slDao.update(sess, sl);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改指定logId的step,stepComplete,isFinish,access状态时异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean addSysAssess(Integer id, String sysAssess, Integer finalScore)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyLogInfo sl = slDao.getEntityById(sess, id);
			if(sl != null){
				sl.setSysAssess(sysAssess);
				sl.setFinalScore(finalScore);
				slDao.update(sess, sl);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加系统评价时异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean addTeaAssess(Integer id, String teaAssess)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyLogInfo sl = slDao.getEntityById(sess, id);
			if(sl != null){
				sl.setTeaAssess(teaAssess);
				slDao.update(sess, sl);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加导师评价时异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyLogInfo> listSlInfoByopt(Integer userId, Integer subId,
			Integer isfinish, Integer logType, String sDate, String eDate)throws WEBException{
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return slDao.findStuLogByOption(sess, userId, subId, isfinish, logType, sDate, eDate);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据学科编号,学生编号  类型,完成状态,时间段获取学习记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyLogInfo> listStuLogByOption(Integer userId,Integer subId, String sDate,
			String eDate) throws WEBException {
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return slDao.findStuLogByOption(sess, userId, subId, sDate, eDate);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据学生编号,学科编号,时间段获取指定学生完成学习记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyLogInfo> listStuLogByStu(Integer stuId,Integer subId,Integer guideSta,String sDate, String eDate) throws WEBException {
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return slDao.findStuLogByStu(sess, stuId, subId, guideSta, sDate, eDate);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据学生编号,学科编号,指导状态,时间段获取指定学生跟踪指导记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
