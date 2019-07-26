package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.LoreInfoDao;
import com.zsd.dao.TeaQueDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.TeaQueInfo;
import com.zsd.service.TeaQueManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class TeaQueManagerImpl implements TeaQueManager{

	LoreInfoDao lDao = null;
	UserDao uDao = null;
	TeaQueDao tqDao = null;
	Transaction tran = null;
	@Override
	public Integer addTQ(Integer loreId, Integer queNum, String queTitle,
			String queSub, String queAnswer, String queResolution,
			String queType, String queType2, Integer teaId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			tqDao = (TeaQueDao) DaoFactory.instance(null).getDao(Constants.DAO_TEA_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			TeaQueInfo tq = new TeaQueInfo(uDao.getEntityById(sess, teaId).get(0), lDao.getEntityById(sess, loreId), queNum,
					queTitle, queSub, queAnswer,queResolution, queType, queType2, 0,CurrentTime.getCurrentTime());
			tqDao.save(sess, tq);
			tran.commit();
			return tq.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加老师上传家庭作业时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateInUseById(Integer tqId, Integer inUse)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			tqDao = (TeaQueDao) DaoFactory.instance(null).getDao(Constants.DAO_TEA_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			TeaQueInfo tq = tqDao.getEntityById(sess, tqId);
			if(tq != null){
				if(inUse.equals(0) || inUse.equals(1)){
					tq.setInUse(inUse);
					tqDao.update(sess, tq);
					tran.commit();
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("设置题库有/无效时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateInfoById(Integer tqId, String queSub,
			String queAnswer, String queResolution, String queType)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			tqDao = (TeaQueDao) DaoFactory.instance(null).getDao(Constants.DAO_TEA_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			TeaQueInfo tq = tqDao.getEntityById(sess, tqId);
			if(tq != null){
				if(!queSub.equals("")){
					tq.setQueSub(queSub);
				}
				if(!queAnswer.equals("")){
					tq.setQueAnswer(queAnswer);				
				}
				if(!queResolution.equals("")){
					tq.setQueResolution(queResolution);
				}
				if(!queType.equals("")){
					tq.setQueType(queType);
				}
				tq.setOperateUserDate(CurrentTime.getCurrentTime());
				tqDao.update(sess, tq);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改题库详细信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<TeaQueInfo> listInfoByOpt(Integer loreId, Integer teaId,
			boolean pageFlag, Integer pageNo, Integer pageSize)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			tqDao = (TeaQueDao) DaoFactory.instance(null).getDao(Constants.DAO_TEA_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			return tqDao.findInfoByOpt(sess, loreId, teaId, pageFlag, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件是否获取老师自传家庭作业信息列表记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByOpt(Integer loreId, Integer teaId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			tqDao = (TeaQueDao) DaoFactory.instance(null).getDao(Constants.DAO_TEA_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			return tqDao.getCountByOpt(sess, loreId, teaId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件是否获取老师自传家庭作业信息列表记录列表条数出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public TeaQueInfo getEntityById(Integer tqId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			tqDao = (TeaQueDao) DaoFactory.instance(null).getDao(Constants.DAO_TEA_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			return tqDao.getEntityById(sess, tqId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据编号获取老师自传家庭作业详情时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
