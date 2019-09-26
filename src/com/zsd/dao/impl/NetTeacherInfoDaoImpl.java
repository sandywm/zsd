package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherInfoDao;
import com.zsd.module.NetTeacherInfo;
import com.zsd.tools.CommonTools;

public class NetTeacherInfoDaoImpl implements NetTeacherInfoDao {

	@Override
	public NetTeacherInfo get(Session sess, int id) {
		
		return (NetTeacherInfo) sess.load(NetTeacherInfo.class, id);
	}

	@Override
	public void save(Session sess, NetTeacherInfo nt) {
		sess.save(nt);

	}

	@Override
	public void delete(Session sess, NetTeacherInfo nt) {
		sess.delete(nt);

	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, NetTeacherInfo nt) {
		sess.update(nt);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherInfo> findntInfoByuserId(Session sess, Integer uid) {
		String hql ="from  NetTeacherInfo as nt where nt.user.id="+uid;
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherInfo> findntInfoByTeaId(Session sess, Integer Id) {
		String hql ="from  NetTeacherInfo as nt where nt.id="+Id;
		return sess.createQuery(hql).list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherInfo> getNtByOption(Session sess,
			String accName, String realName, Integer checkSta,String sDate,String eDate, Integer pageNo,
			Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql ="from NetTeacherInfo as nt where 1=1 ";
		if(!accName.equals("")){
			hql+=" and nt.user.userAccount='"+accName+"'";
		}
		if(!realName.equals("")){
			hql+=" and nt.user.realName='"+realName+"'";
		}
		if(checkSta >= 0){
			hql+=" and nt.checkStatus="+checkSta;
		}
	
		if(!sDate.equals("") && eDate.equals("")){
			hql += " and substring(nt.user.signDate,1,10) >= '"+sDate+"'";
			hql += " and substring(nt.user.signDate,1,10) <= '"+eDate+"'";
		}
		
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getNtByOptionCount(Session sess, String accName,
			String realName, Integer checkSta, String sDate, String eDate) {
		String hql ="select count(nt.id) from NetTeacherInfo as nt where 1=1 ";
		if(!accName.equals("")){
			hql+=" and nt.user.userAccount='"+accName+"'";
		}
		if(!realName.equals("")){
			hql+=" and nt.user.realName='"+realName+"'";
		}
		if(checkSta >= 0){
			hql+=" and nt.checkStatus="+checkSta;
		}
	
		if(!sDate.equals("") && eDate.equals("")){
			hql += " and substring(nt.user.signDate,1,10) >= '"+sDate+"'";
			hql += " and substring(nt.user.signDate,1,10) <= '"+eDate+"'";
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkNtInfoByUserId(Session sess, Integer userId) {
		String hql ="from  NetTeacherInfo as nt where nt.user.id="+userId+"  and nt.checkStatus=2";
		List<NetTeacherInfo> ntlist= sess.createQuery(hql).list();
		if(ntlist.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

}
