package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.NetTeacherInfoDao;
import com.zsd.dao.QuestionInfoDao;
import com.zsd.dao.SubjectDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.NetTeacherInfo;
import com.zsd.module.QuestionInfo;
import com.zsd.module.Subject;
import com.zsd.module.User;
import com.zsd.service.QuestionInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

/**
 * @author zong
 * @version 2019-5-20 上午09:41:59
 */
public class QuestionInfoManagerImpl implements QuestionInfoManager {
	SubjectDao sDao = null;
	NetTeacherInfoDao ntDao = null;
	UserDao uDao = null;
	QuestionInfoDao qDao = null;
	Transaction tran = null;

	@Override
	public List<QuestionInfo> listInfoByOpt(Integer userId,Integer subId, Integer readStatus,
			Integer pageNo, Integer pageSize) throws WEBException {
		try {
			qDao = (QuestionInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_QUESTION_INFO);
			Session sess  = HibernateUtil.currentSession();
			return qDao.findInfoByOpt(sess,userId, subId, readStatus, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据学科,回复状态获取问题信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getInfoByOptCount(Integer userId,Integer subId, Integer readStatus)
			throws WEBException {
		try {
			qDao = (QuestionInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_QUESTION_INFO);
			Session sess  = HibernateUtil.currentSession();
			return qDao.getInfoByOptCount(sess,userId, subId, readStatus);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据学科,回复状态获取问题记录数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer adddQue(Integer subId, Integer userId, Integer ntId,
			String queTitle, String queContent,String queImg,String queTime,
			String queReplyContent, String queReplyTime, Integer readStatus)
			throws WEBException {
		try {
			qDao = (QuestionInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_QUESTION_INFO);
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			sDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			QuestionInfo qInfo = new QuestionInfo();
			Subject subject=sDao.get(sess, subId);
			qInfo.setSubject(subject);
			User user = uDao.get(sess, userId);
			qInfo.setUser(user);
			NetTeacherInfo netTeacherInfo=ntDao.get(sess, ntId);
			qInfo.setNetTeacherInfo(netTeacherInfo);
			qInfo.setQueTitle(queTitle);
			qInfo.setQueContent(queContent);
			qInfo.setQueImg(queImg);
			qInfo.setQueTime(queTime);
			qInfo.setQueReplyContent(queReplyContent);
			qInfo.setQueReplyTime(queReplyTime);
			qInfo.setReadStatus(readStatus);
			qDao.save(sess, qInfo);
			tran.commit();
			return qInfo.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加我的提问时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateQue(Integer qId, String queReplyContent,String queReplyImg,
			String queReplyTime, Integer readStatus) throws WEBException {
		try {
			qDao = (QuestionInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_QUESTION_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			QuestionInfo qInfo = qDao.get(sess, qId);
			if(qInfo != null){
				qInfo.setQueReplyContent(queReplyContent);
				qInfo.setQueReplyImg(queReplyImg);
				qInfo.setQueReplyTime(queReplyTime);
				qInfo.setReadStatus(readStatus);
				qDao.update(sess, qInfo);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("回复指定问题 回复内容,回复时间,回复状态时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<QuestionInfo> findInfoByntId(Integer ntId) throws WEBException {
		try {
			qDao = (QuestionInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_QUESTION_INFO);
			Session sess  = HibernateUtil.currentSession();
			return qDao.findInfoByntId(sess, ntId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师编号获取问题信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<QuestionInfo> listInfoByStu(Integer userId,Integer stuId, Integer readStatus,
			Integer pageNo, Integer pageSize) throws WEBException {
		try {
			qDao = (QuestionInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_QUESTION_INFO);
			Session sess  = HibernateUtil.currentSession();
			return qDao.findInfoByStu(sess,userId, stuId, readStatus, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据学生编号,回复状态获取问题信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getInfoByStuCount(Integer userId,Integer stuId, Integer readStatus)
			throws WEBException {
		try {
			qDao = (QuestionInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_QUESTION_INFO);
			Session sess  = HibernateUtil.currentSession();
			return qDao.getInfoByStuCount(sess,userId, stuId, readStatus);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据学生编号,回复状态获取问题记录数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<QuestionInfo> listInfoById(Integer qId) throws WEBException {
		try {
			qDao = (QuestionInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_QUESTION_INFO);
			Session sess  = HibernateUtil.currentSession();
			return qDao.findInfoById(sess, qId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据主键获取问题信息详情时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
