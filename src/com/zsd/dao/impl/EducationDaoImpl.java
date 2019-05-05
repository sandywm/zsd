package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.EducationDao;
import com.zsd.module.Education;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class EducationDaoImpl implements EducationDao{

	@Override
	public Education get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (Education) sess.load(Education.class, id);
	}

	@Override
	public void save(Session sess, Education edu) {
		// TODO Auto-generated method stub
		sess.save(edu);
	}

	@Override
	public void delete(Session sess, Education edu) {
		// TODO Auto-generated method stub
		sess.delete(edu);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, Education edu) {
		// TODO Auto-generated method stub
		sess.update(edu);
	}

	@Override
	public List<Education> findSpecInfoById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from Education as edu where edu.id = "+id;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<Education> findPageInfoByOpt(Session sess, Integer ediId,
			Integer subId, Integer gradeId, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from Education as edu where 1=1";
		if(ediId > 0){
			hql += " and edu.edition.id = "+ediId;
		}
		if(subId > 0){
			hql += " and edu.gradeSubject.subject.id = "+subId;
		}
		if(gradeId > 0){
			hql += " and edu.gradeSubject.id = "+gradeId;
		}
		hql += " order by edu.eduOrder";
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, Integer ediId, Integer subId,
			Integer gradeId) {
		// TODO Auto-generated method stub
		String hql = "select count(edu.id) from Education as edu where 1=1";
		if(ediId > 0){
			hql += " and edu.edition.id = "+ediId;
		}
		if(subId > 0){
			hql += " and edu.gradeSubject.subject.id = "+subId;
		}
		if(gradeId > 0){
			hql += " and edu.gradeSubject.id = "+gradeId;
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<Education> findInfoByOpt(Session sess, Integer ediId,
			Integer gradeId, String eduVolume) {
		// TODO Auto-generated method stub
		String hql = " from Education as edu where edu.edition.id = "+ediId;
		hql += " and edu.gradeSubject.id = "+gradeId + " and edu.eduVolume = '"+eduVolume+"'";
		return sess.createQuery(hql).list();
	}
}
