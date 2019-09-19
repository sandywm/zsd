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
import com.zsd.tools.CurrentTime;
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
	public boolean updateNTS(Integer id,Integer bindStatus, String cancelDate ) throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherStudent nts = ntsDao.get(sess, id);
			if(nts != null){
				nts.setBindStatus(bindStatus);
				nts.setCancelDate(cancelDate);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("取消网络导师学生绑定信息时出现异常!");
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
	public List<NetTeacherStudent> listByntId(Integer userId) throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			return ntsDao.findNTByntId(sess, userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师编号获取绑定学生信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<NetTeacherStudent> listNTByntId(Integer userId, Integer bindSta,Integer pageNo,Integer pageSize)
			throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			return ntsDao.findNTByntId(sess, userId, bindSta, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师编号,绑定状态获取绑定学生信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public Integer getNTByntIdCount(Integer userId, Integer bindSta)
			throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			return ntsDao.getNTByNTIdCount(sess, userId, bindSta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师编号,绑定状态获取绑定学生记录数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<NetTeacherStudent> listNTByStuNameOrBindSta(Integer ntId,
			Integer paySta, Integer bindFlag, String stuName, Integer pageNo,
			Integer pageSize)throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			return ntsDao.findNTByStuNameOrBindSta(sess, ntId, paySta, bindFlag, stuName, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师编号,支付方式,绑定状态获取绑定学生信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getNtsBystunameOrBindSta(Integer ntId, Integer paySta,
			Integer bindFlag, String stuName)throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			return ntsDao.getNtsBystunameOrBindSta(sess, ntId, paySta, bindFlag, stuName);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师编号,支付方式,绑定状态获取绑定学生记录数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getByStuNum(Integer ntId, Integer bindFlag)
			throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			return ntsDao.getByStuNum(sess, ntId, bindFlag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师编号获取班内免费试用,付费学生人数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean clearUserNetTeacher(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherStudent nts = ntsDao.get(sess, id);
			if(nts != null){
				nts.setClearStatus(1);
				nts.setClearDate(CurrentTime.getCurrentTime());
				nts.setBindStatus(0);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("当学生升学时，之前与之绑定的网络导师将被取消，修改clearStatus的值为1时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean isBindTeaBySubIdAndSchType(Integer stuId, Integer subId,
			Integer schoolType) throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			return ntsDao.isBindTeaBySubIdAndSchType(sess, stuId, subId, schoolType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据学科编号,学段 查看学生是否绑定导师时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<NetTeacherStudent> listValidInfoByOpt(Integer stuId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			return ntsDao.findValidInfoByOpt(sess, stuId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("获取绑定日期没结束且未取消未清除的信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public NetTeacherStudent getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			return ntsDao.get(sess, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据主键获取信息实体时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<NetTeacherStudent> listNtsByNtId(Integer ntId, Integer bindSta)
			throws WEBException {
		try {
			ntsDao = (NetTeacherStudentDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDENT);
			Session sess  = HibernateUtil.currentSession();
			return ntsDao.findNtsByNtId(sess, ntId, bindSta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师编号,绑定状态获取绑定学生信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
}
