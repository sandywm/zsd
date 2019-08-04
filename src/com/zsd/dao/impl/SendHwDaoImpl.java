package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.SendHwDao;
import com.zsd.module.SendHwInfo;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class SendHwDaoImpl implements SendHwDao{

	@Override
	public SendHwInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from SendHwInfo as shw where shw.id = "+id;
		List<SendHwInfo> sList = sess.createQuery(hql).list();
		if(sList.size() > 0){
			return sList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, SendHwInfo sw) {
		// TODO Auto-generated method stub
		sess.save(sw);
	}

	@Override
	public void update(Session sess, SendHwInfo sw) {
		// TODO Auto-generated method stub
		sess.update(sw);
	}

	@Override
	public List<SendHwInfo> findPageInfoByOpt(Session sess, Integer sendUserId,
			Integer classId, Integer hwType, Integer inUse, String sDate, String eDate,boolean pageFlag,
			Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from SendHwInfo as shw where 1=1";
		if(!sDate.equals("") && !eDate.equals("")){
			hql += " and substring(shw.sendDate,1,10) >= '"+ sDate + "' and substring(shw.sendDate,1,10) >= '"+ eDate + "'";
		}
		if(sendUserId > 0){
			hql += " and shw.user.id = "+sendUserId;
		}
		if(classId > 0){
			hql += " and shw.classInfo.id = "+classId;
		}
		if(hwType > 0){
			hql += " and shw.hwType.id = "+hwType;
		}
		if(inUse >= 0){
			hql += " and shw.inUse = "+inUse;
		}
		hql += " order by shw.sendDate desc";
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
	public Integer getCountByOpt(Session sess, Integer sendUserId,
			Integer classId, Integer hwType ,Integer inUse, String sDate, String eDate) {
		// TODO Auto-generated method stub
		String hql = "select count(shw.id) from SendHwInfo as shw where 1=1";
		if(!sDate.equals("") && !eDate.equals("")){
			hql += " and substring(shw.sendDate,1,10) >= '"+ sDate + "' and substring(shw.sendDate,1,10) >= '"+ eDate + "'";
		}
		if(sendUserId > 0){
			hql += " and shw.user.id = "+sendUserId;
		}
		if(classId > 0){
			hql += " and shw.classInfo.id = "+classId;
		}
		if(hwType >= 0){
			hql += " and shw.hwType.id = "+hwType;
		}
		if(inUse >= 0){
			hql += " and shw.inUse = "+inUse;
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

}
