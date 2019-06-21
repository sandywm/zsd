package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.TownDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.TownInfo;
import com.zsd.service.TownManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class TownManagerImpl implements TownManager{

	TownDao tDao = null;
	Transaction tran = null;
	@Override
	public Integer addInfo(String countyCode, String countyName,
			String townCode, String townName) throws WEBException {
		// TODO Auto-generated method stub
		try {
			tDao = (TownDao) DaoFactory.instance(null).getDao(Constants.DAO_TOWN_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			TownInfo town = new TownInfo(countyCode, countyName, townCode,townName);
			tDao.save(sess, town);
			tran.commit();
			return town.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加乡镇信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<TownInfo> listInfoByCountyCode(String countyCode)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			tDao = (TownDao) DaoFactory.instance(null).getDao(Constants.DAO_TOWN_INFO);
			Session sess = HibernateUtil.currentSession();
			return tDao.findInfoByCountyCode(sess, countyCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据县编码获取乡镇信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
