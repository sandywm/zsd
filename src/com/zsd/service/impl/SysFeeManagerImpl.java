package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.SysFeeDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.SysFeeInfo;
import com.zsd.service.SysFeeManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class SysFeeManagerImpl implements SysFeeManager{

	SysFeeDao sfDao = null;
	@Override
	public SysFeeInfo getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sfDao = (SysFeeDao) DaoFactory.instance(null).getDao(Constants.DAO_SYS_FEE_INFO);
			Session sess = HibernateUtil.currentSession();
			return sfDao.getEntityById(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键获取系统费用信息实体时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<SysFeeInfo> listInfoByopt(Integer feeType, Integer schoolType,
			Integer showStatus) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sfDao = (SysFeeDao) DaoFactory.instance(null).getDao(Constants.DAO_SYS_FEE_INFO);
			Session sess = HibernateUtil.currentSession();
			return sfDao.findInfoByOpt(sess, feeType, schoolType, showStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据费用类型、学段、有效状态获取系统费用列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
