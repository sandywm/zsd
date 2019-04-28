package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.EditionDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.Edition;
import com.zsd.service.EditionManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class EditionManagerImpl implements EditionManager{

	EditionDao eDao = null;
	Transaction tran = null;
	@Override
	public Integer addEdi(String ediName, Integer ediOrder) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eDao = (EditionDao) DaoFactory.instance(null).getDao(Constants.DAO_EDITION_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			Edition edi = new Edition(ediName,ediOrder,0);
			eDao.save(sess, edi);
			tran.commit();
			return edi.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加出版社时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateEdiInfoById(Integer id, String ediName,
			Integer ediOrder, Integer showStatus) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eDao = (EditionDao) DaoFactory.instance(null).getDao(Constants.DAO_EDITION_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			Edition edi = eDao.get(sess, id);
			if(edi != null){
				edi.setEdiName(ediName);
				edi.setEdiOrder(ediOrder);
				edi.setShowStatus(showStatus);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改指定出版社时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Edition> listInfoByShowStatus(Integer id, Integer showStatus)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			eDao = (EditionDao) DaoFactory.instance(null).getDao(Constants.DAO_EDITION_INFO);
			Session sess  = HibernateUtil.currentSession();
			return eDao.findInfoByShowStatus(sess, showStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件获取出版社信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
