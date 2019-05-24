package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.NetTeacherInfoDao;
import com.zsd.dao.NetTeacherStudentDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.NetTeacherInfo;
import com.zsd.module.NetTeacherStudent;
import com.zsd.module.User;
import com.zsd.service.NetTeacherStudentManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class NetTeacherStudentManagerImpl implements NetTeacherStudentManager {
	NetTeacherStudentDao ntsDao = null;
	UserDao userDao = null;
	NetTeacherInfoDao ntDao = null;
	Transaction tran = null;
	@Override
	public Integer addNTS(Integer stuId, Integer teaId, String bindDate,
			Integer bindStatus, String endDate, Integer clearStatus,
			String clearDate, String cancelDate, Integer payStatus)
			throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			User user = userDao.get(sess, stuId);
			NetTeacherInfo netTeacherInfo = ntDao.get(sess, teaId);
			NetTeacherStudent nts = new NetTeacherStudent(user, netTeacherInfo, bindDate, bindStatus, endDate, clearStatus, clearDate, cancelDate, payStatus);
			ntsDao.save(sess, nts);
			tran.commit();
			return nts.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加网络导师学生绑定时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateNTSByStuId(Integer id, Integer stuId, Integer teaId,
			String bindDate, Integer bindStatus, String endDate,
			Integer clearStatus, String clearDate, String cancelDate,
			Integer payStatus) throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			userDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherStudent nts = ntsDao.get(sess, id);
			if(nts != null){
				User user = userDao.get(sess, stuId);
				NetTeacherInfo netTeacherInfo = ntDao.get(sess, teaId);
				nts.setUser(user );
				nts.setNetTeacherInfo(netTeacherInfo);
				nts.setBindDate(bindDate);
				nts.setBindStatus(bindStatus);
				nts.setEndDate(endDate);
				nts.setClearStatus(clearStatus);
				nts.setClearDate(clearDate);
				nts.setCancelDate(cancelDate);
				nts.setPayStatus(payStatus);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定网络导师学生信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<NetTeacherStudent> listByStuId(Integer stuId)
			throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			return ntsDao.findNTByStuId(sess, stuId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据学生编号网络导师学生绑定关系信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<NetTeacherStudent> listByntId(Integer ntId) throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			return ntsDao.findNTByntId(sess, ntId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师编号获取绑定学生信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
