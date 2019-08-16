package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.HwTraceStudyDetailDao;
import com.zsd.dao.HwTraceStudyLogDao;
import com.zsd.dao.LoreQuestionDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.HwTraceStudyDetailInfo;
import com.zsd.service.HwTraceStudyDetailManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class HwTraceStudyDetailManagerImpl implements HwTraceStudyDetailManager{

	UserDao uDao = null;
	HwTraceStudyLogDao hwLogDao = null;
	LoreQuestionDao lqDao = null;
	HwTraceStudyDetailDao hwDetailDao = null;
	Transaction tran = null;
	@Override
	public Integer addHTSDetail(Integer userId, Integer studyLogId,
			Integer lqId, Integer questionStep, String realAnswer,
			Integer result, String myAnswer, String a, String b, String c,
			String d, String e, String f, Integer completeTimes)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			hwDetailDao = (HwTraceStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_DETAIL_INFO);
			hwLogDao = (HwTraceStudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			HwTraceStudyDetailInfo detail = new HwTraceStudyDetailInfo(hwLogDao.getEntityById(sess, studyLogId),
					lqDao.getEntityById(sess, lqId), uDao.getEntityById(sess, userId).get(0), 
					result, CurrentTime.getCurrentTime(), questionStep, myAnswer,
					realAnswer, a, b, c, d,e, f, completeTimes);
			hwDetailDao.save(sess, detail);
			tran.commit();
			return detail.getId();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("增加家庭作业溯源学习记录明细时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwTraceStudyDetailInfo> listLastInfoByLogId(Integer studyLogId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwDetailDao = (HwTraceStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return hwDetailDao.findLastInfoByLogId(sess, studyLogId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("获取指定学习记录下最后一次的学习详情列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwTraceStudyDetailInfo> listInfoByLogId(Integer studyLogId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwDetailDao = (HwTraceStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return hwDetailDao.findInfoByLogId(sess, studyLogId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("获取指定学习记录下的学习详情列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwTraceStudyDetailInfo> listLastInfoByOption(
			Integer studyLogId, Integer currentLoreId, String loreType,
			Integer completeTimes) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwDetailDao = (HwTraceStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return hwDetailDao.findLastInfoByOpt(sess, studyLogId, currentLoreId, loreType, completeTimes);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号和知识点编号和知识点诊断类型获和完成次数取最后一次答题数据时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwTraceStudyDetailInfo> listCurrentRightInfoByLogId(
			Integer studyLogId, Integer currentLoreId, String loreTypeName)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwDetailDao = (HwTraceStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return hwDetailDao.findCurrentRightInfoByLogId(sess, studyLogId, currentLoreId, loreTypeName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号获取当前答题正确的再次诊断题时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwTraceStudyDetailInfo> listPretRightInfoByLogId(
			Integer studyLogId, Integer currentLoreId, String loreTypeName,
			Integer completeTimes) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwDetailDao = (HwTraceStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return hwDetailDao.findPretRightInfoByLogId(sess, studyLogId, currentLoreId, loreTypeName, completeTimes);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号获取有无当前知识点指定类型并且不是当前阶段的答题记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwTraceStudyDetailInfo> listExistInfoByOption(
			Integer studyLogId, Integer currentLoreId, String loreTypeName)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwDetailDao = (HwTraceStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return hwDetailDao.findExistInfoByLogId(sess, studyLogId, currentLoreId, loreTypeName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号获取有无当前知识点指定类型的答题记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getQuestionNumberByOption(Integer studyLogId, Integer lqId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwDetailDao = (HwTraceStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return hwDetailDao.getQuestionNumberByOption(sess, studyLogId, lqId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号和问题编号获取做该题的次数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public HwTraceStudyDetailInfo getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hwDetailDao = (HwTraceStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_TRACE_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return hwDetailDao.getEntityById(sess, id);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据主键获取信息实体时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
