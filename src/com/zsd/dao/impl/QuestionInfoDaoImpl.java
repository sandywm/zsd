package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.QuestionInfoDao;
import com.zsd.module.QuestionInfo;
import com.zsd.tools.CommonTools;

/**
 * @author zong
 * @version 2019-5-19 上午10:36:39
 */
public class QuestionInfoDaoImpl implements QuestionInfoDao {

	@Override
	public QuestionInfo get(Session sess, int id) {

		return (QuestionInfo) sess.load(QuestionInfo.class, id);
	}

	@Override
	public void save(Session sess, QuestionInfo queInfo) {
		sess.save(queInfo);

	}

	@Override
	public void delete(Session sess, QuestionInfo queInfo) {
		sess.delete(queInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));

	}

	@Override
	public void update(Session sess, QuestionInfo queInfo) {
		sess.update(queInfo);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionInfo> findInfoByOpt(Session sess,Integer userId, Integer subId,
			Integer readStatus, Integer pageNo, Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql = "from  QuestionInfo as q where 1=1";
		if (!userId.equals(0)) {
			hql += " and q.user.id=" + userId;
		}
		if (!subId.equals(0)) {
			hql += " and q.subject.id=" + subId;
		}
		if (!readStatus.equals(-1)) {
			hql += " and q.readStatus=" + readStatus;
		}
		return sess.createQuery(hql).setFirstResult(offset)
				.setMaxResults(pageSize).list();
	}

	@Override
	public Integer getInfoByOptCount(Session sess,Integer userId, Integer subId,
			Integer readStatus) {
		String hql = "select count(q.id)from  QuestionInfo as q where 1=1";
		if (!userId.equals(0)) {
			hql += " and q.user.id=" + userId;
		}
		if (!subId.equals(0)) {
			hql += " and q.subject.id=" + subId;
		}
		if (!readStatus.equals(-1)) {
			hql += " and q.readStatus=" + readStatus;
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionInfo> findInfoByntId(Session sess, Integer ntId) {
		String hql = "from  QuestionInfo as q where q.netTeacherInfo.id ="+ntId;
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionInfo> findInfoByStu(Session sess,Integer userId, Integer stuId,
			Integer readStatus, Integer pageNo, Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql = "from  QuestionInfo as q where 1=1";
		if(!userId.equals(0)){
			hql += " and q.netTeacherInfo.user.id=" + userId;
		}
		if (!stuId.equals(0)) {
			hql += " and q.user.id=" + stuId;
		}
		if (!readStatus.equals(-1)) {
			hql += " and q.readStatus=" + readStatus;
		}
		hql+=" order by q.queTime  DESC ";
		return sess.createQuery(hql).setFirstResult(offset)
				.setMaxResults(pageSize).list();
	}

	@Override
	public Integer getInfoByStuCount(Session sess,Integer userId, Integer stuId,
			Integer readStatus) {
		String hql = "select count(q.id)from  QuestionInfo as q where 1=1";
		if(!userId.equals(0)){
			hql += " and q.netTeacherInfo.user.id=" + userId;
		}
		if (!stuId.equals(0)) {
			hql += " and q.user.id=" + stuId;
		}
		if (!readStatus.equals(-1)) {
			hql += " and q.readStatus=" + readStatus;
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuestionInfo> findInfoById(Session sess, Integer qId) {
		String hql = "from  QuestionInfo as q where q.id ="+qId;
		return sess.createQuery(hql).list();
	}

}
