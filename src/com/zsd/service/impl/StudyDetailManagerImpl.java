package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.LoreInfoDao;
import com.zsd.dao.LoreQuestionDao;
import com.zsd.dao.StudyDetailDao;
import com.zsd.dao.StudyLogDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.StudyDetailInfo;
import com.zsd.service.StudyDetailManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class StudyDetailManagerImpl implements StudyDetailManager{

	UserDao uDao = null;
	StudyLogDao slDao = null;
	LoreInfoDao lDao = null;
	LoreQuestionDao lqDao = null;
	StudyDetailDao sdDao = null;
	Transaction tran = null;
	@Override
	public Integer addStudyDetail(Integer userId, Integer studyLogId,
			Integer loreId, Integer loreQuestionId, Integer questionStep,
			String realAnswer, Integer result, String addTime, String myAnswer,
			String a, String b, String c, String d, String e, String f,
			Integer completeTimes) throws WEBException {
		// TODO Auto-generated method stub
		try {
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			sdDao = (StudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyDetailInfo sd = new StudyDetailInfo(slDao.getEntityById(sess, studyLogId),
					lqDao.getEntityById(sess, loreQuestionId), uDao.get(sess, userId), lDao.getEntityById(sess, loreId),
					questionStep, result, addTime, myAnswer,realAnswer, a, b, c, d,e, f, completeTimes);
			sdDao.save(sess, sd);
			tran.commit();
			return sd.getId();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("增加学习记录详情时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyDetailInfo> listInfoByLogId(Integer studyLogId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			sdDao = (StudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return sdDao.findInfoByLogId(sess, studyLogId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号获取学习详情列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyDetailInfo> listCurrentRightInfoByLogId(Integer studyLogId, Integer loreId, String loreTypeName)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			sdDao = (StudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return sdDao.findCurrentRightInfoByLogId(sess, studyLogId, loreId, loreTypeName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号获取当前级知识点所有答对的指定答题类型记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyDetailInfo> listLastInfoByLogId(Integer studyLogId,
			Integer loreId, String loreTypeName) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sdDao = (StudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return sdDao.findLastInfoByLogId(sess, studyLogId, loreId, loreTypeName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号和知识点编号和知识点诊断类型获取最后一条数据列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyDetailInfo> listPretRightInfoByLogId(Integer studyLogId,
			Integer loreId, String loreTypeName, Integer completeTimes)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			sdDao = (StudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return sdDao.findPretRightInfoByLogId(sess, studyLogId, loreId, loreTypeName, completeTimes);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号获取当前级知识点所有答对的再次诊断并且不是当前阶段所做的记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyDetailInfo> listInfoByOpt(Integer studyLogId,
			Integer loreId, String loreTypeName) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sdDao = (StudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return sdDao.findInfoByOpt(sess, studyLogId, loreId, loreTypeName);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号和知识点编号和知识点诊断类型获取已做题列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyDetailInfo> listLastInfoByOpt(Integer studyLogId,
			Integer loreId, String loreTypeName, Integer completeTimes)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			sdDao = (StudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return sdDao.findLastInfoByOpt(sess, studyLogId, loreId, loreTypeName, completeTimes);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号和知识点编号和知识点诊断类型获和完成次数取最后一次答题数据列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean checkSuccCompleteFlag(Integer studyLogId, Integer lqId,
			String currDate) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sdDao = (StudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return sdDao.checkSuccCompleteFlag(sess, studyLogId, lqId, currDate);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("检查同一学习记录当天不能成功2次以上的记录情况【针对性诊断+再次诊断】（防止用户恶意提交赚取金币）时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyDetailInfo> listInfoByOpt(Integer studyLogId, Integer lqId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			sdDao = (StudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return sdDao.findInfoByOpt(sess, studyLogId, lqId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据学习记录编号、题库编号获取做题信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
