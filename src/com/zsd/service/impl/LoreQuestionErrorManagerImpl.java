package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.LoreQuestionDao;
import com.zsd.dao.LoreQuestionErrorDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.LoreQuestionErrorInfo;
import com.zsd.service.LoreQuestionErrorManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class LoreQuestionErrorManagerImpl implements LoreQuestionErrorManager{

	UserDao uDao = null;
	LoreQuestionDao lqDao = null;
	LoreQuestionErrorDao lqeDao = null;
	Transaction tran = null;
	@Override
	public LoreQuestionErrorInfo getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqeDao = (LoreQuestionErrorDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_ERROR_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqeDao.get(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据编号获取知识点题库错误信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer addLQE(Integer userId, Integer loreQuestionId,
			String errorType, String content, String addDate)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			lqeDao = (LoreQuestionErrorDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_ERROR_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreQuestionErrorInfo lqe = new LoreQuestionErrorInfo(lqDao.getEntityById(sess, loreQuestionId), uDao.get(sess, userId),
					errorType, content, addDate, 1, 0,"", "","");
			lqeDao.save(sess, lqe);
			tran.commit();
			return lqe.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加知识点错误信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateLQE(Integer lqeId, Integer addCoinNumber,
			Integer updateStatus, String updateUserName, String updateDate,
			String remark) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqeDao = (LoreQuestionErrorDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_ERROR_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreQuestionErrorInfo lqe = lqeDao.get(sess, lqeId);
			if(lqe != null){
				lqe.setCoin(addCoinNumber);
				lqe.setCheckStatus(updateStatus);
				lqe.setOperateUserName(updateUserName);
				lqe.setOperateDate(updateDate);
				lqe.setRemark(remark);
				lqeDao.update(sess, lqe);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改指定编号的知识点错误信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreQuestionErrorInfo> listPageInfoByOptions(Integer userId, String sDate, String eDate,
			Integer checkStatus, String errorType, Integer updateStatus,
			Integer pageNo, Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqeDao = (LoreQuestionErrorDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_ERROR_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqeDao.findPageInfoByOpt(sess, errorType, sDate, eDate, checkStatus, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("分页获取错题列表信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByOptions(Integer userId, String sDate, String eDate, Integer checkStatus, String errorType,
			Integer updateStatus) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqeDao = (LoreQuestionErrorDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_ERROR_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqeDao.getCountByOpt(sess, errorType, sDate, eDate, checkStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取错题记录条数信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
