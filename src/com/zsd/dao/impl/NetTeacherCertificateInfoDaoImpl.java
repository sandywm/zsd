package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherCertificateInfoDao;
import com.zsd.module.NetTeacherCertificateInfo;
import com.zsd.tools.CommonTools;

/** 
 * @author zong
 * @version 2019-5-13 下午05:27:55
 */
public class NetTeacherCertificateInfoDaoImpl implements
		NetTeacherCertificateInfoDao {

	@Override
	public NetTeacherCertificateInfo get(Session sess, int id) {
	
		return (NetTeacherCertificateInfo) sess.load(NetTeacherCertificateInfo.class, id);
	}

	@Override
	public void save(Session sess, NetTeacherCertificateInfo ntcInfo) {
		sess.save(ntcInfo);

	}

	@Override
	public void delete(Session sess, NetTeacherCertificateInfo ntcInfo) {
		sess.delete(ntcInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, NetTeacherCertificateInfo ntcInfo) {
		sess.update(ntcInfo);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherCertificateInfo> getEntityByid(Session sess,
			Integer id) {
		String hql ="from NetTeacherCertificateInfo as ntc where id= "+id;
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherCertificateInfo> getNtcByOption(Session sess,
			String accName, String realName, Integer checkSta,String sDate,String eDate, Integer pageNo,
			Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql ="from NetTeacherCertificateInfo as ntc where 1=1 ";
		if(!accName.equals("")){
			hql+=" and ntc.netTeacherInfo.user.userAccount='"+accName+"'";
		}
		if(!realName.equals("")){
			hql+=" and ntc.netTeacherInfo.user.realName='"+realName+"'";
		}
		if(checkSta > 0){
			hql+=" and ntc.checkStatus="+checkSta;
		}
	
		if(!sDate.equals("") && eDate.equals("")){
			hql += " and substring(ntc.netTeacherInfo.user.signDate,1,10) >= '"+sDate+"'";
			hql += " and substring(ntc.netTeacherInfo.user.signDate,1,10) <= '"+eDate+"'";
		}
		
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getNtcByOptionCount(Session sess, String accName,
			String realName, Integer checkSta, String sDate, String eDate) {
		String hql ="from NetTeacherCertificateInfo as ntc where 1=1 ";
		if(!accName.equals("")){
			hql+=" and ntc.netTeacherInfo.user.userAccount='"+accName+"'";
		}
		if(!realName.equals("")){
			hql+=" and ntc.netTeacherInfo.user.realName='"+realName+"'";
		}
		if(checkSta > 0){
			hql+=" and ntc.checkStatus="+checkSta;
		}
	
		if(!sDate.equals("") && eDate.equals("")){
			hql += " and substring(ntc.netTeacherInfo.user.signDate,1,10) >= '"+sDate+"'";
			hql += " and substring(ntc.netTeacherInfo.user.signDate,1,10) <= '"+eDate+"'";
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

}
