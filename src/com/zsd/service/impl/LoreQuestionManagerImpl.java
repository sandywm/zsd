package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.LoreInfoDao;
import com.zsd.dao.LoreQuestionDao;
import com.zsd.dao.LoreQuestionSubDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.LoreQuestion;
import com.zsd.module.LoreQuestionSubInfo;
import com.zsd.service.LoreQuestionManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class LoreQuestionManagerImpl implements LoreQuestionManager{

	LoreQuestionSubDao lqsDao = null;
	LoreQuestionDao lqDao = null;
	LoreInfoDao lDao = null;
	Transaction tran = null;
	@Override
	public Integer addLoreQuestion(Integer loreId, String loreTypeName,
			Integer queNum, String queTitle, String queSub, String queAnswer,
			Integer queTipId, Integer lexId, String queResolution,
			String queType, Integer queOrder, String queType2, String a,
			String b, String c, String d, String e, String f,
			String operateUserName, String operateDate, Integer queClassTeaId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreQuestion lq = new LoreQuestion(lDao.getEntityById(sess, loreId) , loreTypeName,
					"", queNum, queTitle,queSub, queAnswer,  queTipId,  lexId,
					queResolution, queType,  queOrder,queType2, a, b, c, d, e,
					f,  0, operateUserName,operateDate,  queClassTeaId);
			lqDao.save(sess, lq);
			tran.commit();
			return lq.getId();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("增加指定知识点下的题库时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateLoreQuestion(Integer id, String queSub, String queAnswer, Integer queTipId, Integer lexId,
			String queResolution, String queType,
			String queType2, String a, String b, String c, String d, String e,
			String f, String operateUserName, String operateDate) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreQuestion lq = lqDao.getEntityById(sess, id);
			if(lq != null){
				lq.setQueSub(queSub);
				lq.setQueAnswer(queAnswer);
				lq.setQueTips(queTipId);
				lq.setLexId(lexId);
				lq.setQueResolution(queResolution);
				lq.setQueType(queType);
				lq.setQueType2(queType2);
				lq.setA(a);
				lq.setB(b);
				lq.setC(c);
				lq.setD(d);
				lq.setE(e);
				lq.setF(f);
				lq.setOperateUserName(operateUserName);
				lq.setOperateDate(operateDate);
				lqDao.update(sess, lq);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("修改指定题库时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateInUseStatusById(Integer id, Integer inUse,
			String operateUserName, String operateDate) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreQuestion lq = lqDao.getEntityById(sess, id);
			if(lq != null){
				lq.setInUse(inUse);
				lq.setOperateUserName(operateUserName);
				lq.setOperateDate(operateDate);
				lqDao.update(sess, lq);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("修改指定题库的开启状态时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreQuestion> listInfoByLoreId(Integer loreId, String loreType,
			Integer inUse) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqDao.findInfoByOpt(sess, loreId, loreType, inUse);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据知识点编号、知识点类型(可为空),有效状态,获取题库记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreQuestion> listPageInfoByLoreId(Integer loreId, Integer pageNo, Integer pageSize)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqDao.findPageInfoByLoreId(sess, loreId, pageNo, pageSize);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("分页获取指定知识点下的题库列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByLoreId(Integer loreId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqDao.getCountByLoreId(sess, loreId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("分页获取指定知识点下的题库记录条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public LoreQuestion getEntityByLqId(Integer lqId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqDao.getEntityById(sess, lqId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据题库编号获取指定题库详情时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer addSimpleLoreQuestion(Integer loreId, String loreType,String queTitle,
			String queSub, Integer queNum,Integer queOrder, String queAnswer,
			String queResolution, String operateUserName, String operateDate)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreQuestion lq = new LoreQuestion(lDao.getEntityById(sess, loreId) , loreType,
					"", queNum, queTitle,queSub, queAnswer,  0,  0,
					queResolution, "",  queOrder,"", "", "", "", "", "",
					"",  0, operateUserName,operateDate,  0);
			lqDao.save(sess, lq);
			tran.commit();
			return lq.getId();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("增加知识清单，点拨指导、解题示范、知识讲解题库内容时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateSimpleLoreQuestionByLqId(Integer lqId, String queSub,
			String queAnswer, String queResolution, String operateUserName,
			String operateDate) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreQuestion lq = lqDao.getEntityById(sess, lqId);
			if(lq != null){
				lq.setQueSub(queSub);
				if(!queResolution.equals("")){
					lq.setQueResolution(queResolution);
				}
				lq.setQueAnswer(queAnswer);
				lq.setOperateDate(operateDate);
				lq.setOperateUserName(operateUserName);
				lqDao.update(sess, lq);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("修改解题示范、知识讲解内容时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer addLoreQuestionSubInfo(Integer loreQuestionId,
			String loreType, String lqsTitle, String lqsCon, Integer order,
			String operateUserName, String operateDate) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			lqsDao = (LoreQuestionSubDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_SUB_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreQuestionSubInfo lqs = new LoreQuestionSubInfo(lqDao.getEntityById(sess, loreQuestionId), loreType,
					"", lqsTitle, lqsCon,order, operateUserName, operateDate);
			lqsDao.save(sess, lqs);
			tran.commit();
			return lqs.getId();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("增加指定知识点下的知识清单和点拨指导题库子表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateLoreQuestionSubByLqsId(Integer lqsId, String lqsTitle,
			String lqsCon, String operateUserName, String operateDate)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			lqsDao = (LoreQuestionSubDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_SUB_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreQuestionSubInfo lqs = lqsDao.getEntityById(sess, lqsId);
			if(lqs != null){
				lqs.setLqsTitle(lqsTitle);
				lqs.setLqsContent(lqsCon);
				lqs.setOperateDate(operateDate);
				lqs.setOperateUserName(operateUserName);
				lqsDao.update(sess, lqs);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("修改指定编号的知识清单、点拨指导题库子表信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean delLoreQuestionSubByLqsId(Integer lqsId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			lqsDao = (LoreQuestionSubDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_SUB_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreQuestionSubInfo lqs = lqsDao.getEntityById(sess, lqsId);
			if(lqs != null){
				lqsDao.delete(sess, lqs);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("删除指定题库子表信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreQuestionSubInfo> listLQSInfoByLqId(Integer lqId,String subType)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqsDao = (LoreQuestionSubDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_SUB_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqsDao.findInfoByOpt(sess, lqId,subType);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据知识点题库编号、子表类型获取知识点子表信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public LoreQuestionSubInfo getEntityByLqsId(Integer lqsId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqsDao = (LoreQuestionSubDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_SUB_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqsDao.getEntityById(sess, lqsId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据知识点题库子表编号获取子表详情时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreQuestionSubInfo> listInfoByLoreId(Integer loreId) throws WEBException{
		// TODO Auto-generated method stub
		try {
			lqsDao = (LoreQuestionSubDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_SUB_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqsDao.findInfoByLoreId(sess, loreId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("获取指定知识点下所有的知识清单和点拨指导的题库时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreQuestion> listInfoByLexId(Integer lexId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqDao.findInfoByLexId(sess, lexId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据词库编号获取题库列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreQuestion> listInfoByTipsId(Integer tipsId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqDao.findInfoByTipsId(sess, tipsId);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据提示编号获取题库列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateSimpleInfoByLqId(Integer lqId, Integer lexId,
			Integer tipsId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreQuestion lq = lqDao.getEntityById(sess, lqId);
			if(lq != null){
				if(tipsId >= 0){
					lq.setQueTips(tipsId);
				}
				if(lexId >= 0){
					lq.setLexId(lexId);
				}
				lqDao.update(sess, lq);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("修改指定题库的提示编号、词库编号时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean delLoreQuestionByLqId(Integer lqId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreQuestion lq = lqDao.getEntityById(sess, lqId);
			if(lq != null){
				lqDao.delete(sess, lq);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("删除指定题库主表信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public LoreQuestion getMaxNumInfoByOpt(Integer loreId, String loreType,
			Integer inUse) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lqDao = (LoreQuestionDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_QUESTION_INFO);
			Session sess = HibernateUtil.currentSession();
			return lqDao.getMaxNumInfoByOpt(sess, loreId, loreType, inUse);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("获取指定知识点下，指定类型的最大num值时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
