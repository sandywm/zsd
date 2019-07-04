package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetAbilityRelationInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.BuffetAbilityRelationInfo;
import com.zsd.service.BuffetAbilityRelationInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class BuffetAbilityRelationInfoManagerImpl implements BuffetAbilityRelationInfoManager {
	BuffetAbilityRelationInfoDao barDao = null;
	Transaction tran = null;

	@Override
	public List<BuffetAbilityRelationInfo> listBarInfoBybqId(Integer bqId)
			throws WEBException {
		try {
			barDao = (BuffetAbilityRelationInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ABILITY_RELATION_INFO);
			Session sess = HibernateUtil.currentSession();
			return barDao.findBARInfo(sess, bqId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据自助餐题库编号获取自助餐能力类型关联信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
