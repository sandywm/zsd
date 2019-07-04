package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.BuffetStudyDetailDao;
import com.zsd.module.BuffetStudyDetailInfo;

@SuppressWarnings("unchecked")
public class BuffetStudyDetailDaoImpl implements BuffetStudyDetailDao{

	@Override
	public BuffetStudyDetailInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = "from BuffetStudyDetailInfo as bsd where bsd.id = "+id;
		List<BuffetStudyDetailInfo> list = sess.createQuery(hql).list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, BuffetStudyDetailInfo bsd) {
		// TODO Auto-generated method stub
		sess.save(bsd);
	}

	@Override
	public void update(Session sess, BuffetStudyDetailInfo bsd) {
		// TODO Auto-generated method stub
		sess.update(bsd);
	}

	@Override
	public List<BuffetStudyDetailInfo> findInfoByBsdId(Session sess,
			Integer bsId) {
		// TODO Auto-generated method stub
		String hql = " from  BuffetStudyDetailInfo as bsd where bsd.buffetSendInfo.id = "+bsId;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<BuffetStudyDetailInfo> findInfoByStuId(Session sess,
			Integer stuId, String subName, Integer succFlag) {
		String hql=" from BuffetStudyDetailInfo as bsd where bsd.buffetSendInfo.studyLogInfo.user.id="+stuId;
		if(!subName.equals("all")){
			hql += " and bsd.buffetSendInfo.studyLogInfo.subject.subName = '"+ subName +"'";
		}
		if(!succFlag.equals(-1)){
			hql += " and bsd.result = "+succFlag;
		}
		return sess.createQuery(hql).list();
	}

	

}
