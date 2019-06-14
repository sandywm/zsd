package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.StudyStuTjInfoDao;
import com.zsd.module.StudyStuTjInfo;

/** 
 * @author zong
 * @version 2019-6-9 上午10:20:37
 */
public class StudyStuTjInfoDaoImpl implements StudyStuTjInfoDao {

	@Override
	public StudyStuTjInfo get(Session sess, int id) {
		return (StudyStuTjInfo) sess.load(StudyStuTjInfo.class, id);
	}

	@Override
	public void save(Session sess, StudyStuTjInfo sstjInfo) {
		sess.save(sstjInfo);
	}

	@Override
	public void delete(Session sess, StudyStuTjInfo sstjInfo) {
		sess.delete(sstjInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, StudyStuTjInfo sstjInfo) {
		sess.update(sstjInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudyStuTjInfo> findInfoByOpt(Session sess,Integer userId, Integer subId,
			String startTime, String endTime) {
		String hql="from  StudyStuTjInfo  as sstj where sstj.user.id="+userId+" and sstj.subject.id="+subId+" and sstj.studyDate >= '"+startTime+"'  and  sstj.studyDate <= '"+endTime+"'";
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudyStuTjInfo> findInfoByOption(Session sess, Integer userId,Integer subId, String studyDate) {
		String hql = " from StudyStuTjInfo as sst where sst.studyDate >= '"+ studyDate +"' and sst.user.id = "+ userId + " and sst.subject.id = "+subId;
		return sess.createQuery(hql).list();
	}

}
