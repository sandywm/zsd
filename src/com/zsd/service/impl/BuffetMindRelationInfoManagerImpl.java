package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetMindRelationInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.BuffetMindRelationInfo;
import com.zsd.service.BuffetMindRelationInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class BuffetMindRelationInfoManagerImpl implements
		BuffetMindRelationInfoManager {
	BuffetMindRelationInfoDao bmrDao = null;
	Transaction tran =null;
	
	@Override
	public List<BuffetMindRelationInfo> listBmrInfoBybqId(Integer bqId)
			throws WEBException {
		try {
			bmrDao = (BuffetMindRelationInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_MIND_RELATION_INFO);
			Session sess = HibernateUtil.currentSession();
			return bmrDao.findBMRInfoByBuffetQueId(sess, bqId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据自助餐题库编号获取自助餐思维类型关联信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
