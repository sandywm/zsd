package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.BuffetQueInfoDao;
import com.zsd.dao.BuffetSendInfoDao;
import com.zsd.dao.BuffetStudyDetailDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.BuffetQueInfo;
import com.zsd.module.BuffetStudyDetailInfo;
import com.zsd.service.BuffetStudyDetailManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class BuffetStudyDetailManagerImpl implements BuffetStudyDetailManager{

	private static final BuffetQueInfo BuffetSendInfo = null;
	BuffetSendInfoDao bsDao = null;
	BuffetQueInfoDao bqDao = null;
	BuffetStudyDetailDao bsdDao = null;
	Transaction tran = null;
	
	@Override
	public Integer addBatchBSD(Integer bsId, String bqIdStr)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			bsDao = (BuffetSendInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_SEND_INFO);
			bqDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateBuffetStudyDetailById(Integer bsdId, String myAnswer,
			Integer result, String studyTime, String a, String b, String c,
			String d, String e, String f) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bsdDao = (BuffetStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			BuffetStudyDetailInfo bsd = bsdDao.getEntityById(sess, bsdId);
			if(bsd != null){
				bsd.setMyAnswer(myAnswer);
				bsd.setResult(result);
				bsd.setAddTime(CurrentTime.getCurrentTime());
				bsd.setA(a);
				bsd.setB(b);
				bsd.setC(c);
				bsd.setD(d);
				bsd.setE(e);
				bsd.setF(f);
				bsdDao.update(sess, bsd);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new WEBException("根据巴菲特编号插入自己做题时的情况时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateStatusById(Integer bsdId, Integer traceCompleteFlag,
			Integer currCompleteFlag) throws WEBException {
		// TODO Auto-generated method stub
		try {
			bsdDao = (BuffetStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			BuffetStudyDetailInfo bsd = bsdDao.getEntityById(sess, bsdId);
			if(bsd != null){
				if(!traceCompleteFlag.equals(-1)){
					bsd.setTraceComStatus(traceCompleteFlag);
				}
				if(!currCompleteFlag.equals(-1)){
					bsd.setCurrComStatus(currCompleteFlag);
				}
				bsdDao.update(sess, bsd);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键修改巴菲特学习记录的溯源完成标记（-1不修改）、解析完成标记（-1不修改）时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetStudyDetailInfo> listInfoByBsId(Integer bsId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			bsdDao = (BuffetStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return bsdDao.findInfoByBsdId(sess, bsId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据自助餐发送编号获取自助餐学习题库列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public BuffetStudyDetailInfo getEntityById(Integer bsdId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			bsdDao = (BuffetStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return bsdDao.getEntityById(sess, bsdId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据主键获取自助餐学习题库详情时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetStudyDetailInfo> listInfoByStuId(Integer stuId,
			String subName, Integer succFlag) throws WEBException {
		try {
			bsdDao = (BuffetStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return bsdDao.findInfoByStuId(sess, stuId, subName, succFlag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据学生编号,学科名称,完成状态获取自助餐学习题库列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer addBuffetStudyDetil(Integer buffetSendId, Integer buffetId,
			String realAnswer, String myAnswer, Integer result, String addTime,
			String a, String b, String c, String d, String e, String f)
			throws WEBException {
		try {
			bsdDao = (BuffetStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_STUDY_DETAIL_INFO);
			BuffetSendInfoDao bsDao = (BuffetSendInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_SEND_INFO);
			BuffetQueInfoDao bqDao = (BuffetQueInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_QUE_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			BuffetStudyDetailInfo bsd = new BuffetStudyDetailInfo(bqDao.getEntityById(sess, buffetId), bsDao.get(sess, buffetSendId), realAnswer, myAnswer, result, addTime, a, b, c, d, e, f, 0, 0, 0);
			bsdDao.save(sess, bsd);
			tran.commit();
			return bsd.getId();
		} catch (Exception e1) {
			e1.printStackTrace();
			throw new WEBException("添加自助餐详情信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<BuffetStudyDetailInfo> listBsdInfoByBsdId(Integer bsId)
			throws WEBException {
		try {
			bsdDao = (BuffetStudyDetailDao) DaoFactory.instance(null).getDao(Constants.DAO_BUFFET_STUDY_DETAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return bsdDao.findBsdInfoByBsdId(sess, bsId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据自助餐发送编号获取自助餐完成学习详情信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
