package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetAllDao;
import com.zsd.dao.HwQueDao;
import com.zsd.dao.LoreInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.HwQueInfo;
import com.zsd.service.HwQueManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class HwQueManagerImpl implements HwQueManager{

	BuffetAllDao baDao = null;
	LoreInfoDao lDao = null;
	HwQueDao hqDao = null;
	Transaction tran = null;
	@Override
	public Integer addHW(Integer btId, Integer loreId, Integer num,
			String title, String subject, String answer, String resolution,
			String queType, Integer orders, String operateUserName)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			baDao = (BuffetAllDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_ALL_INFO);
			lDao = (LoreInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_LORE_INFO);
			hqDao = (HwQueDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			HwQueInfo hq = new HwQueInfo(lDao.getEntityById(sess, loreId),baDao.getBTEntityById(sess, btId),
					num, title, subject, answer,resolution, queType, orders, 0,
					operateUserName, CurrentTime.getCurrentTime());
			hqDao.save(sess, hq);
			tran.commit();
			return hq.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加系统家庭作业时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public HwQueInfo getEntityById(Integer hwId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hqDao = (HwQueDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			return hqDao.getEntityById(sess, hwId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键获取实体信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwQueInfo> listInfoByOpt(Integer loreId, Integer btId,
			Integer inUse, boolean currNumFlag) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hqDao = (HwQueDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			return hqDao.findInfoByOpt(sess, loreId, btId, inUse, currNumFlag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件获取系统家庭作业记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwQueInfo> listPageInfoByLoreId(Integer loreId, Integer pageNo,
			Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hqDao = (HwQueDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			return hqDao.findPageInfoByOpt(sess, loreId, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据知识点编号分页获取系统家庭作业列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByLoreId(Integer loreId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hqDao = (HwQueDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			return hqDao.getCountByLoreId(sess, loreId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据知识点编号获取系统家庭作业记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateInUseById(Integer id, Integer inUse,
			String operateUserName) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hqDao = (HwQueDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			HwQueInfo hq = hqDao.getEntityById(sess, id);
			if(hq != null){
				if(inUse.equals(0) || inUse.equals(1)){
					hq.setInUse(inUse);
					hq.setOperateUserDate(CurrentTime.getCurrentTime());
					hqDao.update(sess, hq);
					tran.commit();
					return true;
				}
				return false;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改指定家庭作业的有效状态时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateInfoById(Integer id, String queSub, String queAnswer,
			String queResolution, String queType, String operateUserName)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			hqDao = (HwQueDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			HwQueInfo hq = hqDao.getEntityById(sess, id);
			if(hq != null){
				if(!queSub.equals("")){
					hq.setSubject(queSub);
				}
				if(!queAnswer.equals("")){
					hq.setAnswer(queAnswer);
				}
				hq.setResolution(queResolution);
				if(!queType.equals("")){
					hq.setQueType(queType);
				}
				hq.setOperateUserName(operateUserName);
				hq.setOperateUserDate(CurrentTime.getCurrentTime());
				hqDao.update(sess, hq);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改指定家庭作业基本信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<HwQueInfo> listInfoByLoreAndBuffetType(Integer loreId,
			String buffetTypeName) throws WEBException {
		// TODO Auto-generated method stub
		try {
			hqDao = (HwQueDao) DaoFactory.instance(null).getDao(Constants.DAO_HW_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			return hqDao.findInfoByLoreAndBuffetType(sess, loreId, buffetTypeName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据知识点编号，自助餐类型名称获取系统家庭作业记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
