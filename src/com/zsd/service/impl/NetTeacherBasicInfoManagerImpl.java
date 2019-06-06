package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.NetTeacherBasicInfoDao;
import com.zsd.dao.NetTeacherInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.NetTeacherBasicInfo;
import com.zsd.module.NetTeacherInfo;
import com.zsd.service.NetTeacherBasicInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

/** 
 * @author zong
 * @version 2019-5-12 上午09:15:52
 */
public class NetTeacherBasicInfoManagerImpl implements
		NetTeacherBasicInfoManager {
	NetTeacherBasicInfoDao ntbInfoDao = null;
	NetTeacherInfoDao ntDao = null;
	Transaction tran = null;
	@Override
	public Integer addNtbInfo(Integer ntId, String title, String dataRange,
			String description, Integer type, String addData)
			throws WEBException {
		try {
			ntbInfoDao = (NetTeacherBasicInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_BASIC_INFO);
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherInfo netTeacherInfo = ntDao.get(sess, ntId);
			NetTeacherBasicInfo ntbInfo = new  NetTeacherBasicInfo(netTeacherInfo , dataRange, description, type, addData);
			ntbInfoDao.save(sess, ntbInfo);
			tran.commit();
			return ntbInfo.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加老师背景资料时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<NetTeacherBasicInfo> listNtbByTeaId(Integer teaId)
			throws WEBException {
		try {
			ntbInfoDao = (NetTeacherBasicInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_BASIC_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			return ntbInfoDao.findNtbByTeaId(sess, teaId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师主键获取老师背景资料时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}

}
