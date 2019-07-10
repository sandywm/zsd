package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.SubjectDao;
import com.zsd.module.Subject;

@SuppressWarnings("unchecked")
public class SubjectDaoImpl implements SubjectDao{

	@Override
	public Subject get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (Subject) sess.load(Subject.class, id);
	}

	@Override
	public void save(Session sess, Subject sub) {
		// TODO Auto-generated method stub
		sess.save(sub);
	}

	@Override
	public void delete(Session sess, Subject sub) {
		// TODO Auto-generated method stub
		sess.delete(sub);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, Subject sub) {
		// TODO Auto-generated method stub
		sess.update(sub);
	}

	@Override
	public List<Subject> findInfoByDisplayStatus(Session sess, Integer displayStatus) {
		// TODO Auto-generated method stub
		String hql = " from Subject as sub";
		if(displayStatus >= 0){
			hql += " where sub.displayStatus = "+displayStatus;
		}
		hql += " order by sub.subOrder";
		return sess.createQuery(hql).list();
	}

	@Override
	public List<Subject> findInfoBySubName(Session sess, String subName) {
		String hql = " from Subject as sub where sub.subName='"+subName+"'";
		return sess.createQuery(hql).list();
	}
	

}
