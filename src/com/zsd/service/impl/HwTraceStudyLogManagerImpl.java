package com.zsd.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.HwStudyTjDao;
import com.zsd.dao.HwTraceStudyLogDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.HwTraceStudyLogInfo;
import com.zsd.service.HwTraceStudyLogManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class HwTraceStudyLogManagerImpl implements HwTraceStudyLogManager{

	HwStudyTjDao tjDao = null;
	UserDao uDao = null;
	HwTraceStudyLogDao hwLogDao = null;
	Transaction tran = null;
	@Override
	public Integer addHwStudyLog(Integer tjId, Integer stuId, Integer loreId,
			Integer step, Integer stepComplete, Integer access, Integer taskNumber) throws WEBException {
		// TODO Auto-generated method stub
		try {
			tjDao = (HwStudyTjDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_STUDY_TJ_INFO);
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			hwLogDao = (HwTraceStudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			HwTraceStudyLogInfo logInfo = new HwTraceStudyLogInfo(tjDao.getEntityById(sess, tjId), uDao.getEntityById(sess, stuId).get(0),
					access, CurrentTime.getCurrentTime(), step, stepComplete,0, 1, "","", taskNumber, 0);
			hwLogDao.save(sess, logInfo);
			tran.commit();
			return logInfo.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加溯源学习记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateStudyLog(Integer id, Integer step,
			Integer stepComplete, Integer isFinish, Integer access,
			Integer taskNumber) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwLogDao = (HwTraceStudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			HwTraceStudyLogInfo logInfo = hwLogDao.getEntityById(sess, id);
			if(logInfo != null){
				if(step.equals(-1)){
					logInfo.setStep(step);
				}
				if(stepComplete.equals(-1)){
					logInfo.setStepComplete(stepComplete);			
				}
				if(isFinish.equals(-1)){
					logInfo.setIsFinish(isFinish);
				}
				if(access.equals(-1)){
					logInfo.setAccess(access);
				}
				if(taskNumber.equals(-1)){
					logInfo.setTaskNumber(taskNumber);
				}
				hwLogDao.update(sess, logInfo);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改家庭作业溯源学习记录信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public HwTraceStudyLogInfo getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwLogDao = (HwTraceStudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return hwLogDao.getEntityById(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键获取溯源学习记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public HwTraceStudyLogInfo getEntityByTjId(Integer tjId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwLogDao = (HwTraceStudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return hwLogDao.getEntityByTjId(sess, tjId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据统计编号获取溯源学习记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
