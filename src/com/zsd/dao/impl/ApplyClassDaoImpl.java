package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.ApplyClassDao;
import com.zsd.module.ApplyClassInfo;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class ApplyClassDaoImpl implements ApplyClassDao{

	@Override
	public ApplyClassInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from ApplyClassInfo as ac where ac.id = "+id;
		List<ApplyClassInfo> acList = sess.createQuery(hql).list();
		if(acList.size() > 0){
			return acList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, ApplyClassInfo ac) {
		// TODO Auto-generated method stub
		sess.save(ac);
	}

	@Override
	public void update(Session sess, ApplyClassInfo ac) {
		// TODO Auto-generated method stub
		sess.update(ac);
	}

	@Override
	public List<ApplyClassInfo> findPageInfoByOpt(Session sess, Integer userId,Integer toUserId,Integer checkStatus,String sDate,String eDate,Integer pageNo,Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from ApplyClassInfo as ac where 1 = 1";
		if(userId > 0){
			hql  += " and ac.user.id = "+userId;
		}
		if(toUserId > 0){
			hql += " and ac.toUserId = "+toUserId;
		}
		if(checkStatus >= 0){
			hql += " and ac.checkStatus = "+checkStatus;
		}
		if(!sDate.equals("") && !eDate.equals("")){
			hql += " and substring(bs.applyTime,1,10) >= '"+ sDate + "' and substring(bs.applyTime,1,10) >= '"+ eDate + "'";
		}
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, Integer userId,
			Integer toUserId, Integer checkStatus,String sDate,String eDate) {
		// TODO Auto-generated method stub
		String hql = "select count(ac.id) from ApplyClassInfo as ac where ac.checkStatus = "+checkStatus;
		if(userId > 0){
			hql  += " and ac.user.id = "+userId;
		}
		if(toUserId > 0){
			hql += " and ac.toUserId = "+toUserId;
		}
		if(!sDate.equals("") && !eDate.equals("")){
			hql += " and substring(bs.applyTime,1,10) >= '"+ sDate + "' and substring(bs.applyTime,1,10) >= '"+ eDate + "'";
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<ApplyClassInfo> findMyUnCheckApplyInfo(Session sess,
			Integer toUserId,Integer classId) {
		// TODO Auto-generated method stub
		String hql = " from ApplyClassInfo as ac where ac.toUserId = "+toUserId + " and ac.checkStatus = 0";
		if(classId > 0){
			hql += " and ac.classInfo.id = "+classId;
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public List<ApplyClassInfo> findAllUnCheckApplyInfo(Session sess) {
		// TODO Auto-generated method stub
		String hql = " from ApplyClassInfo as ac where ac.checkStatus = 0";
		return sess.createQuery(hql).list();
	}

}
