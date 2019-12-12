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
		String hql = " from User as u where u.id="+id;
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> checkUserPwd(Session sess, Integer id, String password) {
		String hql = " from User as u where u.id="+id+" and u.password='"+password+"'";
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserInfoByoption(Session sess, String accName,
			String realName, Integer schoolId, Integer roleId, String prov,
			String city, String county,String town, Integer schoolType, Integer gradeNo,
			Integer classId, Integer pageNo, Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql = " from  User as u where 1=1";
		if(!accName.equals("")){
			hql+=" and u.userAccount like '%"+accName+"%'";
		}
		if(!realName.equals("")){
			hql+=" and u.realName like '%"+realName+"%'";
		}
		hql+=" and exists(select ru.id from RoleUserInfo as ru where u.id=ru.user.id";
		if(schoolId!=0){
			hql +=" and ru.schoolId="+schoolId;
		}
		if(roleId!=0){
			hql += " and ru.roleInfo.id ="+roleId;
		}
		if(!prov.equals("")){
			hql +=" and ru.prov='"+prov+"'";
		}
		if(!city.equals("")){
			hql +=" and ru.city='"+city+"'";
		}
		if(!county.equals("")){
			hql += " and ru.county='"+county+"'";
		}
		if(!town.equals("")){
			hql += " and ru.town='"+town+"'";
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
			String city, String county,String town, Integer schoolType, Integer gradeNo,
			Integer classId) {
		String hql ="select count(u.id) from  User as u where 1=1";
		if(!accName.equals("")){
			hql+=" and u.userAccount like '%"+accName+"%'";
		}
		if(!realName.equals("")){
			hql+=" and u.realName like '%"+realName+"%'";
		}
		hql+=" and exists(select ru.id from RoleUserInfo as ru where u.id=ru.user.id";
		if(schoolId!=0){
			hql +=" and ru.schoolId="+schoolId;
		}
		if(roleId!=0){
			hql += " and ru.roleInfo.id ="+roleId;
		}
		if(!prov.equals("")){
			hql +=" and ru.prov='"+prov+"'";
		}
		if(!city.equals("")){
			hql +=" and ru.city='"+city+"'";
		}
		if(!county.equals("")){
			hql += " and ru.county='"+county+"'";
		}
		if(!town.equals("")){
			hql += " and ru.town='"+town+"'";
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

	@SuppressWarnings("unchecked")
	@Override
	public List<User> checkUserMobile(Session sess, String mobile) {
		String hql = " from User as u where u.mobile='"+mobile+"'";
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findPageInfoByOpt(Session sess, String stuName,
			String prov, String city, String county, String town,
			Integer schoolType, Integer schoolId, Integer gradeNo,
			Integer classId, Integer userId,Integer pageNo,Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from User as u where u.id in(select ru.user.id from RoleUserInfo as ru where ru.roleInfo.id = 2)";
		if(!stuName.equals("")){
			hql += " and u.realName = '"+stuName+"'";
		}else if(userId > 0){//指定学生
			hql += " and u.id = "+userId;
		}else if(classId > 0){//指定班级
			hql += " and u.id in(select ru.user.id from RoleUserInfo as ru where ru.classId = "+classId+")";
		}else if(gradeNo > 0){//指定年级--附带着学校
			hql += " and u.id in(select ru.user.id from RoleUserInfo as ru where ru.gradeNo = "+gradeNo+" and ru.schoolId = "+schoolId+")";
		}else if(schoolId > 0){
			hql += " and u.schoolId = "+schoolId;
		}else if(schoolType > 0){//指定学段--附带上乡镇
			hql += " and u.id in(select ru.user.id from RoleUserInfo as ru where ru.schoolType = "+schoolType+" and ru.town = '"+town+"')";
		}else if(!town.equals("")){//指定镇
			hql += " and u.id in(select ru.user.id from RoleUserInfo as ru where ru.town = '"+town+"')";
		}else if(!county.equals("")){//指定县
			hql += " and u.in(select ru.user.id from RoleUserInfo as ru where ru.county = '"+county+"')";
		}else if(!city.equals("")){//指定市
			hql += " and u.id in(select ru.user.id from RoleUserInfo as ru where ru.city = '"+city+"')";
		}else if(!prov.equals("")){//指定省
			hql += " and u.id in(select ru.user.id from RoleUserInfo as ru where ru.prov = '"+prov+"')";
		}
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return 	sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, String stuName, String prov,
			String city, String county, String town, Integer schoolType,
			Integer schoolId, Integer gradeNo, Integer classId, Integer userId) {
		// TODO Auto-generated method stub
		String hql = "select count(u.id) from User as u where u.id in(select ru.user.id from RoleUserInfo as ru where ru.roleInfo.id = 2)";
		if(!stuName.equals("")){
			hql += " and u.realName = '"+stuName+"'";
		}else if(userId > 0){//指定学生
			hql += " and u.id = "+userId;
		}else if(classId > 0){//指定班级
			hql += " and u.id in(select ru.user.id from RoleUserInfo as ru where ru.classId = "+classId+")";
		}else if(gradeNo > 0){//指定年级--附带着学校
			hql += " and u.id in(select ru.user.id from RoleUserInfo as ru where ru.gradeNo = "+gradeNo+" and ru.schoolId = "+schoolId+")";
		}else if(schoolId > 0){
			hql += " and u.schoolId = "+schoolId;
		}else if(schoolType > 0){//指定学段--附带上乡镇
			hql += " and u.id in(select ru.user.id from RoleUserInfo as ru where ru.schoolType = "+schoolType+" and ru.town = '"+town+"')";
		}else if(!town.equals("")){//指定镇
			hql += " and u.id in(select ru.user.id from RoleUserInfo as ru where ru.town = '"+town+"')";
		}else if(!county.equals("")){//指定县
			hql += " and u.in(select ru.user.id from RoleUserInfo as ru where ru.county = '"+county+"')";
		}else if(!city.equals("")){//指定市
			hql += " and u.id in(select ru.user.id from RoleUserInfo as ru where ru.city = '"+city+"')";
		}else if(!prov.equals("")){//指定省
			hql += " and u.id in(select ru.user.id from RoleUserInfo as ru where ru.prov = '"+prov+"')";
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

}
