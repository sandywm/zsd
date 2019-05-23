package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.LoreQuestionErrorDao;
import com.zsd.module.LoreQuestionErrorInfo;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class LoreQuestionErrorDaoImpl implements LoreQuestionErrorDao{

	@Override
	public LoreQuestionErrorInfo get(Session sess, int id) {
		// TODO Auto-generated method stub
		String hql = " from LoreQuestionErrorInfo as lqe where lqe.id = "+id;
		List<LoreQuestionErrorInfo> lqeList = sess.createQuery(hql).list();
		if(lqeList.size() > 0){
			return lqeList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, LoreQuestionErrorInfo lqs) {
		// TODO Auto-generated method stub
		sess.save(lqs);
	}

	@Override
	public void delete(Session sess, LoreQuestionErrorInfo lqs) {
		// TODO Auto-generated method stub
		sess.delete(lqs);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, LoreQuestionErrorInfo lqs) {
		// TODO Auto-generated method stub
		sess.update(lqs);
	}

	@Override
	public List<LoreQuestionErrorInfo> findPageInfoByOpt(Session sess,Integer userId,
			String errorType, String sDate, String eDate, Integer updateStatus,Integer pageNo,Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from LoreQuestionErrorInfo as lqe where 1=1";
		if(!errorType.equals("")){
			hql += " and lqe.errorType = '"+errorType+"'";
		}
		if(!sDate.equals("") && !eDate.equals("")){
			hql += " and lqe.addDate >= '"+sDate+"' and lqeDate <= '"+eDate+"'";
		}
		if(!updateStatus.equals(-1)){
			hql += " and lqe.checkStatus = "+updateStatus;
		}
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, Integer userId,String errorType, String sDate,
			String eDate, Integer updateStatus) {
		// TODO Auto-generated method stub
		String hql = "select count(lqe.id) from LoreQuestionErrorInfo as lqe where 1=1";
		if(!errorType.equals("")){
			hql += " and lqe.errorType = '"+errorType+"'";
		}
		if(!sDate.equals("") && !eDate.equals("")){
			hql += " and lqe.addDate >= '"+sDate+"' and lqeDate <= '"+eDate+"'";
		}
		if(!updateStatus.equals(-1)){
			hql += " and lqe.checkStatus = "+updateStatus;
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

}
