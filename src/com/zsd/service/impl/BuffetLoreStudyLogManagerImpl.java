package com.zsd.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetLoreStudyLogDao;
import com.zsd.dao.BuffetStudyDetailDao;
import com.zsd.dao.SubjectDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.BuffetLoreStudyLogInfo;
import com.zsd.service.BuffetLoreStudyLogManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class BuffetLoreStudyLogManagerImpl implements BuffetLoreStudyLogManager{

	UserDao uDao = null;
	BuffetStudyDetailDao bsdDao = null;
	SubjectDao sDao = null;
	BuffetLoreStudyLogDao blslDao = null;
	Transaction tran =  null;
	@Override
	public Integer addBLSLog(Integer userId, Integer buffetStudyDetailId,
			Integer subjectId, Integer step, Integer stepComplete,
			Integer isFinish, Integer currentGold, Integer access,
			String addTime, Integer taskNumber) throws WEBException {
		// TODO Auto-generated method stub
		try {
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			bsdDao = (BuffetStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_STUDY_DETAIL_INFO);
			sDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			blslDao = (BuffetLoreStudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetLoreStudyLogInfo blsl = new BuffetLoreStudyLogInfo(sDao.get(sess, subjectId), uDao.get(sess, userId),
					bsdDao.getEntityById(sess, buffetStudyDetailId), isFinish,
					access, addTime, step, stepComplete,taskNumber, currentGold);
			blslDao.save(sess, blsl);
			tran.commit();
			return blsl.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加自助餐知识点学习记录信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public BuffetLoreStudyLogInfo getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			blslDao = (BuffetLoreStudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return blslDao.getEntityById(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键获取自助餐知识点学习记录详情时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public BuffetLoreStudyLogInfo getEntityByBsdId(Integer bsdId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			blslDao = (BuffetLoreStudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return blslDao.getInfoByBsdId(sess, bsdId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据自助餐学习详情编号获取自助餐知识点学习记录详情时出现异常!");
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
			blslDao = (BuffetLoreStudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetLoreStudyLogInfo blsl = blslDao.getEntityById(sess, id);
			if(blsl != null){
				if(!step.equals(-1)){
					blsl.setStep(step);
				}
				if(!stepComplete.equals(-1)){
					blsl.setStepComlete(stepComplete);
				}
				if(!isFinish.equals(-1)){
					blsl.setIsFinish(isFinish);
				}
				if(!currentGold.equals(-1)){
					blsl.setCurrentGold(currentGold);
				}
				if(!access.equals(-1)){
					blsl.setAccess(access);
				}
				if(!addTime.equals("")){
					blsl.setAddTime(addTime);
				}
				blslDao.update(sess, blsl);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改自助餐知识点学习记录详情时出现异常!");
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
			blslDao = (BuffetLoreStudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetLoreStudyLogInfo blsl = blslDao.getEntityById(sess, id);
			if(blsl != null){
				if(step > 0){
					blsl.setStep(step);
				}
				if(taskNumber > 0){
					blsl.setTaskNumber(taskNumber);
				}
				blsl.setStepComlete(stepComplete);
				blsl.setIsFinish(isFinish);
				blsl.setAccess(access);
				blsl.setAddTime(CurrentTime.getCurrentTime());
				blslDao.update(sess, blsl);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键修改答题记录的本阶段的完成状态和整个知识典的完成状态时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
