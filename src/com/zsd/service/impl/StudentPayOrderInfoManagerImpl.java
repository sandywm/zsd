package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.StudentPayOrderInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.StudentPayOrderInfo;
import com.zsd.service.StudentPayOrderInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

/** 
 * @author zong
 * @version 2019-5-25 上午10:40:44
 */
public class StudentPayOrderInfoManagerImpl implements
		StudentPayOrderInfoManager {
	StudentPayOrderInfoDao sOrderInfoDao =null;
	Transaction tran = null;
	@Override
	public List<StudentPayOrderInfo> findSpayOrderInfoByOpt(Integer ntsId,
			Integer comSta) throws WEBException {
		try {
			sOrderInfoDao = (StudentPayOrderInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDENT_PAY_ORDER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sOrderInfoDao.findSpayOrderInfoByOpt(sess, ntsId, comSta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师学生绑定主键,完成状态获取订单信息时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<StudentPayOrderInfo> listSpayOrderInfoByOpt(Integer ntsId,
			Integer pageNo, Integer pageSize) throws WEBException {
		try {
			sOrderInfoDao = (StudentPayOrderInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDENT_PAY_ORDER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sOrderInfoDao.findSpayOrderInfoByOpt(sess, ntsId, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师学生绑定主键,完成状态分页获取订单信息时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public Integer getspOrderInfoCount(Integer ntsId) throws WEBException {
		try {
			sOrderInfoDao = (StudentPayOrderInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDENT_PAY_ORDER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sOrderInfoDao.getspOrderInfoCount(sess, ntsId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师学生绑定主键获取订单记录数时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}

}
