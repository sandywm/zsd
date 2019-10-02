package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.StudentPayOrderInfoDao;
import com.zsd.module.StudentPayOrderInfo;
import com.zsd.tools.CommonTools;

/** 
 * @author zong
 * @version 2019-5-25 上午09:40:25
 */
@SuppressWarnings("unchecked")
public class StudentPayOrderInfoDaoImpl implements StudentPayOrderInfoDao {

	@Override
	public StudentPayOrderInfo get(Session sess, int id) {
		String hql = " from StudentPayOrderInfo as spo where spo.id = "+id;
		List<StudentPayOrderInfo> l = sess.createQuery(hql).list();
		if(l.size() > 0){
			return l.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, StudentPayOrderInfo sPayOrderInfo) {
		sess.save(sPayOrderInfo);
	}

	@Override
	public void delete(Session sess, StudentPayOrderInfo sPayOrderInfo) {
		sess.delete(sPayOrderInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, StudentPayOrderInfo sPayOrderInfo) {
		sess.update(sPayOrderInfo);

	}

	@Override
	public List<StudentPayOrderInfo> findSpayOrderInfoByOpt(Session sess,
			Integer ntsId, Integer comSta) {
		String hql=" from StudentPayOrderInfo as spo where 1=1";
		if(ntsId > 0){
			hql += " and spo.ntsId = " +ntsId;
		}
		if(comSta > -1){
			hql += " and spo.comStatus = " +comSta;
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public List<StudentPayOrderInfo> findSpayOrderInfoByOpt(Session sess,
			Integer ntsId, Integer pageNo, Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql=" from StudentPayOrderInfo as spo where spo.ntsId="+ntsId+" and spo.comStatus=1";
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getspOrderInfoCount(Session sess,Integer ntsId) {
		String hql="Select count(spo.id) from StudentPayOrderInfo as spo where spo.ntsId="+ntsId+" and spo.comStatus=1";
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<StudentPayOrderInfo> findOrderPageInfoByOpt(Session sess,
			Integer userId, String sDate, String eDate, Integer comSta,
			Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql=" from StudentPayOrderInfo as spo where spo.user.id = "+userId;
		if(!sDate.equals("") && !eDate.equals("")){
			hql += " and substring(spo.addDate,1,10) >= '"+sDate+"' and substring(spo.addDate,1,10) <= '"+eDate+"'";
		}
		if(comSta >= 0){
			hql += " and spo.comStatus = "+ comSta;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, Integer userId, String sDate,
			String eDate, Integer comSta) {
		// TODO Auto-generated method stub
		String hql="select count(spo.id) from StudentPayOrderInfo as spo where spo.user.id = "+userId;
		if(!sDate.equals("") && !eDate.equals("")){
			hql += " and substring(spo.addDate,1,10) >= '"+sDate+"' and substring(spo.addDate,1,10) <= '"+eDate+"'";
		}
		if(comSta >= 0){
			hql += " and spo.comStatus = "+ comSta;
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<StudentPayOrderInfo> findUnComInfoByOpt(Session sess,
			Integer userId, Integer ntsId) {
		// TODO Auto-generated method stub
		String hql = " from StudentPayOrderInfo as spo where spo.user.id = "+userId + " and spo.comStatus = 0";
		if(ntsId > 0){
			hql += " and spo.ntsId > 0";
		}else{
			hql += " and spo.ntsId = 0";
		}
		return sess.createQuery(hql).list();
	}

}
