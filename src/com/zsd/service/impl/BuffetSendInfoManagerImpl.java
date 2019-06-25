package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetSendInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.BuffetSendInfo;
import com.zsd.service.BuffetSendInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class BuffetSendInfoManagerImpl implements BuffetSendInfoManager {
	BuffetSendInfoDao bsDao = null;
	Transaction tran = null;
	@Override
	public List<BuffetSendInfo> listBsInfoByOption(Integer stuId,
			Integer subId, Integer isfinish, String starttime, String endtime)
			throws WEBException {
		
		try {
			bsDao = (BuffetSendInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_SEND_INFO);
			Session sess = HibernateUtil.currentSession();
			return bsDao.findBsInfoByOption(sess, stuId, subId, isfinish, starttime, endtime);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据指定学生,学科,完成状态,时间段获取自助餐发布信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<BuffetSendInfo> listPageInfoByOption(Integer stuId,
			Integer subId, Integer isfinish, String sDate, String eDate,
			Integer pageNo, Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bsDao = (BuffetSendInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_SEND_INFO);
			Session sess = HibernateUtil.currentSession();
			return bsDao.findPageInfoByOption(sess, stuId, subId, isfinish, sDate, eDate, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据指定学生,学科,完成状态,时间段分页获取自助餐发布信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
