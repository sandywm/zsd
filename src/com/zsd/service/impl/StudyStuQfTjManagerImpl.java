package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.ClassInfoDao;
import com.zsd.dao.SchoolDao;
import com.zsd.dao.StudyStuQfTjDao;
import com.zsd.dao.SubjectDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.ClassInfo;
import com.zsd.module.School;
import com.zsd.module.StudyStuQfTjInfo;
import com.zsd.module.Subject;
import com.zsd.module.User;
import com.zsd.service.StudyStuQfTjManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class StudyStuQfTjManagerImpl implements StudyStuQfTjManager{

	SubjectDao subDao = null;
	UserDao uDao = null;
	SchoolDao schDao = null;
	ClassInfoDao cDao = null;
	StudyStuQfTjDao tjDao = null;
	Transaction tran = null;
	@Override
	public Integer addQFTJ(Integer userId, Integer subId,
			Integer oneZdSuccNum, Integer oneZdFailNum, Integer againXxSuccNum,
			Integer againXxFailNum, Integer noRelateNum,
			Integer relateZdFailNum, Integer relateXxSuccNum,
			Integer relateXxFailNum, String rate, String prov, String city,
			String county, Integer schoolType, Integer schoolId,
			String gradeName, Integer classId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			subDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			uDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			schDao = (SchoolDao) DaoFactory.instance(null).getDao(Constants.DAO_SCHOOL_INFO);
			cDao = (ClassInfoDao) DaoFactory.instance(null).getDao(Constants.DAO_CLASS_INFO);
			tjDao = (StudyStuQfTjDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_STU_QFTJ_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			StudyStuQfTjInfo qftj = new StudyStuQfTjInfo(subDao.get(sess, subId), schDao.get(sess, schoolId), uDao.get(sess, userId),
					CurrentTime.getStringDate(), oneZdSuccNum, oneZdFailNum,againXxSuccNum, againXxFailNum,
					noRelateNum, relateZdFailNum,relateXxSuccNum, relateXxFailNum, rate,
					prov, city, county, "",schoolType, gradeName, cDao.get(sess, classId));
			tjDao.save(sess, qftj);
			tran.commit();
			return qftj.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加勤奋统计信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public StudyStuQfTjInfo getEntityById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			tjDao = (StudyStuQfTjDao) DaoFactory.instance(null).getDao(Constants.DAO_STUDY_STU_QFTJ_INFO);
			Session sess = HibernateUtil.currentSession();
			return tjDao.getEntityById(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加勤奋统计信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateTjInfoById(Integer id, Integer oneZdSuccNum,
			Integer oneZdFailNum, Integer againXxSuccNum,
			Integer againXxFailNum, Integer noRelateNum,
			Integer relateZdFailNum, Integer relateXxSuccNum,
			Integer relateXxFailNum, String rate) throws WEBException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public StudyStuQfTjInfo getEntityByOpt(Integer userId, Integer subId,
			String addDate) throws WEBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudyStuQfTjInfo> listInfoByOpt(Integer userId, Integer subId,
			String sDate, String eDate, String prov, String city,
			String county, Integer schoolType, Integer schoolId,
			String gradeName, Integer classId) throws WEBException {
		// TODO Auto-generated method stub
		return null;
	}

}
