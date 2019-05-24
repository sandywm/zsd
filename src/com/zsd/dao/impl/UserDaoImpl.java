package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.UserDao;
import com.zsd.module.User;
import com.zsd.tools.CommonTools;

public class UserDaoImpl implements UserDao {

	@Override
	public User get(Session sess, int id) {
		return  (User) sess.load(User.class, id);
	}

	@Override
	public void save(Session sess, User user) {
		sess.save(user);
	}

	@Override
	public void update(Session sess, User user) {
		sess.update(user);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findInfoByAccount(Session sess, String account) {
		String hql = " from User as u where u.userAccount='"+account+"'";
		return sess.createQuery(hql).list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findInfoByAccPwd(Session sess, String account,
			String password) {
		String hql = " from User as u where u.userAccount='"+account+"' and password='"+password+"'";
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getEntityById(Session sess, Integer id) {
		String hql = " from User as u where u.id='"+id;
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> checkUserPwd(Session sess, Integer id, String password) {
		String hql = " from User as u where u.id='"+id+" and u.password='"+password+"'";
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserInfoByoption(Session sess, String accName,
			String realName, Integer schoolId, Integer roleId, String prov,
			String city, String county, Integer schoolType, Integer gradeNo,
			Integer classId, Integer pageNo, Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql ="from  User as u where 1=1";
		if(!accName.equals("")){
			hql+=" and u.userAccount='"+accName+"'";
		}
		if(!accName.equals("")){
			hql+=" and u.realName='"+realName+"'";
		}
		hql+="exists(from RoleUserInfo as ru where u.id=ru.user.id";
		if(schoolId!=0){
			hql +=" and ru.schoolId="+schoolId;
		}
		if(roleId!=0){
			hql += " and ru.roleInfo.id ="+roleId;
		}
		if(prov!=""){
			hql +=" and ru.prov='"+prov+"'";
		}
		if(city != ""){
			hql +=" and ru.city='"+city+"'";
		}
		if(county!=""){
			hql += " and ru.county='"+county+"'";
		}
		if(schoolType!=0){
			hql +=" and ru.schoolType='"+schoolType+"'";
		}
		if(gradeNo!=0){
			hql +=" and ru.gradeNo='"+gradeNo+"'";
		}
		if(classId!=0){
			hql +=" and classId='"+classId+"'";
		}
		hql+=")";
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getUserByoptionCount(Session sess, String accName,
			String realName, Integer schoolId, Integer roleId, String prov,
			String city, String county, Integer schoolType, Integer gradeNo,
			Integer classId) {
		String hql ="select count(u.id) from  User as u where 1=1";
		if(!accName.equals("")){
			hql+=" and u.userAccount='"+accName+"'";
		}
		if(!accName.equals("")){
			hql+=" and u.realName='"+realName+"'";
		}
		hql+="exists(from RoleUserInfo as ru where u.id=ru.user.id";
		if(schoolId!=0){
			hql +=" and ru.schoolId="+schoolId;
		}
		if(roleId!=0){
			hql += " and ru.roleInfo.id ="+roleId;
		}
		if(prov!=""){
			hql +=" and ru.prov='"+prov+"'";
		}
		if(city != ""){
			hql +=" and ru.city='"+city+"'";
		}
		if(county!=""){
			hql += " and ru.county='"+county+"'";
		}
		if(schoolType!=0){
			hql +=" and ru.schoolType='"+schoolType+"'";
		}
		if(gradeNo!=0){
			hql +=" and ru.gradeNo='"+gradeNo+"'";
		}
		if(classId!=0){
			hql +=" and classId='"+classId+"'";
		}
		hql+=")";
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

}
