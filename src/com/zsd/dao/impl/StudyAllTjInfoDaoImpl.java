package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.StudyAllTjInfoDao;
import com.zsd.module.StudyAllTjInfo;

/** 
 * @author zong
 * @version 2019-6-9 上午10:49:44
 */
public class StudyAllTjInfoDaoImpl implements StudyAllTjInfoDao {

	@Override
	public StudyAllTjInfo get(Session sess, int id) {
		return (StudyAllTjInfo) sess.load(StudyAllTjInfo.class, id);
	}

	@Override
	public void save(Session sess, StudyAllTjInfo satjInfo) {
		sess.save(satjInfo);
	}

	@Override
	public void delete(Session sess, StudyAllTjInfo satjInfo) {
		sess.delete(satjInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, StudyAllTjInfo satjInfo) {
		sess.update(satjInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudyAllTjInfo> findInfoByOpt(Session sess, Integer subId,
			String startTime, String endTime) {
		String hql ="from StudyAllTjInfo as sat where  sat.subject.id= "+ subId +" and sat.studyDate>='"+startTime+", and sat.studyDate<='"+endTime+"'";
		return sess.createSQLQuery(hql).list();
	}

}
