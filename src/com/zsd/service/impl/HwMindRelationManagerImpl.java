package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetMindRelationInfoDao;
import com.zsd.dao.HwQueDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.HwMindRelationInfo;
import com.zsd.service.HwMindRelationManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class HwMindRelationManagerImpl implements HwMindRelationManager{

	HwQueDao hqDao = null;
	BuffetMindRelationInfoDao bmrDao = null;
	Transaction tran = null;
	@Override
	public Integer addHMR(Integer bmtId, Integer hwId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hqDao = (HwQueDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_QUE_INFO);
			bmrDao = (BuffetMindRelationInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_MIND_RELATION_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<HwMindRelationInfo> listInfoByOpt(Integer bmtId, Integer hwId)
			throws WEBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delHMR(Integer hwId) throws WEBException {
		// TODO Auto-generated method stub
		return false;
	}

}
