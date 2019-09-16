package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.NetTeacherInfoDao;
import com.zsd.dao.NetTeacherTxRecordDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.NetTeacherInfo;
import com.zsd.module.NetTeacherTxRecord;
import com.zsd.service.NetTeacherTxRecordManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

/** 
 * @author zong
 * @version 2019-5-25 下午05:36:28
 */
public class NetTeacherTxRecordManagerImpl implements NetTeacherTxRecordManager {
	NetTeacherTxRecordDao ntxDao = null;
	NetTeacherInfoDao ntDao = null;
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
	public List<NetTeacherTxRecord> listnTxReCordByNtId(Integer userId,String txDate,Integer operFlag,
			Integer pageNo, Integer pageSize) throws WEBException {
		try {
			ntxDao= (NetTeacherTxRecordDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_TX_RECORD);
			Session sess  = HibernateUtil.currentSession();
			return ntxDao.findnTxReCordByNtId(sess, userId,txDate,operFlag, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据导师用户编号分页获取提现信息时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public Integer getnTxReCordCount(Integer userId,String txDate,Integer operFlag) throws WEBException {
		try {
			ntxDao= (NetTeacherTxRecordDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_TX_RECORD);
			Session sess  = HibernateUtil.currentSession();
			return ntxDao.getnTxReCordCount(sess, userId,txDate,operFlag);
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
	@Override
	public Integer addTX(Integer stuId, Integer teaId, Integer txFee,
			String bankName, String bankNo, Integer operateUserId,String remark) throws WEBException {
		// TODO Auto-generated method stub
		try {
			ntxDao= (NetTeacherTxRecordDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_TX_RECORD);
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			Transaction tran = sess.beginTransaction();
			NetTeacherTxRecord tx = new NetTeacherTxRecord(ntDao.get(sess, teaId),stuId, CurrentTime.getCurrentTime(),
					txFee, operateUserId, "",bankName, bankNo,remark);
			ntxDao.save(sess, tx);
			tran.commit();
			return tx.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加提现/返现记录信息时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}

}
