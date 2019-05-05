package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ModuleDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.ModuleInfo;
import com.zsd.service.ModuleManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class ModuleManagerImpl implements ModuleManager{

	ModuleDao mDao = null;
	Transaction tran = null;
	@Override
	public Integer addMod(String modName, String modUrl, Integer mainStatus,
			Integer modOrder, String modSmallImgPc, String modBigImgPc,
			String modImgApp) throws WEBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateMod(Integer modId, String modName, String modUrl,
			Integer mainStatus, Integer modOrder, String modSmallImgPc,
			String modBigImgPc, String modImgApp) throws WEBException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ModuleInfo> listAllInfo() throws WEBException {
		// TODO Auto-generated method stub
		try {
			mDao = (ModuleDao) DaoFactory.instance(null).getDao(Constants.DAO_MODULE_INFO);
			Session sess = HibernateUtil.currentSession();
			return mDao.findAllInfo(sess);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取所有模块信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
