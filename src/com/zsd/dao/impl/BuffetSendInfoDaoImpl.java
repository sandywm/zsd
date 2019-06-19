package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.BuffetSendInfoDao;
import com.zsd.module.BuffetSendInfo;

public class BuffetSendInfoDaoImpl implements BuffetSendInfoDao {

	@Override
	public BuffetSendInfo get(Session sess, int id) {
		return (BuffetSendInfo) sess.load(BuffetSendInfo.class, id);
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

	@SuppressWarnings("unchecked")
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
			hql += "and bs.sendTime >= '"+ starttime + "'";
		}
		if (!endtime.equals("")) {
			endtime += " 23:59:59";
			hql += "and bs.sendTime <='" + endtime +"'";
		}
		return sess.createQuery(hql).list();
	}

}
