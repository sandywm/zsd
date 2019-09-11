package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherTxRecordDao;
import com.zsd.module.NetTeacherTxRecord;
import com.zsd.tools.CommonTools;

/** 
 * @author zong
 * @version 2019-5-25 上午09:55:44
 */
public class NetTeacherTxRecordDaoImpl implements NetTeacherTxRecordDao {

	@Override
	public NetTeacherTxRecord get(Session sess, int id) {
		
		return (NetTeacherTxRecord) sess.load(NetTeacherTxRecord.class, id);
	}

	@Override
	public void save(Session sess, NetTeacherTxRecord nTxRecord) {
		sess.save(nTxRecord);
	}

	@Override
	public void delete(Session sess, NetTeacherTxRecord nTxRecord) {
		 sess.delete(nTxRecord);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, NetTeacherTxRecord nTxRecord) {
		sess.update(nTxRecord);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherTxRecord> findnTxReCordByNtId(Session sess,Integer ntId,Integer operId) {
		String hql="from  NetTeacherTxRecord as ntx  where ntx.netTeacherInfo.id="+ntId;
		if(operId.equals(0)){
			hql+=" and ntx.operateUserId=0";
		}else if(operId>0){
			hql +=" and ntx.operateUserId >0";
		}
		return sess.createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherTxRecord> findnTxReCordByNtId(Session sess,
			Integer userId, Integer pageNo, Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql="from  NetTeacherTxRecord as ntx  where ntx.netTeacherInfo.user.id="+userId+" order by txDate desc";
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getnTxReCordCount(Session sess, Integer userId) {
		String hql="Select count(ntx.id) from  NetTeacherTxRecord as ntx  where ntx.netTeacherInfo.user.id="+userId;
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherTxRecord> findnTxReCordById(Session sess, Integer Id) {
		String hql="from  NetTeacherTxRecord as ntx  where ntx.id="+Id;
		return sess.createQuery(hql).list();
	}

}
