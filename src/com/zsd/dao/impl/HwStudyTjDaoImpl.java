package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.HwStudyTjDao;
import com.zsd.module.HwStudyTjInfo;
import com.zsd.tools.CommonTools;

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
			Integer hwSendId, Integer stuId, Integer comStatus, Integer pageNo,
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
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
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

}
