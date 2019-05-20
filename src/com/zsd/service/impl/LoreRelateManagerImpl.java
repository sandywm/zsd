package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.LoreInfoDao;
import com.zsd.dao.LoreRelateDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.LoreRelateInfo;
import com.zsd.service.LoreRelateManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class LoreRelateManagerImpl implements LoreRelateManager{

	LoreInfoDao lDao = null;
	LoreRelateDao lrDao = null;
	Transaction tran = null;
	@Override
	public Integer addLoreRelate(Integer loreId, Integer rootLoreId,String addOpt)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			lrDao = (LoreRelateDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreRelateInfo lr = new LoreRelateInfo(lDao.getEntityById(sess, loreId),lDao.getEntityById(sess, rootLoreId),addOpt);
			lrDao.save(sess, lr);
			tran.commit();
			return lr.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加知识点关联信息表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean deleteLoreRelate(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			lrDao = (LoreRelateDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreRelateInfo lr = lrDao.get(sess, id);
			if(lr != null){
				lrDao.delete(sess, lr);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("删除指定主键的知识点关联信息表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateLoreRelate(Integer id, Integer loreId,
			Integer rootLoreId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			lrDao = (LoreRelateDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreRelateInfo lr = lrDao.get(sess, id);
			if(lr != null){
				lr.setLoreInfo(lDao.getEntityById(sess, loreId));
				lr.setRootLoreInfo(lDao.getEntityById(sess, rootLoreId));
				lrDao.update(sess, lr);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改指定主键编号的知识点关联信息表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreRelateInfo> listRelateInfoByOpt(Integer loreId,Integer rootLoreId,
			Integer loreInUse,String orderOpt) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lrDao = (LoreRelateDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lrDao.findIndoByLoreId(sess, loreId, rootLoreId, loreInUse, orderOpt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主知识点、关联知识点编号获取关联知识点信息列表（学生学习的时候需要loreInUse为开启状态）时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public LoreRelateInfo getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lrDao = (LoreRelateDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lrDao.get(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键获取实体信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreRelateInfo> listInfoByOpt(Integer subId, Integer ediId,
			String gradeNoArea) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lrDao = (LoreRelateDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			return lrDao.findInfoByOpt(sess, subId, ediId, gradeNoArea);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据学科编号、出版社编号、年级区间获取关联信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
