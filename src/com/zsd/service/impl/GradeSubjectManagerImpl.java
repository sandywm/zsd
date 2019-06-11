package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.GradeSubjectDao;
import com.zsd.dao.SubjectDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.GradeSubject;
import com.zsd.service.GradeSubjectManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class GradeSubjectManagerImpl implements GradeSubjectManager{

	SubjectDao sDao = null;
	GradeSubjectDao gsDao = null;
	Transaction tran = null;
	@Override
	public Integer addGSub(String gName, Integer subId, Integer schoolType)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			sDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			gsDao = (GradeSubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_GRADE_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			GradeSubject gs = new GradeSubject(sDao.get(sess, subId), gName, schoolType,0);
			gsDao.save(sess, gs);
			tran.commit();
			return gs.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加年级科目时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateGSub(Integer id, String gName, Integer subId,
			Integer schoolType, Integer showStatus) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			gsDao = (GradeSubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_GRADE_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			GradeSubject gs = gsDao.get(sess, id);
			if(gs != null){
				gs.setGradeName(gName);
				gs.setSubject(sDao.get(sess, subId));
				gs.setSchoolType(schoolType);
				gs.setDisplayStatus(showStatus);
				gsDao.update(sess, gs);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改指定编号的年级科目信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<GradeSubject> listPageInfoByOpt(String gradeName,
			Integer subId, Integer shoolType, Integer showStatus,
			Integer pageNo, Integer pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			gsDao = (GradeSubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_GRADE_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			return gsDao.findPageInfoByOpt(sess, gradeName, subId, shoolType, showStatus, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件分页获取年级学科列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByOpt(String gradeName, Integer subId,
			Integer shoolType, Integer showStatus) throws WEBException {
		// TODO Auto-generated method stub
		try {
			gsDao = (GradeSubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_GRADE_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			return gsDao.getCountByOpt(sess, gradeName, subId, shoolType, showStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件获取年级学科记录条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<GradeSubject> listSpecInfoById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			gsDao = (GradeSubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_GRADE_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			return gsDao.findSpecInfoById(sess, id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取指定编号的年级学科记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<GradeSubject> listSpecInfoBySubId(Integer subId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			gsDao = (GradeSubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_GRADE_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			return gsDao.findSpecInfoBySubId(sess, subId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取指定学科下的年级学科记录列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<GradeSubject> listSpecInfoByOpt(String gradeName,
			Integer subId, Integer shoolType) throws WEBException {
		// TODO Auto-generated method stub
		try {
			gsDao = (GradeSubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_GRADE_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			return gsDao.findSpecInfoByOpt(sess, gradeName, subId, shoolType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据年级、学科编号、学段获取年级学科列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<GradeSubject> listSpecInfoByGname(String gradeName)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			gsDao = (GradeSubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_GRADE_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			return gsDao.findSpecInfoByGname(sess, gradeName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据年级名称获取年级学科列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<GradeSubject> findSpecInfoByschType(Integer schType)
			throws WEBException {
		try {
			gsDao = (GradeSubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_GRADE_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			return gsDao.findSpecInfoByschType(sess, schType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据学段获取年级学科列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
