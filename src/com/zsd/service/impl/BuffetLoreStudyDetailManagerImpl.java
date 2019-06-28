package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetLoreStudyDetailDao;
import com.zsd.dao.BuffetLoreStudyLogDao;
import com.zsd.dao.BuffetStudyDetailDao;
import com.zsd.dao.LoreInfoDao;
import com.zsd.dao.LoreQuestionDao;
import com.zsd.dao.SubjectDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.BuffetLoreStudyDetailInfo;
import com.zsd.module.BuffetLoreStudyLogInfo;
import com.zsd.module.LoreInfo;
import com.zsd.module.LoreQuestion;
import com.zsd.service.BuffetLoreStudyDetailManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class BuffetLoreStudyDetailManagerImpl implements BuffetLoreStudyDetailManager{

	UserDao uDao = null;
	BuffetLoreStudyLogDao blslDao = null;
	LoreInfoDao lDao = null;
	LoreQuestionDao lqDao = null;
	BuffetLoreStudyDetailDao blsdDao = null;
	Transaction tran = null;
	@Override
	public Integer addBLSDetail(Integer userId, Integer studyLogId,
			Integer loreId, Integer loreQuestionId, Integer questionStep,
			String realAnswer, Integer result, String addTime, String myAnswer,
			String a, String b, String c, String d, String e, String f,
			Integer completeTimes) throws WEBException {
		// TODO Auto-generated method stub
		try {
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			blslDao = (BuffetLoreStudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_LOG_INFO);
			blsdDao = (BuffetLoreStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetLoreStudyDetailInfo blsd = new BuffetLoreStudyDetailInfo(blslDao.getEntityById(sess, studyLogId),
					lqDao.getEntityById(sess, loreQuestionId), lDao.getEntityById(sess, loreId), questionStep,
					result, addTime, realAnswer, myAnswer,a, b, c, d, e, f,completeTimes);
			blsdDao.save(sess, blsd);
			tran.commit();
			return blsd.getId();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("增加自助餐知识点学习详情信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetLoreStudyDetailInfo> listLastInfoByLogId(
			Integer studyLogId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			blsdDao = (BuffetLoreStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return blsdDao.findLastInfoByLogId(sess, studyLogId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号获取最后一次的自助餐知识点学习详情信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetLoreStudyDetailInfo> listInfoByLogId(Integer studyLogId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			blsdDao = (BuffetLoreStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return blsdDao.findInfoByLogId(sess, studyLogId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号获取自助餐知识点学习详情信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetLoreStudyDetailInfo> listLastInfoByOption(
			Integer buffetLoreStudyLogId, Integer currentLoreId,
			String loreType, Integer completeTimes) throws WEBException {
		// TODO Auto-generated method stub
		try {
			blsdDao = (BuffetLoreStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return blsdDao.findLastInfoByOpt(sess, buffetLoreStudyLogId, currentLoreId, loreType, completeTimes);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号和知识点编号和知识点诊断类型获和完成次数取最后一次答题数据列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetLoreStudyDetailInfo> listCurrentRightInfoByLogId(
			Integer studyLogId, Integer currentLoreId, String loreTypeName)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			blsdDao = (BuffetLoreStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return blsdDao.findCurrentRightInfoByLogId(sess, studyLogId, currentLoreId, loreTypeName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号获取当前答题正确的再次诊断题列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetLoreStudyDetailInfo> listPretRightInfoByLogId(
			Integer studyLogId, Integer currentLoreId, String loreTypeName,
			Integer completeTimes) throws WEBException {
		// TODO Auto-generated method stub
		try {
			blsdDao = (BuffetLoreStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return blsdDao.findPretRightInfoByLogId(sess, studyLogId, currentLoreId, loreTypeName, completeTimes);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号获取有无当前知识点指定类型并且不是当前阶段的答题记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetLoreStudyDetailInfo> listExistInfoByOption(
			Integer studyLogId, Integer currentLoreId, String loreTypeName)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			blsdDao = (BuffetLoreStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return blsdDao.findExistInfoByLogId(sess, studyLogId, currentLoreId, loreTypeName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号获取有无当前知识点指定类型的答题记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
