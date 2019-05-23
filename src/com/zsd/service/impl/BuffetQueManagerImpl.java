package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetAllDao;
import com.zsd.dao.BuffetQueInfoDao;
import com.zsd.dao.LoreInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.BuffetQueInfo;
import com.zsd.service.BuffetQueInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class BuffetQueManagerImpl implements BuffetQueInfoManager{

	BuffetAllDao baDao = null;
	LoreInfoDao lDao = null;
	BuffetQueInfoDao bqDao = null;
	Transaction tran = null;
	@Override
	public Integer addBQ(Integer btId, Integer loreId, Integer num,
			String title, String subject, String answer, Integer lexId,
			Integer tipsId, String resolution, String queType, Integer order,
			String answerA, String answerB, String answerC, String answerD,
			String answerE, String answerF, String operateUserName,
			String operateDate) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bqDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetQueInfo bq = new BuffetQueInfo(baDao.getBTEntityById(sess, btId),lDao.getEntityById(sess, loreId), num,
					title, subject, answer, lexId,tipsId, resolution, queType, order, answerA, answerA, answerA,
					answerA, answerA, answerA, 0,operateUserName, operateDate);
			bqDao.save(sess, bq);
			tran.commit();
			return bq.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加自助餐题库时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public BuffetQueInfo getEntityById(Integer buffetId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bqDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			return bqDao.getEntityById(sess, buffetId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键获取自助餐题库详情时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetQueInfo> listInfoByOpt(Integer loreId,
			Integer buffetType, Integer inUse) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bqDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			return bqDao.findInfoByOpt(sess, loreId, buffetType, inUse,false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据知识点名称、自助餐基础类型、开启状态获取自助餐题库列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetQueInfo> listPageInfoByLoreId(Integer loreId, int pageNo,
			int pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bqDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			return bqDao.findPageInfoByLoreId(sess, loreId, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("分页获取指定知识点目录下的自助餐题库列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByLoreId(Integer loreId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bqDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			return bqDao.getCountByLoreId(sess, loreId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("分页获取指定知识点目录下的自助餐题库记录条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateInUseStatusById(Integer buffetId, Integer inUse,
			String operateUserName, String operateDate) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bqDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetQueInfo bq = bqDao.getEntityById(sess, buffetId);
			if(bq != null){
				bq.setInUse(inUse);
				bq.setOperateUserName(operateUserName);
				bq.setOperateDate(operateDate);
				bqDao.update(sess, bq);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改自助餐题库启用状态时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public BuffetQueInfo getCurrMaxNumAndOrderByOpt(Integer loreId, Integer btId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			bqDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			List<BuffetQueInfo> bqList =  bqDao.findInfoByOpt(sess, loreId, btId, -1, true);
			if(bqList.size() > 0){
				return bqList.get(0);
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取指定知识点编号和指定基础类型下的最后一个巴菲特题记录(获取下一个最大的的num和order)时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateInfoById(Integer id,String queSub, String queAnswer,
			Integer queTipId, Integer lexId, String queResolution,
			String queType, String a, String b, String c, String d, String e,
			String f, String operateUserName, String operateDate)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			bqDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetQueInfo bq = bqDao.getEntityById(sess, id);
			if(bq != null){
				bq.setSubject(queSub);
				bq.setAnswer(queAnswer);
				bq.setTips(queTipId);
				bq.setLexId(lexId);
				bq.setResolution(queResolution);
				bq.setQueType(queType);
				bq.setA(a);
				bq.setB(b);
				bq.setC(c);
				bq.setD(d);
				bq.setE(e);
				bq.setF(f);
				bq.setOperateUserName(operateUserName);
				bq.setOperateDate(operateDate);
				bqDao.update(sess, bq);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("修改自助餐题库时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
