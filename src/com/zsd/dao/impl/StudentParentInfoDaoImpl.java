package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.StudentParentInfoDao;
import com.zsd.module.StudentParentInfo;

public class StudentParentInfoDaoImpl implements StudentParentInfoDao {

	@Override
	public StudentParentInfo get(Session sess, int id) {
		return (StudentParentInfo) sess.load(StudentParentInfo.class, id);
	}

	@Override
	public void save(Session sess, StudentParentInfo spInfo) {
		sess.save(spInfo);
	}

	@Override
	public void delete(Session sess, StudentParentInfo spInfo) {
		sess.delete(spInfo);

	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, StudentParentInfo spInfo) {
	sess.update(spInfo);

	}

	@SuppressWarnings("unchecked")
	@Override
	public StudentParentInfo getEntityByParentId(Session sess, Integer parId) {
		// TODO Auto-generated method stub
		String hql = " from StudentParentInfo as sp where sp.parent.id = "+parId;
		List<StudentParentInfo> spList = sess.createQuery(hql).list();
		if(spList.size() > 0){
			return spList.get(0);
		}
		return null;
	}

	@Override
	public StudentParentInfo getEntityByStuId(Session sess, Integer stuId) {
		String hql = " from StudentParentInfo as sp where sp.stu.id= "+ stuId;
		@SuppressWarnings("unchecked")
		List<StudentParentInfo> spList = sess.createQuery(hql).list();
		if(spList.size() > 0){
			return spList.get(0);
		}
		return null;
	}

}
