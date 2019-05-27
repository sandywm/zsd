package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.EducationDao;
import com.zsd.dao.StuSubjectEduDao;
import com.zsd.dao.SubjectDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.StuSubjectEduInfo;
import com.zsd.service.StuSubjectEduManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class StuSubjectEduManagerImpl implements StuSubjectEduManager{

	UserDao uDao = null;
	SubjectDao sDao = null;
	EducationDao eDao = null;
	StuSubjectEduDao sseDao = null;
	Transaction tran = null;
	@Override
	public Integer addSSE(Integer stuId, Integer subId, Integer eduId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			sDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			eDao = (EducationDao) DaoFactory.instance(null).getDao(Constants.DAO_EDUCATION_INFO);
			sseDao = (StuSubjectEduDao) DaoFactory.instance(null).getDao(Constants.DAO_STU_SUB_EDU_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StuSubjectEduInfo sse = new StuSubjectEduInfo(eDao.get(sess, eduId),sDao.get(sess, subId),uDao.get(sess, stuId),CurrentTime.getCurrentTime());
			sseDao.add(sess, sse);
			tran.commit();
			return sse.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加学生学科教材信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateSSEById(Integer sseId, Integer eduId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			eDao = (EducationDao) DaoFactory.instance(null).getDao(Constants.DAO_EDUCATION_INFO);
			sseDao = (StuSubjectEduDao) DaoFactory.instance(null).getDao(Constants.DAO_STU_SUB_EDU_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StuSubjectEduInfo sse = sseDao.getEntityById(sess, sseId);
			if(sse != null){
				sse.setEducation(eDao.get(sess, eduId));
				sse.setAddDate(CurrentTime.getCurrentTime());
				sseDao.update(sess, sse);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改指定的学生学科教材信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<StuSubjectEduInfo> listInfoByOpt(Integer stuId, Integer subId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			sseDao = (StuSubjectEduDao) DaoFactory.instance(null).getDao(Constants.DAO_STU_SUB_EDU_INFO);
			Session sess = HibernateUtil.currentSession();
			return sseDao.findInfoByOpt(sess, stuId, subId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据学生编号、学科编号获取学生学科教材信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}
}
