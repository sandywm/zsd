package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.LexInfoDao;
import com.zsd.dao.LoreInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.LexInfo;
import com.zsd.module.LexLoreRelateInfo;
import com.zsd.service.LexInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class LexInfoManagerImpl implements LexInfoManager{

	LexInfoDao lexDao = null;
	LoreInfoDao loreDao = null;
	Transaction tran = null;
	@Override
	public Integer addLex(String title, String titlePy, String lexContent)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			lexDao = (LexInfoDao)DaoFactory.instance(null).getDao(Constants.DAO_LEX_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LexInfo lex = new LexInfo(title,titlePy,lexContent);
			lexDao.save(sess, lex);
			tran.commit();
			return lex.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加词库时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean delLexById(Integer lexId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lexDao = (LexInfoDao)DaoFactory.instance(null).getDao(Constants.DAO_LEX_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LexInfo lex = lexDao.getEntityById(sess, lexId);
			if(lex != null){
				lexDao.delete(sess, lex);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("删除指定编号的词库时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateLexById(Integer lexId, String title, String titlePy,
			String lexContent) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lexDao = (LexInfoDao)DaoFactory.instance(null).getDao(Constants.DAO_LEX_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			LexInfo lex = lexDao.getEntityById(sess, lexId);
			if(lex != null){
				lex.setLexTitle(title);
				lex.setLexTitlePy(titlePy);
				lex.setLexContent(lexContent);
				lexDao.update(sess, lex);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改指定编号的词库信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public LexInfo getEntityById(Integer lexId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lexDao = (LexInfoDao)DaoFactory.instance(null).getDao(Constants.DAO_LEX_INFO);
			Session sess = HibernateUtil.currentSession();
			return lexDao.getEntityById(sess, lexId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据指定词库编号获取词库详情时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LexInfo> listInfoByOpt(String titleName, String titlePyCode,
			String queryOpt) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lexDao = (LexInfoDao)DaoFactory.instance(null).getDao(Constants.DAO_LEX_INFO);
			Session sess = HibernateUtil.currentSession();
			return lexDao.findInfoByOpt(sess, titleName, titlePyCode, queryOpt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件查询词库记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<LexInfo> listPageInfoByOpt(String titleName,
			String titlePyCode, String queryOpt, Integer pageNo,
			Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lexDao = (LexInfoDao)DaoFactory.instance(null).getDao(Constants.DAO_LEX_INFO);
			Session sess = HibernateUtil.currentSession();
			return lexDao.findPageInfoByOpt(sess, titleName, titlePyCode, queryOpt, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件分页查询词库记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByOpt(String titleName, String titlePyCode,
			String queryOpt) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lexDao = (LexInfoDao)DaoFactory.instance(null).getDao(Constants.DAO_LEX_INFO);
			Session sess = HibernateUtil.currentSession();
			return lexDao.getCountByOpt(sess, titleName, titlePyCode, queryOpt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件获取词库记录条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer addLLR(Integer lexId, Integer loreId) throws WEBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delLLRById(Integer llrId) throws WEBException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<LexLoreRelateInfo> listInfoByOpt(Integer lexId, Integer loreId)
			throws WEBException {
		// TODO Auto-generated method stub
		return null;
	}

}
