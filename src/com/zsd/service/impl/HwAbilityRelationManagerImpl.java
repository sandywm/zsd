package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetAllDao;
import com.zsd.dao.HwAbilityRelationDao;
import com.zsd.dao.HwQueDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.HwAbilityRelationInfo;
import com.zsd.module.HwQueInfo;
import com.zsd.service.HwAbilityRelationManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class HwAbilityRelationManagerImpl implements HwAbilityRelationManager{

	HwQueDao hqDao = null;
	BuffetAllDao baDao = null;
	HwAbilityRelationDao harDao = null;
	Transaction tran = null;
	@Override
	public boolean addHAR(String batIdStr, Integer hwId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hqDao = (HwQueDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_QUE_INFO);
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			harDao = (HwAbilityRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_ABILITY_RELATION_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			HwQueInfo hq = hqDao.getEntityById(sess, hwId);
			if(!batIdStr.equals("")){
				for(Integer i = 0 ; i < batIdStr.split(",").length ; i++){
					Integer batId = Integer.parseInt(batIdStr.split(",")[i]);
					HwAbilityRelationInfo har = new HwAbilityRelationInfo(baDao.getBATEntityById(sess, batId),hq);
					harDao.save(sess, har);
					if(i % 10 == 0){//批插入的对象立即写入数据库并释放内存
						sess.flush();
						sess.clear();
						tran.commit();
						tran = sess.beginTransaction();
					}
				}
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加系统家庭作业能力关联信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwAbilityRelationInfo> listInfoByOpt(Integer batId, Integer hwId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			harDao = (HwAbilityRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_ABILITY_RELATION_INFO);
			Session sess = HibernateUtil.currentSession();
			return harDao.findInfoByOpt(sess, hwId, batId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据能力类型编号、家庭作业编号获取系统家庭作业能力类型关联信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean delHAR(Integer hwId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			harDao = (HwAbilityRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_ABILITY_RELATION_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			List<HwAbilityRelationInfo> harList = harDao.findInfoByOpt(sess, hwId, 0);
			if(harList.size() > 0){
				for(Integer i = 0 ; i < harList.size() ; i++){
					harDao.delete(sess, harList.get(i));
					if(i % 10 == 0){//批插入的对象立即写入数据库并释放内存
						sess.flush();
						sess.clear();
						tran.commit();
						tran = sess.beginTransaction();
					}
				}
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据家庭作业编号批量删除系统家庭作业能力类型关联信息记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
