package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.LoreInfoDao;
import com.zsd.dao.StudyMapDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.StudyMapInfo;
import com.zsd.service.StudyMapManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class StudyMapManagerImpl implements StudyMapManager{

	LoreInfoDao lDao = null;
	UserDao uDao = null;
	StudyMapDao smDao = null;
	Transaction tran = null;
	@Override
	public Integer addSM(Integer stuId, Integer loreId, Integer currStep)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			smDao = (StudyMapDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_MAP_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyMapInfo sm = new StudyMapInfo(uDao.getEntityById(sess, stuId).get(0),lDao.getEntityById(sess, loreId),currStep);
			smDao.save(sess, sm);
			tran.commit();
			return sm.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加学习地图信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateStepById(Integer id, Integer currStep)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			smDao = (StudyMapDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_MAP_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyMapInfo sm = smDao.getEntityById(sess, id);
			if(sm != null){
				sm.setCurrStep(currStep);
				smDao.update(sess, sm);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改学习地图的层数信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StudyMapInfo> listInfoByOpt(Integer stuId, Integer loreId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			smDao = (StudyMapDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_MAP_INFO);
			Session sess = HibernateUtil.currentSession();
			return smDao.findInfoByOptions(sess, loreId, stuId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据学生编号、知识点编号获取学习地图的信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public StudyMapInfo getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			smDao = (StudyMapDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_MAP_INFO);
			Session sess = HibernateUtil.currentSession();
			return smDao.getEntityById(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键编号获取学习地图的信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
