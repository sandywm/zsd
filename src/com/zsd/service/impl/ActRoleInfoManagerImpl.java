package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ActRoleInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.ActRoleInfo;
import com.zsd.service.ActRoleInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class ActRoleInfoManagerImpl implements ActRoleInfoManager{
	
	ActRoleInfoDao arDao = null;
	Transaction tran = null;
	@Override
	public List<ActRoleInfo> listInfoByOpt(Integer roleId, Integer actId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			arDao = (ActRoleInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_ACT_ROLE_INFO);
			Session sess = HibernateUtil.currentSession();
			return arDao.findInfoByOpt(sess, roleId, actId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据角色编号、模块动作编号获取动作角色列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
