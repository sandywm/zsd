package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.BuffetSendInfoDao;
import com.zsd.module.BuffetSendInfo;

@SuppressWarnings("unchecked")
public class BuffetSendInfoDaoImpl implements BuffetSendInfoDao {

	@Override
	public BuffetSendInfo get(Session sess, int id) {
		String hql="from BuffetSendInfo as bs where bs.id ="+id;
		List<BuffetSendInfo> bsList = sess.createQuery(hql).list();
		if(bsList.size() > 0){
			return bsList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, BuffetSendInfo bsInfo) {
		sess.save(bsInfo);
	}

	@Override
	public void delete(Session sess, BuffetSendInfo bsInfo) {
		sess.delete(bsInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));

	}

	@Override
	public void update(Session sess, BuffetSendInfo bsInfo) {
		sess.update(bsInfo);
	}

	@Override
	public List<BuffetSendInfo> findBsInfoByOption(Session sess, Integer stuId,
			Integer subId, Integer isfinish, String starttime, String endtime) {
		String hql="from BuffetSendInfo as bs where bs.studyLogInfo.user.id ="+stuId;
		if(!subId.equals(0)){
		  hql+=" and bs.studyLogInfo.subject.id ="+subId;
		}
		if(!isfinish.equals(-1)){
			hql+=" and bs.studyResult="+isfinish;
		}
		if (!starttime.equals("")){
			hql += " and substring(bs.sendTime,1,10) >= '"+ starttime + "'";
		}
		if (!endtime.equals("")) {
			hql += " and substring(bs.sendTime,1,10) <='" + endtime +"'";
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public List<BuffetSendInfo> findBsInfoById(Session sess, Integer id) {
		String hql="from BuffetSendInfo as bs where bs.id ="+id;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<BuffetSendInfo> findPageInfoByOption(Session sess,
			Integer stuId, Integer subId, Integer isfinish, Integer teaId, String sDate,
			String eDate, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from BuffetSendInfo as bs where bs.studyLogInfo.user.id ="+stuId;
		if(!subId.equals(0)){
		  hql+=" and bs.studyLogInfo.subject.id ="+subId;
		}
		if(!isfinish.equals(-1)){
			hql+=" and bs.studyResult="+isfinish;
		}
		if(teaId > 0){//网络导师
			hql += " and bs.studyLogInfo.teaId = "+teaId;
		}
		if (!sDate.equals("") && !eDate.equals("")){
			hql += " and substring(bs.sendTime,1,10) >= '"+ sDate + "'";
			hql += " and substring(bs.sendTime,1,10) <='" + eDate +"'";
		}
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public List<BuffetSendInfo> findBsInfoByStudyLogId(Session sess,
			Integer studyLogid) {
		String hql="from BuffetSendInfo as bs where bs.studyLogInfo.id ="+studyLogid;
		return sess.createQuery(hql).list();
	}

}
