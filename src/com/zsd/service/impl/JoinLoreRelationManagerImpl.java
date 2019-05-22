package com.zsd.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.JoinLoreRelationDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.JoinLoreRelation;
import com.zsd.service.JoinLoreRelationManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class JoinLoreRelationManagerImpl implements JoinLoreRelationManager{

	JoinLoreRelationDao jlrDao = null;
	Transaction tran = null;
	@Override
	public Integer addJLR(String loreIdArr) throws WEBException {
		// TODO Auto-generated method stub
		try {
			jlrDao = (JoinLoreRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_JOIN_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			JoinLoreRelation jlr = new JoinLoreRelation(loreIdArr);
			jlrDao.save(sess, jlr);
			tran.commit();
			return jlr.getId(); 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加合并知识点时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean delJLR(Integer jlrId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			jlrDao = (JoinLoreRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_JOIN_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			JoinLoreRelation jlr = jlrDao.getEntityById(sess, jlrId);
			if(jlr != null){
				jlrDao.delete(sess, jlr);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("删除合并知识点时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateJLR(Integer jlrId, String loreIdArr)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			jlrDao = (JoinLoreRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_JOIN_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			JoinLoreRelation jlr = jlrDao.getEntityById(sess, jlrId);
			if(jlr != null){
				jlr.setLoreIdArray(loreIdArr);
				jlrDao.update(sess, jlr);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改合并知识点时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public JoinLoreRelation getInfoByLoreId(Integer loreId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			jlrDao = (JoinLoreRelationDao) DaoFactory.instance(null).getDao(Constants.DAO_JOIN_LORE_RELATE_INFO);
			Session sess = HibernateUtil.currentSession();
			return jlrDao.getInfoByLoreId(sess, loreId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据知识点编号查询合并知识点时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
