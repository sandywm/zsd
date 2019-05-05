package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ModuleActionInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.ModuleActionInfo;
import com.zsd.service.ModuleActionInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class ModuleActionInfoManagerImpl implements ModuleActionInfoManager{
	ModuleActionInfoDao maDao = null;
	Transaction tran = null;
	@Override
	public List<ModuleActionInfo> listInfoByModId(Integer modId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			maDao = (ModuleActionInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_MODULE_ACTION_INFO);
			Session sess = HibernateUtil.currentSession();
			return maDao.findInfoByModId(sess, modId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据模块编号获取模块动作信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
