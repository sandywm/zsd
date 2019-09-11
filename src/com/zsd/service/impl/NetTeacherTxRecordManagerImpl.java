package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.NetTeacherTxRecordDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.NetTeacherTxRecord;
import com.zsd.service.NetTeacherTxRecordManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

/** 
 * @author zong
 * @version 2019-5-25 下午05:36:28
 */
public class NetTeacherTxRecordManagerImpl implements NetTeacherTxRecordManager {
	NetTeacherTxRecordDao ntxDao = null;
	Transaction tran = null;
	@Override
	public List<NetTeacherTxRecord> findnTxReCordByNtId(Integer ntId,Integer operId)
			throws WEBException {
		try {
			ntxDao= (NetTeacherTxRecordDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_TX_RECORD);
			Session sess  = HibernateUtil.currentSession();
			return ntxDao.findnTxReCordByNtId(sess, ntId,operId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师编号获取提现信息时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<NetTeacherTxRecord> listnTxReCordByNtId(Integer userId,
			Integer pageNo, Integer pageSize) throws WEBException {
		try {
			ntxDao= (NetTeacherTxRecordDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_TX_RECORD);
			Session sess  = HibernateUtil.currentSession();
			return ntxDao.findnTxReCordByNtId(sess, userId, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据导师用户编号分页获取提现信息时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public Integer getnTxReCordCount(Integer userId) throws WEBException {
		try {
			ntxDao= (NetTeacherTxRecordDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_TX_RECORD);
			Session sess  = HibernateUtil.currentSession();
			return ntxDao.getnTxReCordCount(sess, userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据导师用户编号获取提现记录数时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<NetTeacherTxRecord> listnTxReCordById(Integer Id)
			throws WEBException {
		try {
			ntxDao= (NetTeacherTxRecordDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_TX_RECORD);
			Session sess  = HibernateUtil.currentSession();
			return ntxDao.findnTxReCordById(sess, Id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据主键编号获取账单信息时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}

}
