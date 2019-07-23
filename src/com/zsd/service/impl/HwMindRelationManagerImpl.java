package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetAllDao;
import com.zsd.dao.HwMindRelationDao;
import com.zsd.dao.HwQueDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.HwMindRelationInfo;
import com.zsd.module.HwQueInfo;
import com.zsd.service.HwMindRelationManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class HwMindRelationManagerImpl implements HwMindRelationManager{

	HwQueDao hqDao = null;
	BuffetAllDao baDao = null;
	HwMindRelationDao hmrDao = null;
	Transaction tran = null;
	@Override
	public boolean addHMR(String bmtIdStr, Integer hwId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hqDao = (HwQueDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_QUE_INFO);
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			hmrDao = (HwMindRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_MIND_RELATION_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			HwQueInfo hq = hqDao.getEntityById(sess, hwId);
			if(!bmtIdStr.equals("")){
				for(Integer i = 0 ; i < bmtIdStr.split(",").length ; i++){
					Integer bmtId = Integer.parseInt(bmtIdStr.split(",")[i]);
					HwMindRelationInfo hmr = new HwMindRelationInfo(baDao.getBMTEntityById(sess, bmtId),hq);
					hmrDao.save(sess, hmr);
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
			throw new WEBException("增加系统家庭作业思维关联信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwMindRelationInfo> listInfoByOpt(Integer bmtId, Integer hwId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			hmrDao = (HwMindRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_MIND_RELATION_INFO);
			Session sess = HibernateUtil.currentSession();
			return hmrDao.findInfoByOpt(sess, hwId, bmtId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据思维类型编号、家庭作业编号获取系统家庭作业思维类型关联信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean delHMR(Integer hwId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hmrDao = (HwMindRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_MIND_RELATION_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			List<HwMindRelationInfo>  hmrList = hmrDao.findInfoByOpt(sess, hwId, 0);
			if(hmrList.size() > 0){
				for(Integer i = 0 ; i < hmrList.size() ; i++){
					hmrDao.delete(sess, hmrList.get(i));
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
			throw new WEBException("根据家庭作业编号批量删除系统家庭作业思维类型关联信息记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
