package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.StudentPayOrderInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.StudentPayOrderInfo;
import com.zsd.service.StudentPayOrderInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

/** 
 * @author zong
 * @version 2019-5-25 上午10:40:44
 */
public class StudentPayOrderInfoManagerImpl implements
		StudentPayOrderInfoManager {
	StudentPayOrderInfoDao sOrderInfoDao =null;
	Transaction tran = null;
	@Override
	public List<StudentPayOrderInfo> findSpayOrderInfoByOpt(Integer ntsId,
			Integer comSta) throws WEBException {
		try {
			sOrderInfoDao = (StudentPayOrderInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDENT_PAY_ORDER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sOrderInfoDao.findSpayOrderInfoByOpt(sess, ntsId, comSta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师学生绑定主键,完成状态获取订单信息时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<StudentPayOrderInfo> listSpayOrderInfoByOpt(Integer ntsId,
			Integer pageNo, Integer pageSize) throws WEBException {
		try {
			sOrderInfoDao = (StudentPayOrderInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDENT_PAY_ORDER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sOrderInfoDao.findSpayOrderInfoByOpt(sess, ntsId, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师学生绑定主键,完成状态分页获取订单信息时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public Integer getspOrderInfoCount(Integer ntsId) throws WEBException {
		try {
			sOrderInfoDao = (StudentPayOrderInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDENT_PAY_ORDER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sOrderInfoDao.getspOrderInfoCount(sess, ntsId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师学生绑定主键获取订单记录数时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public void delBatchUnComPayOrder() throws WEBException {
		// TODO Auto-generated method stub
		try {
			sOrderInfoDao = (StudentPayOrderInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDENT_PAY_ORDER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			List<StudentPayOrderInfo> spoList = sOrderInfoDao.findSpayOrderInfoByOpt(sess, 0, 0);
			if(spoList.size() > 0){
				for(Integer i = 0 ; i < spoList.size() ; i++){
					sOrderInfoDao.delete(sess, spoList.get(i));
					if(i % 10 == 0){//批插入的对象立即写入数据库并释放内存
						sess.flush();
						sess.clear();
						tran.commit();
						tran = sess.beginTransaction();
					}
				}
				tran.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("批量删除所有未完成订单时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public boolean delSpecUnComPayOrder(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sOrderInfoDao = (StudentPayOrderInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDENT_PAY_ORDER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudentPayOrderInfo spo = sOrderInfoDao.get(sess, id);
			if(spo != null){
				if(spo.getComStatus().equals(0)){//只有未完成的才能删除
					sOrderInfoDao.delete(sess, spo);
					tran.commit();
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("删除指定未完成订单时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<StudentPayOrderInfo> listOrderPageInfoByOpt(Integer userId,
			String sDate, String eDate, Integer comSta, Integer pageNo,
			Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sOrderInfoDao = (StudentPayOrderInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDENT_PAY_ORDER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sOrderInfoDao.findOrderPageInfoByOpt(sess, userId, sDate, eDate, comSta, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据条件获取订单列表时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public Integer getCountByOpt(Integer userId, String sDate, String eDate,
			Integer comSta) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sOrderInfoDao = (StudentPayOrderInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDENT_PAY_ORDER_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sOrderInfoDao.getCountByOpt(sess, userId, sDate, eDate, comSta);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据条件获取订单记录条数时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}

}
