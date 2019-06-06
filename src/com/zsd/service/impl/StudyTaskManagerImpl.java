package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.StudyLogDao;
import com.zsd.dao.StudyTaskDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.StudyTaskInfo;
import com.zsd.service.StudyTaskManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class StudyTaskManagerImpl implements StudyTaskManager{

	StudyLogDao slDao = null;
	StudyTaskDao stDao = null;
	Transaction tran = null;
	
	@Override
	public Integer addSTask(Integer taskNum, Integer studyLogId,
			String taskName, Integer coin) throws WEBException {
		// TODO Auto-generated method stub
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			stDao = (StudyTaskDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_TASK_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyTaskInfo st = new StudyTaskInfo(slDao.getEntityById(sess, studyLogId), taskNum,
					taskName, coin, CurrentTime.getCurrentTime());
			stDao.save(sess, st);
			tran.commit();
			return st.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加学习任务信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateCoinInfoById(Integer id, Integer newAddCoin)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			stDao = (StudyTaskDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_TASK_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyTaskInfo st = stDao.getEntityById(sess, id);
			if(st != null){
				st.setCoin(st.getCoin() + newAddCoin);
				st.setAddTime(CurrentTime.getCurrentTime());
				stDao.update(sess, st);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改学习任务中奖励金币数信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyTaskInfo> listTaskInfoByOpt(Integer sLogId, String taskName)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			stDao = (StudyTaskDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_TASK_INFO);
			Session sess = HibernateUtil.currentSession();
			return stDao.findInfoByOpt(sess, sLogId, taskName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据学习记录编号、学习任务获取学习任务记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public StudyTaskInfo getLastInfoByLogId(Integer studyLogId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			stDao = (StudyTaskDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_TASK_INFO);
			Session sess = HibernateUtil.currentSession();
			return stDao.getLastInfoByLogId(sess, studyLogId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取最后一次的任务记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}


}
