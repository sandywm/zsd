package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ActRoleInfoDao;
import com.zsd.dao.ApplyClassDao;
import com.zsd.dao.ClassInfoDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.ApplyClassInfo;
import com.zsd.module.ClassInfo;
import com.zsd.module.User;
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
	public Integer addApplyClassInfo(Integer userId, Integer classId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			cDao = (ClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_CLASS_INFO);
			acDao = (ApplyClassDao) DaoFactory.instance(null).getDao(Constants.DAO_APPLY_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			ApplyClassInfo ac = new  ApplyClassInfo(uDao.get(sess, userId), cDao.get(sess, classId), CurrentTime.getCurrentTime(),"","");
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
	public boolean setCancleInfo(Integer id,String cancleReson) throws WEBException {
		// TODO Auto-generated method stub
		try {
			acDao = (ApplyClassDao) DaoFactory.instance(null).getDao(Constants.DAO_APPLY_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			ApplyClassInfo ac = acDao.getEntityById(sess, id);
			if(ac != null){
				ac.setCancelTime(CurrentTime.getCurrentTime());
				ac.setCancelReason(cancleReson);
				acDao.update(sess, ac);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("取消老师接班信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<ApplyClassInfo> listInfoByOpt(Integer userId,
			Integer validStatus) throws WEBException {
		// TODO Auto-generated method stub
		try {
			acDao = (ApplyClassDao) DaoFactory.instance(null).getDao(Constants.DAO_APPLY_CLASS_INFO);
			Session sess = HibernateUtil.currentSession();
			return acDao.findInfoByOpt(sess, userId, validStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据老师编号、有效状态获取信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
