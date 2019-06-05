package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.StudentPayOrderInfoDao;
import com.zsd.module.StudentPayOrderInfo;
import com.zsd.tools.CommonTools;

/** 
 * @author zong
 * @version 2019-5-25 上午09:40:25
 */
public class StudentPayOrderInfoDaoImpl implements StudentPayOrderInfoDao {

	@Override
	public StudentPayOrderInfo get(Session sess, int id) {
		return (StudentPayOrderInfo) sess.load(StudentPayOrderInfo.class, id);
	}

	@Override
	public void save(Session sess, StudentPayOrderInfo sPayOrderInfo) {
		sess.save(sPayOrderInfo);
	}

	@Override
	public void delete(Session sess, StudentPayOrderInfo sPayOrderInfo) {
		sess.delete(sPayOrderInfo);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, StudentPayOrderInfo sPayOrderInfo) {
		sess.update(sPayOrderInfo);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentPayOrderInfo> findSpayOrderInfoByOpt(Session sess,
			Integer ntsId, Integer comSta) {
		String hql="from StudentPayOrderInfo as spo where spo.ntsId="+ntsId+" and spo.comStatus="+comSta;
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentPayOrderInfo> findSpayOrderInfoByOpt(Session sess,
			Integer ntsId, Integer pageNo, Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql="from StudentPayOrderInfo as spo where spo.ntsId="+ntsId+" and spo.comStatus=1";
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getspOrderInfoCount(Session sess,Integer ntsId) {
		String hql="Select count(spo.id) from StudentPayOrderInfo as spo where spo.ntsId="+ntsId+" and spo.comStatus=1";
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

}
