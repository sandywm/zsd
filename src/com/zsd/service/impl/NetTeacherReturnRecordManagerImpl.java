package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.NetTeacherReturnRecordDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.NetTeacherReturnRecord;
import com.zsd.service.NetTeacherReturnRecordManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

/** 
 * @author zong
 * @version 2019-5-28 下午04:13:08
 */
public class NetTeacherReturnRecordManagerImpl implements NetTeacherReturnRecordManager {
	NetTeacherReturnRecordDao ntrDao = null;
	Transaction tran = null;
	@Override
	public List<NetTeacherReturnRecord> listnTrRecordByNtId(Integer ntId,
			Integer pageNo, Integer pageSize) throws WEBException {
		try {
			ntrDao= (NetTeacherReturnRecordDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_RETURN_RECORD);
			Session sess  = HibernateUtil.currentSession();
			return ntrDao.findnTrRecordByNtId(sess, ntId, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师编号分页获取 返现信息时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getnTrRecordCount(Integer ntId) throws WEBException {
		try {
			ntrDao= (NetTeacherReturnRecordDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_RETURN_RECORD);
			Session sess  = HibernateUtil.currentSession();
			return ntrDao.getnTrRecordCount(sess, ntId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师编号获取 返现信息记录数时出现异常!");
		}finally{
			HibernateUtil.closeSession();
		}
	}

}
