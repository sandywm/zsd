package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.HwStudyTjDao;
import com.zsd.module.HwStudyTjInfo;
import com.zsd.tools.CommonTools;
import com.zsd.tools.CurrentTime;

@SuppressWarnings("unchecked")
public class HwStudyTjDaoImpl implements HwStudyTjDao{

	@Override
	public HwStudyTjInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from HwStudyTjInfo as hwtj where hwtj.id = "+id;
		List<HwStudyTjInfo> sList = sess.createQuery(hql).list();
		if(sList.size() > 0){
			return sList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, HwStudyTjInfo hwTj) {
		// TODO Auto-generated method stub
		sess.save(hwTj);
	}

	@Override
	public void update(Session sess, HwStudyTjInfo hwTj) {
		// TODO Auto-generated method stub
		sess.update(hwTj);
	}

	@Override
	public List<HwStudyTjInfo> findPageInfoByOpt(Session sess,
			Integer hwSendId, Integer stuId, Integer comStatus, boolean pageFlag,Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from HwStudyTjInfo as hwtj where 1 = 1";
		if(hwSendId > 0){
			hql += " and hwtj.sendHwInfo.id = "+hwSendId;
		}
		if(stuId > 0){
			hql += " and hwtj.user.id = "+stuId;
		}
		if(comStatus >= 0){
			hql += " and hwtj.comStatus = "+comStatus;
		}
		hql += " order by hwtj.hwScore desc";
		if(pageFlag){
			int offset = (pageNo - 1) * pageSize;
			if (offset < 0) {
				offset = 0;
			}
			return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, Integer hwSendId, Integer stuId,
			Integer comStatus) {
		// TODO Auto-generated method stub
		String hql = "select count(hwtj.id) from HwStudyTjInfo as hwtj where 1 = 1";
		if(hwSendId > 0){
			hql += " and hwtj.sendHwInfo.id = "+hwSendId;
		}
		if(stuId > 0){
			hql += " and hwtj.user.id = "+stuId;
		}
		if(comStatus >= 0){
			hql += " and hwtj.comStatus = "+comStatus;
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<HwStudyTjInfo> findPageInfoByOpt_1(Session sess, Integer hwType,Integer subId,
			Integer stuId, Integer comStatus, String sDate, String eDate,
			boolean pageFlag, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from HwStudyTjInfo as hwtj where 1 = 1";
		if(hwType > 0){
			hql += " and hwtj.sendHwInfo.hwType = "+hwType;
		}
		if(subId > 0){
			hql += " and hwtj.sendHwInfo.subject.id = "+subId;
		}
		if(stuId > 0){
			hql += " and hwtj.user.id = "+stuId;
		}
		if(comStatus >= 0){
			hql += " and hwtj.comStatus = "+comStatus;
		}
		if(!sDate.equals("") && !eDate.equals("")){
			hql += " and substring(hwtj.sendHwInfo.sendDate,1,10) >= '"+sDate+"' and substring(hwtj.sendHwInfo.sendDate,1,10) <= '"+eDate+"'";
		}
		hql += " order by hwtj.sendHwInfo.sendDate desc";
		if(pageFlag){
			int offset = (pageNo - 1) * pageSize;
			if (offset < 0) {
				offset = 0;
			}
			return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public Integer getCountByOpt_1(Session sess, Integer hwType,Integer subId, Integer stuId,
			Integer comStatus, String sDate, String eDate) {
		// TODO Auto-generated method stub
		String hql = "select count(hwtj.id) from HwStudyTjInfo as hwtj where 1 = 1";
		if(hwType > 0){
			hql += " and hwtj.sendHwInfo.hwType = "+hwType;
		}
		if(subId > 0){
			hql += " and hwtj.sendHwInfo.subject.id = "+subId;
		}
		if(stuId > 0){
			hql += " and hwtj.user.id = "+stuId;
		}
		if(comStatus >= 0){
			hql += " and hwtj.comStatus = "+comStatus;
		}
		if(!sDate.equals("") && !eDate.equals("")){
			hql += " and substring(hwtj.sendHwInfo.sendDate,1,10) >= '"+sDate+"' and substring(hwtj.sendHwInfo.sendDate,1,10) <= '"+eDate+"'";
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<HwStudyTjInfo> findInfoBySendHwId(Session sess, Integer sendHwId,Integer stuId) {
		// TODO Auto-generated method stub
		String hql = " from HwStudyTjInfo as hwtj where hwtj.sendHwInfo.id = "+sendHwId + " and hwtj.user.id = "+stuId;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<HwStudyTjInfo> findPageInfoByOpt_2(Session sess,
			Integer hwType, Integer subId, Integer stuId, Integer comStatus,
			Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from HwStudyTjInfo as hwtj where 1 = 1";
		if(hwType > 0){
			hql += " and hwtj.sendHwInfo.hwType = "+hwType;
		}
		if(subId > 0){
			hql += " and hwtj.sendHwInfo.subject.id = "+subId;
		}
		if(stuId > 0){
			hql += " and hwtj.user.id = "+stuId;
		}
		if(comStatus >= 0){
			hql += " and hwtj.comStatus = "+comStatus;
		}
		hql += " and substring(hwtj.sendHwInfo.sendDate,1,10) < '"+CurrentTime.getStringDate()+"'";
		hql += " order by hwtj.sendHwInfo.sendDate desc";
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

}
