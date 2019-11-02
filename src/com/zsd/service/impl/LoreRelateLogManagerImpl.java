package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.LoreInfoDao;
import com.zsd.dao.LoreRelateLogDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.LoreRelateLogInfo;
import com.zsd.service.LoreRelateLogManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class LoreRelateLogManagerImpl implements LoreRelateLogManager{

	LoreInfoDao lDao = null;
	LoreRelateLogDao lrlDao = null;
	Transaction tran = null;
	@Override
	public Integer addLRL(Integer loreId, String relateType,
			Integer relateStatus, String relateResult, String relateUser)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			lrlDao = (LoreRelateLogDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_RELATE_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LoreRelateLogInfo lrl = new LoreRelateLogInfo(lDao.getEntityById(sess, loreId), relateType,
					relateStatus, relateResult, CurrentTime.getCurrentTime(),
					relateUser);
			lrlDao.save(sess, lrl);
			tran.commit();
			return lrl.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加知识点关联日志文件时出现 异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public LoreRelateLogInfo getEntityById(Integer lrlId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lrlDao = (LoreRelateLogDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_RELATE_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return lrlDao.get(sess, lrlId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键获取知识点关联日志实体时出现 异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LoreRelateLogInfo> listPageInfoByOpt(String lorePyCode,
			String loreName, Integer ediId, Integer pageNo, Integer pageSize)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lrlDao = (LoreRelateLogDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_RELATE_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return lrlDao.findPageInfoByOpt(sess, lorePyCode, loreName, ediId, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据知识点拼音码,知识点名称,出版社编号分页获取日志列表时出现 异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByOpt(String lorePyCode, String loreName,
			Integer ediId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lrlDao = (LoreRelateLogDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_RELATE_LOG_INFO);
			Session sess = HibernateUtil.currentSession();
			return lrlDao.getCountByOpt(sess, lorePyCode, loreName, ediId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据知识点拼音码,知识点名称,出版社编号获取日志记录条数时出现 异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
