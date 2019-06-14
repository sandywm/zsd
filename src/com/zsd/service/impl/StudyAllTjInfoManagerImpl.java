package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.StudyAllTjInfoDao;
import com.zsd.dao.SubjectDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.StudyAllTjInfo;
import com.zsd.module.Subject;
import com.zsd.service.StudyAllTjInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

/** 
 * @author zong
 * @version 2019-6-10 上午10:30:08
 */
public class StudyAllTjInfoManagerImpl implements StudyAllTjInfoManager {
	StudyAllTjInfoDao sAllTjInfoDao =null;
	Transaction tran = null;
	@Override
	public List<StudyAllTjInfo> findInfoByOpt(Integer subId, String startTime,
			String endTime) throws WEBException {
		try {
			sAllTjInfoDao = (StudyAllTjInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_ALL_TJ_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sAllTjInfoDao.findInfoByOpt(sess, subId, startTime, endTime);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据学科,时间段获取平台知识点信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<StudyAllTjInfo> listInfoByOption(String studyDate, Integer subId)
			throws WEBException {
		try{
			sAllTjInfoDao = (StudyAllTjInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_ALL_TJ_INFO);
			Session sess = HibernateUtil.currentSession();
			return sAllTjInfoDao.findInfoByOpt(sess, subId, studyDate);
		}catch(Exception e){
			throw new WEBException("根据时间、学科查询全平台学习统计信息时出现异常，请重试！");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public boolean updateSATById(Integer id, String questionType,
			boolean liaojieSuccFlag, boolean lijieSuccFlag, boolean yySuccFlag)
			throws WEBException {
		try{
			sAllTjInfoDao = (StudyAllTjInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_ALL_TJ_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyAllTjInfo sat = sAllTjInfoDao.get(sess, id);
			if(questionType.equals("了解")){
				if(liaojieSuccFlag){
					sat.setLiaojieSuccNum(sat.getLiaojieSuccNum() + 1);
				}
				sat.setLiaojieTotalNum(sat.getLiaojieTotalNum() + 1);
			}else if(questionType.equals("理解")){
				if(lijieSuccFlag){
					sat.setLijieSuccNum(sat.getLijieSuccNum() + 1);
				}
				sat.setLijieTotalNum(sat.getLijieTotalNum() + 1);
			}else if(questionType.equals("应用")){
				if(yySuccFlag){
					sat.setYySuccNum(sat.getYySuccNum() + 1);
				}
				sat.setYyTotalNum(sat.getYyTotalNum() + 1);
			}
			sAllTjInfoDao.update(sess, sat);
			tran.commit();
			return true;
		}catch(Exception e){
			tran.rollback();
			throw new WEBException("根据主键修改全平台学习统计信息时出现异常，请重试！");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public Integer addSAT(String studyDate, Integer subId, String questionType,
			Integer result) throws WEBException {
		try{
			sAllTjInfoDao = (StudyAllTjInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_ALL_TJ_INFO);
			SubjectDao subDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			Integer liaojieTotalNum = 0;
		    Integer liaojieSuccNum = 0;
		    Integer lijieTotalNum = 0;
		    Integer lijieSuccNum = 0;
		    Integer yyTotalNum = 0;
		    Integer yySuccNum = 0;
		    if(questionType.equals("了解")){
		    	if(result.equals(1)){
		    		liaojieSuccNum = 1;
		    	}
				liaojieTotalNum = 1;
			}else if(questionType.equals("理解")){
				if(result.equals(1)){
					lijieSuccNum = 1;
				}
				lijieTotalNum = 1;
			}else if(questionType.equals("应用")){
				if(result.equals(1)){
					yySuccNum = 1;
				}
				yyTotalNum = 1;
			}
		    Subject subject = subDao.get(sess, subId);
		    StudyAllTjInfo sat = new StudyAllTjInfo();
		    sat.setSubject(subject);
		    sat.setStudyDate(studyDate);
		    sat.setLiaojieTotalNum(liaojieTotalNum);
		    sat.setLiaojieSuccNum(liaojieSuccNum);
		    sat.setLijieTotalNum(lijieTotalNum);
		    sat.setLijieSuccNum(lijieSuccNum);
		    sat.setYyTotalNum(yyTotalNum);
		    sat.setYySuccNum(yySuccNum);
		    sAllTjInfoDao.save(sess, sat);
			tran.commit();
			return sat.getId();
		}catch(Exception e){
			tran.rollback();
			throw new WEBException("初始化增加全平台学习统计信息时出现异常，请重试！");
		}finally{
			HibernateUtil.closeSession();
		}
	}

}
