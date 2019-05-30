package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.LoreInfoDao;
import com.zsd.dao.RelationZdResultDao;
import com.zsd.dao.StudyLogDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.RelationZdResult;
import com.zsd.service.RelationZdResultManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class RelationZdResultManagerImpl implements RelationZdResultManager{

	StudyLogDao slDao = null;
	LoreInfoDao lDao = null;
	RelationZdResultDao rzDao = null;
	Transaction tran = null;
	@Override
	public Integer addRZR(Integer studyLogId, Integer loreId,
			Integer zdxzdFlag, Integer studyFlag, Integer zczdFlag,
			Integer studyTimes, Integer zczdTimes) throws WEBException {
		// TODO Auto-generated method stub
		try {
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			rzDao = (RelationZdResultDao) DaoFactory.instance(null).getDao(Constants.DAO_RELATION_ZD_RESULT_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			RelationZdResult rz = new RelationZdResult(slDao.getEntityById(sess, studyLogId), lDao.getEntityById(sess, loreId),
					zdxzdFlag, studyFlag, zczdFlag,studyTimes, zczdTimes);
			rzDao.save(sess, rz);
			tran.commit();
			return rz.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateEntity(Integer id, Integer zdxzdFlag,
			Integer studyFlag, Integer zczdFlag, Integer studyTimes,
			Integer zczdTimes) throws WEBException {
		// TODO Auto-generated method stub
		try {
			rzDao = (RelationZdResultDao) DaoFactory.instance(null).getDao(Constants.DAO_RELATION_ZD_RESULT_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			RelationZdResult rz = rzDao.getEntityById(sess, id);
			if(rz != null){
				if(zdxzdFlag > -2){
					rz.setZdxzdFlag(zdxzdFlag);
				}
				if(studyFlag > -2){
					rz.setStudyFlag(studyFlag);				
								}
				if(zczdFlag > -2){
					rz.setZczdFlag(zczdFlag);
				}
				if(studyTimes > -2){
					rz.setStudyTimes(studyTimes);
				}
				if(zczdTimes > -2){
					rz.setZczdTimes(zczdTimes);
				}
				rzDao.update(sess, rz);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public RelationZdResult getEntityByOpt(Integer studyLogId, Integer loreId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			rzDao = (RelationZdResultDao) DaoFactory.instance(null).getDao(Constants.DAO_RELATION_ZD_RESULT_INFO);
			Session sess = HibernateUtil.currentSession();
			List<RelationZdResult>  rzList = rzDao.findInfoByOpt(sess, studyLogId, loreId);
			if(rzList.size() > 0){
				return rzList.get(0);
			}
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据学习记录编号、关联知识点编号获取信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
