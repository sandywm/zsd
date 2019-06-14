package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.StudyStuTjInfoDao;
import com.zsd.dao.SubjectDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.StudyStuTjInfo;
import com.zsd.module.Subject;
import com.zsd.module.User;
import com.zsd.service.StudyStuTjInfoManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

/** 
 * @author zong
 * @version 2019-6-10 上午10:29:46
 */
public class StudyStuTjInfoManagerImpl implements StudyStuTjInfoManager {
	StudyStuTjInfoDao stuTjInfoDao = null;
	Transaction tran = null;
	@Override
	public List<StudyStuTjInfo> listInfoByOpt(Integer userId, Integer subId,
			String startTime, String endTime) throws WEBException {
		try {
			stuTjInfoDao = (StudyStuTjInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_STU_TJ_INFO);
			Session sess  = HibernateUtil.currentSession();
			return stuTjInfoDao.findInfoByOpt(sess, userId, subId, startTime, endTime);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据用户编号,学科,时间段获取学生学习统计信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public List<StudyStuTjInfo> listInfoByOption(Integer userId, Integer subId,
			String studyDate) throws WEBException {
		try{
			stuTjInfoDao = (StudyStuTjInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_STU_TJ_INFO);
			Session sess = HibernateUtil.currentSession();
			return stuTjInfoDao.findInfoByOption(sess, userId, subId, studyDate);
		}catch(Exception e){
			throw new WEBException("根据学习时间、学生编号、学科编号获取学习统计信息时出现异常，请重试！");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public boolean updateSSTById(Integer id, String questionType,
			boolean liaojieSuccFlag, boolean lijieSuccFlag, boolean yySuccFlag)
			throws WEBException {
		try{
			stuTjInfoDao = (StudyStuTjInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_STU_TJ_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyStuTjInfo sst = stuTjInfoDao.get(sess, id);
			if(questionType.equals("了解")){
				if(liaojieSuccFlag){
					sst.setLiaojieSuccNum(sst.getLiaojieSuccNum() + 1);
				}
				sst.setLiaojieTotalNum(sst.getLiaojieTotalNum() + 1);
			}else if(questionType.equals("理解")){
				if(lijieSuccFlag){
					sst.setLijieSuccNum(sst.getLijieSuccNum() + 1);
				}
				sst.setLijieTotalNum(sst.getLijieTotalNum() + 1);
			}else if(questionType.equals("应用")){
				if(yySuccFlag){
					sst.setYySuccNum(sst.getYySuccNum() + 1);
				}
				sst.setYyTotalNum(sst.getYyTotalNum() + 1);
			}
			stuTjInfoDao.update(sess, sst);
			tran.commit();
			return true;
		}catch(Exception e){
			tran.rollback();
			throw new WEBException("修改学生学习统计信息时出现异常，请重试！");
		}finally{
			HibernateUtil.closeSession();
		}
	}
	@Override
	public Integer addSST(String studyDate, Integer userId, Integer subId,
			String questionType, Integer result) throws WEBException {
		try{
			stuTjInfoDao = (StudyStuTjInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_STU_TJ_INFO);
			UserDao udao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
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
			StudyStuTjInfo sst = new StudyStuTjInfo();
			sst.setStudyDate(studyDate);
			User user=udao.get(sess, userId);
			sst.setUser(user);
			Subject subject =subDao.get(sess, subId);
			sst.setSubject(subject );
			sst.setLiaojieSuccNum(liaojieSuccNum);
			sst.setLiaojieTotalNum(liaojieTotalNum);
			sst.setLijieTotalNum(lijieTotalNum);
			sst.setLijieSuccNum(lijieSuccNum);
			sst.setYyTotalNum(yyTotalNum);
			sst.setYySuccNum(yySuccNum);
			stuTjInfoDao.save(sess, sst);
			tran.commit();
			return sst.getId();
		}catch(Exception e){
			tran.rollback();
			throw new WEBException("初始化增加学生学习统计信息时出现异常，请重试！");
		}finally{
			HibernateUtil.closeSession();
		}
	}

}
