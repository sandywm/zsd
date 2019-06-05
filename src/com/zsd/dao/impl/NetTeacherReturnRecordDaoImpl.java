package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.NetTeacherReturnRecordDao;
import com.zsd.module.NetTeacherReturnRecord;
import com.zsd.tools.CommonTools;

/** 
 * @author zong
 * @version 2019-5-28 下午03:35:50
 */
public class NetTeacherReturnRecordDaoImpl implements NetTeacherReturnRecordDao {

	@Override
	public NetTeacherReturnRecord get(Session sess, int id) {
		return (NetTeacherReturnRecord) sess.load(NetTeacherReturnRecord.class, id);
	}

	@Override
	public void save(Session sess, NetTeacherReturnRecord nTrRecord) {
		sess.save(nTrRecord);
	}

	@Override
	public void delete(Session sess, NetTeacherReturnRecord nTrRecord) {
			sess.delete(nTrRecord);
	}

	@Override
	public void delete(Session sess, int id) {
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, NetTeacherReturnRecord nTrRecord) {
			sess.update(nTrRecord);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NetTeacherReturnRecord> findnTrRecordByNtId(Session sess,
			Integer ntId, Integer pageNo, Integer pageSize) {
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		String hql="from  NetTeacherReturnRecord as ntr  where ntr.netTeacherInfo.id="+ntId;
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getnTrRecordCount(Session sess, Integer ntId) {
		String hql="Select count(ntr.id) from  NetTeacherReturnRecord as ntr  where ntr.netTeacherInfo.id="+ntId;
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

}
