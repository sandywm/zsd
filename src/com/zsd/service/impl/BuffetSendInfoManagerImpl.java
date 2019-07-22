package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetSendInfoDao;
import com.zsd.dao.StudyLogDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.BuffetSendInfo;
import com.zsd.service.BuffetSendInfoManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class BuffetSendInfoManagerImpl implements BuffetSendInfoManager {
	BuffetSendInfoDao bsDao = null;
	StudyLogDao slDao = null;
	UserDao uDao = null;
	Transaction tran = null;
	@Override
	public List<BuffetSendInfo> listBsInfoByOption(Integer stuId,
			Integer subId, Integer isfinish, String starttime, String endtime)
			throws WEBException {
		
		try {
			bsDao = (BuffetSendInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_SEND_INFO);
			Session sess = HibernateUtil.currentSession();
			return bsDao.findBsInfoByOption(sess, stuId, subId, isfinish, starttime, endtime);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据指定学生,学科,完成状态,时间段获取自助餐发布信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<BuffetSendInfo> listBsInfoById(Integer Id) throws WEBException {
		try {
			bsDao = (BuffetSendInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_SEND_INFO);
			Session sess = HibernateUtil.currentSession();
			return bsDao.findBsInfoById(sess, Id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据主键获取自助餐发布信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
}

	@Override
	public List<BuffetSendInfo> listPageInfoByOption(Integer stuId,
			Integer subId, Integer isfinish, String sDate, String eDate,
			Integer pageNo, Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bsDao = (BuffetSendInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_SEND_INFO);
			Session sess = HibernateUtil.currentSession();
			return bsDao.findPageInfoByOption(sess, stuId, subId, isfinish, sDate, eDate, pageNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据指定学生,学科,完成状态,时间段分页获取自助餐发布信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public Integer addBuffetSend(Integer studyLogId,String sendTime,Integer sendUserId,Integer sendMode,Integer allNumber) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bsDao = (BuffetSendInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_SEND_INFO);
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetSendInfo bs = new BuffetSendInfo(slDao.getEntityById(sess, studyLogId), uDao.get(sess, sendUserId),
					CurrentTime.getCurrentTime(), 1, sendMode,0, allNumber);
			bsDao.save(sess, bs);
			tran.commit();
			return bs.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加自助餐发布信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public boolean updateBuffetSend(Integer id,Integer isfinish,Integer completeNumber) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bsDao = (BuffetSendInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_SEND_INFO);
			slDao = (StudyLogDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_LOG_INFO);
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetSendInfo bs = bsDao.get(sess, id);
			if(bs != null){
				if(!isfinish.equals(0)){
					bs.setStudyResult(isfinish);
				}
				if(completeNumber.equals(1) && (bs.getSendNumber() > bs.getComNumber())){
					bs.setComNumber(bs.getComNumber() + 1);
				}
				bsDao.update(sess, bs);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定主键的学习结果、完成次数信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<BuffetSendInfo> listBsInfoByStudyLogId(Integer studyLogid)
			throws WEBException {
		try {
			bsDao = (BuffetSendInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_SEND_INFO);
			Session sess = HibernateUtil.currentSession();
			return bsDao.findBsInfoByStudyLogId(sess, studyLogid);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据学习记录主键查询自助餐发送信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}


}
