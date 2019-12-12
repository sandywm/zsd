package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.TodayTaskDao;
import com.zsd.module.TodayTask;

@SuppressWarnings("unchecked")
public class TodayTaskDaoImpl implements TodayTaskDao{

	@Override
	public TodayTask get(Session sess, int id) {
		// TODO Auto-generated method stub
		String hql = " from TodayTask as tt where tt.id = "+id;
		List<TodayTask> ttList = sess.createQuery(hql).list();
		if(ttList.size() > 0){
			return ttList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, TodayTask task) {
		// TODO Auto-generated method stub
		sess.save(task);
	}

	@Override
	public void delete(Session sess, TodayTask task) {
		// TODO Auto-generated method stub
		sess.delete(task);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, TodayTask task) {
		// TODO Auto-generated method stub
		sess.update(task);
	}

	@Override
	public List<TodayTask> findInfoByOpt(Session sess, Integer stuId,Integer loreId,
			String addDate, Integer reviewStatus,Integer confirmStatus) {
		// TODO Auto-generated method stub
		String hql = " from TodayTask as tt where 1=1";
		if(stuId > 0){
			hql += " and tt.user.id = "+stuId;
		}
		if(loreId > 0){
			hql += " and tt.loreInfo.id = "+loreId;
		}
		if(!addDate.equals("")){
			hql += " and tt.addDate >= '"+addDate+"'";
		}
		if(reviewStatus.equals(1)){
			hql += " and tt.reviewData = ''";
		}else if(reviewStatus.equals(2)){
			hql += " and tt.reviewData != ''";
		}
		if(confirmStatus.equals(1)){
			hql += " and tt.parentUserId = 0";
		}else if(confirmStatus.equals(2)){
			hql += " and tt.parentUserId > 0";
		}
		return null;
	}

}
