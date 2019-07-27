package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ApplyClassDao;
import com.zsd.dao.ClassInfoDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.ApplyClassInfo;
import com.zsd.service.ApplyClassManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class ApplyClassManagerImpl implements ApplyClassManager{

	UserDao uDao = null;
	ClassInfoDao cDao = null;
	ApplyClassDao acDao = null;
	Transaction tran = null;
	@Override
	public Integer addApplyClassInfo(Integer userId, Integer classId,String classDetail,Integer checkUserId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			cDao = (ClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_CLASS_INFO);
			acDao = (ApplyClassDao) DaoFactory.instance(null).getDao(Constants.DAO_APPLY_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			ApplyClassInfo ac = new  ApplyClassInfo(uDao.get(sess, userId), cDao.get(sess, classId), CurrentTime.getCurrentTime(),classDetail,
					checkUserId,0,"","");
			acDao.save(sess, ac);
			tran.commit();
			return ac.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加班内老师接班信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean setCancleInfo(Integer id, Integer checkStatus,
			String checkRemark) throws WEBException {
		// TODO Auto-generated method stub
		try {
			acDao = (ApplyClassDao) DaoFactory.instance(null).getDao(Constants.DAO_APPLY_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			ApplyClassInfo ac = acDao.getEntityById(sess, id);
			if(ac != null){
				ac.setCheckTime(CurrentTime.getCurrentTime());
				ac.setCheckStatus(checkStatus);
				ac.setCheckRemark(checkRemark);
				acDao.update(sess, ac);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("审核老师接班信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<ApplyClassInfo> listPageInfoByOpt(Integer userId,
			Integer toUserId, Integer checkStatus,String sDate,String eDate, Integer pageNo,
			Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			acDao = (ApplyClassDao) DaoFactory.instance(null).getDao(Constants.DAO_APPLY_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			return acDao.findPageInfoByOpt(sess, userId, toUserId, checkStatus, sDate, eDate, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据申请老师编号、被申请老师编号、审核状态分页获取信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByOpt(Integer userId, Integer toUserId,
			Integer checkStatus,String sDate,String eDate) throws WEBException {
		// TODO Auto-generated method stub
		try {
			acDao = (ApplyClassDao) DaoFactory.instance(null).getDao(Constants.DAO_APPLY_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			return acDao.getCountByOpt(sess, userId, toUserId, checkStatus, sDate, eDate);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据申请老师编号、被申请老师编号、审核状态分页获取信息记录条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<ApplyClassInfo> listMyUnCheckApplyInfo(Integer toUserId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			acDao = (ApplyClassDao) DaoFactory.instance(null).getDao(Constants.DAO_APPLY_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			return acDao.findMyUnCheckApplyInfo(sess, toUserId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取未处理的接班申请列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
