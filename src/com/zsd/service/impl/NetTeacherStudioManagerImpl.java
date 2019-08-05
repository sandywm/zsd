package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.NetTeacherInfoDao;
import com.zsd.dao.NetTeacherStudioDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.NetTeacherInfo;
import com.zsd.module.NetTeacherStudioInfo;
import com.zsd.service.NetTeacherStudioManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class NetTeacherStudioManagerImpl implements NetTeacherStudioManager {

	NetTeacherStudioDao ntStudioDao=null;
	NetTeacherInfoDao ntDao = null;
	Transaction tran = null;
	
	@Override
	public Integer addNTStudio(Integer teaId, String studioName,
			String studioCode, Integer maxNum, String studioProfile)
			throws WEBException {
		try {
			ntStudioDao = (NetTeacherStudioDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDIO);
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherInfo netTeacherInfo = ntDao.get(sess, teaId);
			NetTeacherStudioInfo ntStudio = new NetTeacherStudioInfo(netTeacherInfo, studioName, studioCode, maxNum, studioProfile);
			ntStudioDao.save(sess, ntStudio);
			tran.commit();
			return ntStudio.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加网络导师工作室时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateNTStudio(Integer id,String studioName,
			Integer maxNum, String studioProfile)throws WEBException {
		try {
			ntStudioDao = (NetTeacherStudioDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDIO);
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherStudioInfo ntStudio = ntStudioDao.get(sess, id);
			if(ntStudioDao != null){
				ntStudio.setStudioName(studioName);
				ntStudio.setMaxNum(maxNum);
				ntStudio.setStudioProfile(studioProfile);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定网络导师工作室信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<NetTeacherStudioInfo> listNTStudioByuId(Integer userId)
			throws WEBException {
		try {
			ntStudioDao = (NetTeacherStudioDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDIO);
			Session sess  = HibernateUtil.currentSession();
			return ntStudioDao.findNTStudioByuId(sess, userId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户编号获取网络导师工作室信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<NetTeacherStudioInfo> listNTStudioBystudioCode(String studioCode)
			throws WEBException {
		try {
			ntStudioDao = (NetTeacherStudioDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDIO);
			Session sess  = HibernateUtil.currentSession();
			return ntStudioDao.findNTStudioBystudioCode(sess, studioCode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据工作室邀请码获取网络导师工作室信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
