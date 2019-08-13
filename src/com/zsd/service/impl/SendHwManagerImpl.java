package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ClassInfoDao;
import com.zsd.dao.LoreInfoDao;
import com.zsd.dao.SendHwDao;
import com.zsd.dao.SubjectDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.SendHwInfo;
import com.zsd.service.SendHwManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class SendHwManagerImpl implements SendHwManager{

	UserDao uDao = null;
	LoreInfoDao lDao = null;
	ClassInfoDao cDao = null;
	SubjectDao sDao = null;
	SendHwDao shwDao = null;
	Transaction tran = null;
	@Override
	public Integer addSendHw(Integer sendUserId, String hwTitle,
			Integer loreId, Integer classId, String className, Integer subId,
			String endDate, Integer hwType, String sysQueIdArr,
			String hwQueIdArr, String teaQueIdArr, Integer coin,
			Integer traceStatus) throws WEBException {
		// TODO Auto-generated method stub
		try {
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			cDao = (ClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_CLASS_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			sDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			shwDao = (SendHwDao) DaoFactory.instance(null).getDao(Constants.DAO_SEND_HW_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			SendHwInfo shw = new SendHwInfo(uDao.get(sess, sendUserId), lDao.getEntityById(sess, loreId), cDao.get(sess, classId),
					hwTitle, className, sDao.get(sess, subId),
					CurrentTime.getCurrentTime(), endDate, hwType,
					sysQueIdArr, hwQueIdArr, teaQueIdArr,
					coin, traceStatus,0,0,"");
			shwDao.save(sess, shw);
			tran.commit();
			return shw.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加发布家庭作业时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateInfoById(Integer id, Integer coin,
			Integer traceStatus, Integer inUse) throws WEBException {
		// TODO Auto-generated method stub
		try {
			shwDao = (SendHwDao) DaoFactory.instance(null).getDao(Constants.DAO_SEND_HW_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			SendHwInfo shw = shwDao.getEntityById(sess, id);
			if(shw != null){
				if(coin > 0){
					shw.setCoin(coin);
				}
				if(traceStatus.equals(0) || traceStatus.equals(1)){
					shw.setTraceStatus(traceStatus);
				}
				if(inUse.equals(0) || inUse.equals(1)){
					shw.setInUse(inUse);
				}
				shwDao.update(sess, shw);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改发布家庭作业时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public SendHwInfo getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			shwDao = (SendHwDao) DaoFactory.instance(null).getDao(Constants.DAO_SEND_HW_INFO);
			Session sess = HibernateUtil.currentSession();
			return shwDao.getEntityById(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键获取发布家庭作业详情时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<SendHwInfo> listPageInfoByOpt(Integer sendUserId,Integer subId,
			Integer classId, Integer hwType,Integer checkStatus, Integer inUse, String sDate,
			String eDate, boolean pageFlag, Integer pageNo, Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			shwDao = (SendHwDao) DaoFactory.instance(null).getDao(Constants.DAO_SEND_HW_INFO);
			Session sess = HibernateUtil.currentSession();
			return shwDao.findPageInfoByOpt(sess, sendUserId, subId, classId, hwType, checkStatus, inUse, sDate, eDate, pageFlag, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件分页获取家庭作业信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByOpt(Integer sendUserId,Integer subId, Integer classId,
			Integer hwType,Integer checkStatus, Integer inUse, String sDate, String eDate)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			shwDao = (SendHwDao) DaoFactory.instance(null).getDao(Constants.DAO_SEND_HW_INFO);
			Session sess = HibernateUtil.currentSession();
			return shwDao.getCountByOpt(sess, sendUserId, subId, classId, hwType, checkStatus, inUse, sDate, eDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件获取家庭主页信息记录条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateCheckInfoById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			shwDao = (SendHwDao) DaoFactory.instance(null).getDao(Constants.DAO_SEND_HW_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			SendHwInfo shw = shwDao.getEntityById(sess, id);
			if(shw != null){
				shw.setCheckStatus(1);
				shw.setCheckTime(CurrentTime.getCurrentTime());
				shwDao.update(sess, shw);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改检查作业时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
