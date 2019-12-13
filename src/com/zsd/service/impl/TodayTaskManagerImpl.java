package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.LoreInfoDao;
import com.zsd.dao.TodayTaskDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.TodayTask;
import com.zsd.service.TodayTaskManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class TodayTaskManagerImpl implements TodayTaskManager{

	UserDao uDao = null;
	LoreInfoDao lDao = null;
	TodayTaskDao ttDao = null;
	Transaction tran = null;
	@Override
	public Integer addTTask(Integer stuId, Integer loreId, Integer zdxzdFlag,
			Integer studyFlag, Integer zczdFlag, Integer studyTimes,
			Integer zczdTimes) throws WEBException {
		// TODO Auto-generated method stub
		try {
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			ttDao = (TodayTaskDao) DaoFactory.instance(null).getDao(Constants.DAO_TODAY_TASK_INFO);
			Session sess = HibernateUtil.currentSession();
			tran  = sess.beginTransaction();
			TodayTask tt = new TodayTask(lDao.getEntityById(sess, loreId), zdxzdFlag, studyFlag,
					zczdFlag, studyTimes, zczdTimes,
					CurrentTime.getStringDate(), uDao.get(sess, stuId), "",0, "");
			ttDao.save(sess, tt);
			tran.commit();
			return tt.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加今日任务信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateEntityById(Integer id, Integer zdxzdFlag,
			Integer studyFlag, Integer zczdFlag, Integer studyTimes,
			Integer zczdTimes) throws WEBException {
		// TODO Auto-generated method stub
		try {
			ttDao = (TodayTaskDao) DaoFactory.instance(null).getDao(Constants.DAO_TODAY_TASK_INFO);
			Session sess = HibernateUtil.currentSession();
			tran  = sess.beginTransaction();
			TodayTask tt = ttDao.get(sess, id);
			if(tt != null){
				if(zdxzdFlag > -1){
					tt.setZdxzdFlag(zdxzdFlag);
				}
				if(studyFlag > -1){
					tt.setStudyFlag(studyFlag);				
								}
				if(zczdFlag > -1){
					tt.setZczdFlag(zczdFlag);
				}
				if(studyTimes > -1){
					tt.setStudyTimes(studyTimes);
				}
				if(zczdTimes > -1){
					tt.setZczdTimes(zczdTimes);
				}
				ttDao.update(sess, tt);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改今日任务信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateStatusById(Integer id, String reviewDate,
			Integer parentUserId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			ttDao = (TodayTaskDao) DaoFactory.instance(null).getDao(Constants.DAO_TODAY_TASK_INFO);
			Session sess = HibernateUtil.currentSession();
			tran  = sess.beginTransaction();
			TodayTask tt = ttDao.get(sess, id);
			if(tt != null){
				if(!reviewDate.equals("")){
					tt.setReviewData(CurrentTime.getCurrentTime());
				}else if(parentUserId > 0){
					tt.setParentUserId(parentUserId);
					tt.setParentConfirmDate(CurrentTime.getCurrentTime());
				}
				ttDao.update(sess, tt);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改今日任务复习/确认信息信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public TodayTask getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			ttDao = (TodayTaskDao) DaoFactory.instance(null).getDao(Constants.DAO_TODAY_TASK_INFO);
			Session sess = HibernateUtil.currentSession();
			return ttDao.get(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键获取信息实体时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<TodayTask> listInfoByOpt(Integer stuId, Integer loreId,
			String addDate, Integer reviewStatus, Integer confirmStatus)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			ttDao = (TodayTaskDao) DaoFactory.instance(null).getDao(Constants.DAO_TODAY_TASK_INFO);
			Session sess = HibernateUtil.currentSession();
			return ttDao.findInfoByOpt(sess, stuId, loreId, addDate, reviewStatus, confirmStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件获取今日任务列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
