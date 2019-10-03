package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.EmailDao;
import com.zsd.module.Email;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class EmailDaoImpl implements EmailDao{

	@Override
	public Email getEntity(Session sess, int id) {
		// TODO Auto-generated method stub
		String hql = " from Email as e where e.id = "+id;
		List<Email> l = sess.createQuery(hql).list();
		if(l.size() > 0){
			return l.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, Email email) {
		// TODO Auto-generated method stub
		sess.save(email);
	}

	@Override
	public void delete(Session sess, Email email) {
		// TODO Auto-generated method stub
		sess.delete(email);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.getEntity(sess, id));
	}

	@Override
	public void update(Session sess, Email email) {
		// TODO Auto-generated method stub
		sess.update(email);
	}

	@Override
	public List<Email> findPageInfoByOpt(Session sess, Integer userId,
			String title, String sDate, String eDate, String emailType,
			int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		String hql = " from Email as e where 1 = 1";
		if(userId > 0){
			hql += " and e.userByToUserId.id = "+userId;
		}
		if(!title.equals("")){
			hql += " and e.emailTitle like '%"+title+"%'";
		}
		if(!sDate.equals("") && !eDate.equals("")){
			hql += " and substring(e.sendTime,1,10) >= '"+sDate+"' and substring(e.sendTime,1,10) <= '"+eDate+"'";
		}
		if(!emailType.equals("")){
			hql += " and e.emailType = '"+emailType+"'";
		}
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, Integer userId, String title,
			String sDate, String eDate, String emailType) {
		// TODO Auto-generated method stub
		String hql = "select count(e.id) from Email as e where 1 = 1";
		if(userId > 0){
			hql += " and e.userByToUserId.id = "+userId;
		}
		if(!title.equals("")){
			hql += " and e.emailTitle like '%"+title+"%'";
		}
		if(!sDate.equals("") && !eDate.equals("")){
			hql += " and substring(e.sendTime,1,10) >= '"+sDate+"' and substring(e.sendTime,1,10) <= '"+eDate+"'";
		}
		if(!emailType.equals("")){
			hql += " and e.emailType = '"+emailType+"'";
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public Integer getUnReadCount(Session sess, Integer userId) {
		// TODO Auto-generated method stub
		String hql = "select count(e.id)  from Email as e where e.readStatus = 0 and e.userByToUserId.id = "+userId;
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}
}
