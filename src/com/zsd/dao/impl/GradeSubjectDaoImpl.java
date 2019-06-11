package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.GradeSubjectDao;
import com.zsd.module.GradeSubject;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class GradeSubjectDaoImpl implements GradeSubjectDao{

	@Override
	public GradeSubject get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (GradeSubject) sess.load(GradeSubject.class, id);
	}

	@Override
	public void save(Session sess, GradeSubject gSub) {
		// TODO Auto-generated method stub
		sess.save(gSub);
	}

	@Override
	public void delete(Session sess, GradeSubject gSub) {
		// TODO Auto-generated method stub
		sess.delete(gSub);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, GradeSubject gSub) {
		// TODO Auto-generated method stub
		sess.update(gSub);
	}

	@Override
	public List<GradeSubject> findPageInfoByOpt(Session sess, String gradeName,
			Integer subId, Integer shoolType, Integer showStatus,
			Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from GradeSubject as gs where 1=1";
		if(!gradeName.equals("")){
			hql += " and gs.gradeName = '"+gradeName+"'";
		}
		if(subId > 0){
			hql += " and gs.subject.id = "+subId;
		}
		if(shoolType > 0){
			hql += " and gs.shoolType = "+shoolType;
		}
		if(showStatus >= 0){
			hql += " and gs.displayStatus = "+showStatus;
		}
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, String gradeName, Integer subId,
			Integer shoolType, Integer showStatus) {
		// TODO Auto-generated method stub
		String hql = "select count(gs.id) from GradeSubject as gs where 1=1";
		if(!gradeName.equals("")){
			hql += " and gs.gradeName = '"+gradeName+"'";
		}
		if(subId > 0){
			hql += " and gs.subject.id = "+subId;
		}
		if(shoolType > 0){
			hql += " and gs.shoolType = "+shoolType;
		}
		if(showStatus >= 0){
			hql += " and gs.displayStatus = "+showStatus;
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<GradeSubject> findSpecInfoById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from GradeSubject as gs where gs.id = "+id;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<GradeSubject> findSpecInfoBySubId(Session sess,
			Integer subId) {
		// TODO Auto-generated method stub
		String hql = " from GradeSubject as gs where gs.subject.id = "+subId;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<GradeSubject> findSpecInfoByOpt(Session sess,
			String gradeName, Integer subId, Integer shoolType) {
		// TODO Auto-generated method stub
		String hql = " from GradeSubject as gs where gs.gradeName = '"+gradeName+"'";
		hql += " and gs.subject.id = "+subId + " and gs.schoolType = "+shoolType;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<GradeSubject> findSpecInfoByGname(Session sess, String gradeName) {
		// TODO Auto-generated method stub
		String hql = " from GradeSubject as gs where gs.gradeName = '"+gradeName+"' and gs.displayStatus = 0";
		return sess.createQuery(hql).list();
	}

	@Override
	public List<GradeSubject> findSpecInfoByschType(Session sess,
			Integer schoolType) {
		String hql = " from GradeSubject as gs where gs.schoolType = "+schoolType+" and gs.displayStatus = 0";
		return sess.createQuery(hql).list();
	}

}
