package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetAllDao;
import com.zsd.dao.BuffetQueInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.BuffetAbilityRelationInfo;
import com.zsd.module.BuffetAbilityTypeInfo;
import com.zsd.module.BuffetMindRelationInfo;
import com.zsd.module.BuffetMindTypeInfo;
import com.zsd.module.BuffetQueInfo;
import com.zsd.module.BuffetTypeInfo;
import com.zsd.service.BuffetAllManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class BuffetAllManagerImpl implements BuffetAllManager{

	BuffetAllDao baDao = null;
	BuffetQueInfoDao bqDao = null;
	Transaction tran = null;
	
	@Override
	public BuffetTypeInfo getEntityByBtId(Integer btId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			Session sess = HibernateUtil.currentSession();
			return baDao.getBTEntityById(sess, btId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据基础类型编号获取基础类型实体时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	
	@Override
	public List<BuffetTypeInfo> listBTInfo() throws WEBException {
		// TODO Auto-generated method stub
		try {
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			Session sess = HibernateUtil.currentSession();
			return baDao.findBTInfo(sess);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取自助餐基础类型时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetMindTypeInfo> listBMTInfo() throws WEBException {
		// TODO Auto-generated method stub
		try {
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			Session sess = HibernateUtil.currentSession();
			return baDao.findBMTInfo(sess);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取自助餐思维类型时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetAbilityTypeInfo> listBATInfo() throws WEBException {
		// TODO Auto-generated method stub
		try {
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			Session sess = HibernateUtil.currentSession();
			return baDao.findBATInfo(sess);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取自助餐能力类型时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean addBMR(Integer buffetId, String bmtIdStr)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			bqDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetQueInfo bq = bqDao.getEntityById(sess, buffetId);
			for(Integer i = 0 ; i < bmtIdStr.split(",").length ; i++){
				Integer bmtId = Integer.parseInt(bmtIdStr.split(",")[i]);
				BuffetMindRelationInfo bmr = new BuffetMindRelationInfo(baDao.getBMTEntityById(sess, bmtId),bq);
				baDao.saveBMR(sess, bmr);
			}
			sess.flush();
			sess.clear();
			tran.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加自助餐思维关联信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void delBMR(String bmrIdStr) throws WEBException {
		// TODO Auto-generated method stub
		try {
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			if(!bmrIdStr.equals("")){
				String[] bmrIdArr = bmrIdStr.split(",");
				for(int i = 0 ; i < bmrIdArr.length ; i++){
					Integer bmrId = Integer.parseInt(bmrIdArr[i]);
					baDao.delBMRById(sess, bmrId);
					if(i % 10 == 0){//批插入的对象立即写入数据库并释放内存
						sess.flush();
						sess.clear();
						tran.commit();
						tran = sess.beginTransaction();
					}
				}
			}
			tran.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("删除指定自助餐编号的自助餐思维关联信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetMindRelationInfo> listBMRInfoByBuffetId(Integer buffetId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			Session sess = HibernateUtil.currentSession();
			return baDao.findBMRInfoByBuffetId(sess, buffetId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据自助餐编号获取自助餐思维类型关联信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean addBAR(Integer buffetId, String batIdStr)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			bqDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetQueInfo bq = bqDao.getEntityById(sess, buffetId);
			for(Integer i = 0 ; i < batIdStr.split(",").length ; i++){
				Integer batId = Integer.parseInt(batIdStr.split(",")[i]);
				BuffetAbilityRelationInfo bar = new BuffetAbilityRelationInfo(baDao.getBATEntityById(sess, batId),bq);
				baDao.saveBAR(sess, bar);
			}
			sess.flush();
			sess.clear();
			tran.commit();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加自助餐能力关联信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void delBAR(String barIdStr) throws WEBException {
		// TODO Auto-generated method stub
		try {
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			if(!barIdStr.equals("")){
				String[] barIdArr = barIdStr.split(",");
				for(int i = 0 ; i < barIdArr.length ; i++){
					Integer bmrId = Integer.parseInt(barIdArr[i]);
					baDao.delBMRById(sess, bmrId);
					if(i % 10 == 0){//批插入的对象立即写入数据库并释放内存
						sess.flush();
						sess.clear();
						tran.commit();
						tran = sess.beginTransaction();
					}
				}
			}
			tran.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("删除指定自助餐编号的自助餐能力关联信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetAbilityRelationInfo> listBARInfoByBuffetId(
			Integer buffetId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			Session sess = HibernateUtil.currentSession();
			return baDao.findBARInfoByBuffetId(sess, buffetId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据自助餐编号获取自助餐能力类型关联信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
