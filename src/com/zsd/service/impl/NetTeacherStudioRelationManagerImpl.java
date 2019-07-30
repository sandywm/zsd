package com.zsd.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.NetTeacherStudioDao;
import com.zsd.dao.NetTeacherStudioRelationDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.NetTeacherStudioInfo;
import com.zsd.module.NetTeacherStudioRelationInfo;
import com.zsd.service.NetTeacherStudioRelationManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class NetTeacherStudioRelationManagerImpl implements
		NetTeacherStudioRelationManager {
	
	NetTeacherStudioRelationDao ntsrDao = null;
	NetTeacherStudioDao ntStudioDao=null;
	Transaction tran = null;

	@Override
	public Integer addNTStudioRelation(Integer ntStudioId, Integer teaId,
			String addTime, String outTime) throws WEBException {
		try {
			ntsrDao = (NetTeacherStudioRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDIO_RELATION);
			ntStudioDao = (NetTeacherStudioDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDIO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherStudioInfo netTeacherStudioInfo = ntStudioDao.get(sess, ntStudioId);
			NetTeacherStudioRelationInfo ntsr = new NetTeacherStudioRelationInfo(netTeacherStudioInfo, teaId, addTime, outTime);
			ntsrDao.save(sess, ntsr);
			tran.commit();
			return ntsr.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加网络导师工作室老师信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
