package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.HwStudyDetailDao;
import com.zsd.module.HwStudyDetailInfo;

@SuppressWarnings("unchecked")
public class HwStudyDetailDaoImpl implements HwStudyDetailDao{

	
	@Override
	public HwStudyDetailInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql =  " from HwStudyDetailInfo as hwsd where hwsd.id = "+id;
		List<HwStudyDetailInfo> hwsdList = sess.createQuery(hql).list();
		if(hwsdList.size() > 0){
			return hwsdList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, HwStudyDetailInfo hwsd) {
		// TODO Auto-generated method stub
		sess.save(hwsd);
	}

	@Override
	public void update(Session sess, HwStudyDetailInfo hwsd) {
		// TODO Auto-generated method stub
		sess.update(hwsd);
	}

	@Override
	public List<HwStudyDetailInfo> findInfoByOpt(Session sess,
			Integer sendHwId, Integer hwTjId, Integer queId, String queArea) {
		// TODO Auto-generated method stub
		String hql =  " from HwStudyDetailInfo as hwsd where 1 = 1";
		if(sendHwId > 0){
			hql += " and hwsd.hwStudyTjInfo.sendHwInfo.id = "+sendHwId;
		}
		if(hwTjId > 0){
			hql += " and hwsd.hwStudyTjInfo.id = "+hwTjId;
		}
		if(queId > 0){
			hql += " and hwsd.queId = "+queId;
		}
		if(!queArea.equals("")){
			hql += " and hwsd.queArea = '"+queArea+"'";
		}
		return sess.createQuery(hql).list();
	}

}
