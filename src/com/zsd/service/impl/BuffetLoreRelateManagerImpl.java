package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetLoreRelateInfoDao;
import com.zsd.dao.BuffetQueInfoDao;
import com.zsd.dao.EditionDao;
import com.zsd.dao.LoreInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.BuffetLoreRelateInfo;
import com.zsd.service.BuffetLoreRelateInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class BuffetLoreRelateManagerImpl implements BuffetLoreRelateInfoManager{

	BuffetQueInfoDao bDao = null;
	LoreInfoDao lDao = null;
	EditionDao eDao = null;
	BuffetLoreRelateInfoDao blrDao = null;
	Transaction tran = null;
	@Override
	public Integer addBLR(Integer buffetId, Integer loreId,Integer ediId,Integer currLoreId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			blrDao = (BuffetLoreRelateInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetLoreRelateInfo blr = new BuffetLoreRelateInfo(eDao.get(sess, ediId), lDao.getEntityById(sess, currLoreId),
					lDao.getEntityById(sess, loreId), bDao.getEntityById(sess, buffetId));
			blrDao.save(sess, blr);
			tran.commit();
			return blr.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加自助餐知识点关联信息");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean delBLRById(Integer blrId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			blrDao = (BuffetLoreRelateInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetLoreRelateInfo blr = blrDao.getEntityById(sess, blrId);
			if(blr != null){
				blrDao.delete(sess, blr);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("删除指定主键的自助餐知识点关联信息");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetLoreRelateInfo> listInfoByBuffetId(Integer buffetId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			blrDao = (BuffetLoreRelateInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			return blrDao.findInfoByOpt(sess, buffetId, 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取指定自助餐提题关联的知识点列表（知识编码降序排列）信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetLoreRelateInfo> listInfoByOpt(Integer buffetId, Integer loreId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			blrDao = (BuffetLoreRelateInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			return blrDao.findInfoByOpt(sess, buffetId, loreId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据自助餐编号、知识点编号获取自助餐知识点关联信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
