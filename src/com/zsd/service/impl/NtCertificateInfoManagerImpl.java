package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.NetTeacherCertificateInfoDao;
import com.zsd.dao.NetTeacherInfoDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.NetTeacherCertificateInfo;
import com.zsd.module.NetTeacherInfo;
import com.zsd.service.NtCertificateInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

/** 
 * @author zong
 * @version 2019-5-14 上午09:12:43
 */
public class NtCertificateInfoManagerImpl implements NtCertificateInfoManager {
	NetTeacherCertificateInfoDao ntcDao = null;
	NetTeacherInfoDao ntDao = null;
	Transaction tran = null;
	@Override
	public Integer addNtcInfo(Integer ntId, String icardImgFrontBig,
			String icardImgBackBig, String icardImgFrontSmall,
			String icardImgBackSmall, String icardName, String icardNum,
			String zgzImgBig, String zgzImgSmall, String xlzImgBig,
			String xlzImgSmall, Integer checkUserId, String checkUserAccount,
			Integer checkStatus, String checkTime, String checkReasonICard,
			String checkReasonZgz, String checkReasonXlz) throws WEBException {
		try {
			ntcDao =(NetTeacherCertificateInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_CERTIFICATE_INFO);
			ntDao = (NetTeacherInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherInfo netTeacherInfo = ntDao.get(sess, ntId);
			NetTeacherCertificateInfo ntc =  new NetTeacherCertificateInfo(netTeacherInfo, icardImgFrontBig, icardImgBackBig, icardImgFrontSmall, icardImgBackSmall, icardName, icardNum, zgzImgBig, zgzImgSmall, xlzImgBig, xlzImgSmall, checkUserId, checkUserAccount, checkStatus, checkTime, checkReasonICard, checkReasonZgz, checkReasonXlz);
			ntcDao.save(sess, ntc);
			tran.commit();
			return ntc.getId();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("增加网络导师证件信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateNtcInfo(Integer id,String icardImgFrontBig, String icardImgBackBig,
			String icardImgFrontSmall, String icardImgBackSmall,
			String icardName, String icardNum, String zgzImgBig,
			String zgzImgSmall, String xlzImgBig, String xlzImgSmall)
			throws WEBException {
		try {
			ntcDao =(NetTeacherCertificateInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_CERTIFICATE_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherCertificateInfo ntc = ntcDao.get(sess, id);
			if(ntc != null){
				if(!icardImgFrontBig.equals("")||icardImgFrontBig!=ntc.getIcardImgFrontBig()){
					ntc.setIcardImgFrontBig(icardImgFrontBig);	
				}
				if(!icardImgBackBig.equals("")||icardImgBackBig!=ntc.getIcardImgBackBig()){
					ntc.setIcardImgBackBig(icardImgBackBig);
				}
				if(!icardImgFrontSmall.equals("")||icardImgFrontSmall!=ntc.getIcardImgFrontSmall()){
					ntc.setIcardImgFrontSmall(icardImgFrontSmall);
				}
				if(!icardImgBackSmall.equals("")||icardImgBackSmall!=ntc.getIcardImgBackSmall()){
					ntc.setIcardImgBackSmall(icardImgBackSmall);
				}
				if(!icardName.equals("")||icardName!=ntc.getIcardName()){
					ntc.setIcardName(icardName);
				}
				if(!icardNum.equals("")||icardNum!=ntc.getIcardNum()){
					ntc.setIcardNum(icardNum);
				}
				if(!zgzImgBig.equals("")||zgzImgBig!=ntc.getZgzImgBig()){
					ntc.setZgzImgBig(zgzImgBig);
				}
				if(!zgzImgSmall.equals("")||zgzImgSmall!=ntc.getZgzImgSmall()){
					ntc.setZgzImgSmall(zgzImgSmall);
				}
				if(!xlzImgBig.equals("")||xlzImgBig!=ntc.getXlzImgBig()){
					ntc.setXlzImgBig(xlzImgBig);
				}
				if(!xlzImgSmall.equals("")||xlzImgSmall!=ntc.getXlzImgSmall()){
					ntc.setXlzImgSmall(xlzImgSmall);
				}
				ntcDao.update(sess, ntc);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定网络导师证件信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateNtcByCheck(Integer id, Integer checkUserId,
			String checkUserAccount, Integer checkStatus, String checkTime,
			String checkReasonICard, String checkReasonZgz,
			String checkReasonXlz) throws WEBException {
		try {
			ntcDao =(NetTeacherCertificateInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_CERTIFICATE_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherCertificateInfo ntc = ntcDao.get(sess, id);
			if(ntc != null){
				ntc.setCheckUserId(checkUserId);
				ntc.setCheckUserAccount(checkUserAccount);
				ntc.setCheckStatus(checkStatus);
				ntc.setCheckTime(checkTime);
				ntc.setCheckReasonICard(checkReasonICard);
				ntc.setCheckReasonZgz(checkReasonZgz);
				ntc.setCheckReasonXlz(checkReasonXlz);
				ntcDao.update(sess, ntc);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定网络导师证件审核信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<NetTeacherCertificateInfo> listEntityByid(Integer id)throws WEBException {
		try {
			ntcDao =(NetTeacherCertificateInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_CERTIFICATE_INFO);
			Session sess  = HibernateUtil.currentSession();
			return ntcDao.getEntityByid(sess, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据主键获取网络导师证件信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<NetTeacherCertificateInfo> getNtcByTeaId(Integer teaId)
			throws WEBException {
		try {
			ntcDao =(NetTeacherCertificateInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_CERTIFICATE_INFO);
			Session sess  = HibernateUtil.currentSession();
			return ntcDao.getNtcByTeaId(sess, teaId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据网络导师主键获取网络导师证件信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateiCardInfo(Integer id, String icardImgFrontBig,
			String icardImgBackBig, String icardImgFrontSmall,
			String icardImgBackSmall, String icardName, String icardNum)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			ntcDao =(NetTeacherCertificateInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_CERTIFICATE_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherCertificateInfo ntc = ntcDao.get(sess, id);
			if(ntc != null){
				ntc.setIcardImgFrontBig(icardImgFrontBig);	
				ntc.setIcardImgBackBig(icardImgBackBig);
				ntc.setIcardImgFrontSmall(icardImgFrontSmall);
				ntc.setIcardImgBackSmall(icardImgBackSmall);
				if(!icardName.equals("")){
					ntc.setIcardName(icardName);
				}
				if(!icardNum.equals("")){
					ntc.setIcardNum(icardNum);
				}
				ntcDao.update(sess, ntc);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定网络导师证件信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateXlzInfo(Integer id, String xlzImgBig,
			String xlzImgSmall) throws WEBException {
		// TODO Auto-generated method stub
		try {
			ntcDao =(NetTeacherCertificateInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_CERTIFICATE_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherCertificateInfo ntc = ntcDao.get(sess, id);
			if(ntc != null){
				ntc.setXlzImgBig(xlzImgBig);
				ntc.setXlzImgSmall(xlzImgSmall);
				ntcDao.update(sess, ntc);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定网络导师证件信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateZgzInfo(Integer id, String zgzImgBig,
			String zgzImgSmall) throws WEBException {
		// TODO Auto-generated method stub
		try {
			ntcDao =(NetTeacherCertificateInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_CERTIFICATE_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			NetTeacherCertificateInfo ntc = ntcDao.get(sess, id);
			if(ntc != null){
				ntc.setZgzImgBig(zgzImgBig);
				ntc.setZgzImgSmall(zgzImgSmall);
				ntcDao.update(sess, ntc);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("修改指定网络导师证件信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
}
