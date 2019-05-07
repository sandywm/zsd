package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.EditionDao;
import com.zsd.dao.EducationDao;
import com.zsd.dao.GradeSubjectDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.Education;
import com.zsd.service.EducationManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class EducationManagerImpl implements EducationManager{

	EditionDao ediDao;
	EducationDao eduDao;
	GradeSubjectDao gsDao;
	Transaction tran = null;
	@Override
	public Integer addEdu(Integer gradeId, Integer ediId, Integer eduOrder,
			String eduVolume, String eduImg) throws WEBException {
		// TODO Auto-generated method stub
		try {
			ediDao = (EditionDao) DaoFactory.instance(null).getDao(Constants.DAO_EDITION_INFO);
			eduDao = (EducationDao) DaoFactory.instance(null).getDao(Constants.DAO_EDUCATION_INFO);
			gsDao = (GradeSubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_GRADE_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			Education edu = new Education(ediDao.get(sess, ediId), gsDao.get(sess, gradeId),
					eduOrder, 0, eduVolume,eduImg);
			eduDao.save(sess, edu);
			tran.commit();
			return edu.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加教材信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateEduById(Integer eduId, Integer gradeId, Integer ediId,
			Integer eduOrder, Integer showStatus, String eduVolume,
			String eduImg) throws WEBException {
		// TODO Auto-generated method stub
		try {
			ediDao = (EditionDao) DaoFactory.instance(null).getDao(Constants.DAO_EDITION_INFO);
			eduDao = (EducationDao) DaoFactory.instance(null).getDao(Constants.DAO_EDUCATION_INFO);
			gsDao = (GradeSubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_GRADE_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			Education edu = eduDao.get(sess, eduId);
			if(edu != null){
				edu.setGradeSubject(gsDao.get(sess, gradeId));
				edu.setEdition(ediDao.get(sess, ediId));
				if(eduOrder > 0){
					edu.setEduOrder(eduOrder);
				}
				edu.setDisplayStatus(showStatus);
				edu.setEduVolume(eduVolume);
				if(!eduImg.equals("")){
					edu.setEduImg(eduImg);
				}
				eduDao.update(sess, edu);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改教材信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Education> listSpecInfoById(Integer eduId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eduDao = (EducationDao) DaoFactory.instance(null).getDao(Constants.DAO_EDUCATION_INFO);
			Session sess = HibernateUtil.currentSession();
			return eduDao.findSpecInfoById(sess, eduId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取指定教材编号的详情时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Education> listPageInfoByOpt(Integer ediId, Integer gsId,
			Integer pageNo, Integer pageSize)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			eduDao = (EducationDao) DaoFactory.instance(null).getDao(Constants.DAO_EDUCATION_INFO);
			Session sess = HibernateUtil.currentSession();
			return eduDao.findPageInfoByOpt(sess, ediId, gsId, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("分页获取指定条件教材信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByOpt(Integer ediId, Integer gsId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eduDao = (EducationDao) DaoFactory.instance(null).getDao(Constants.DAO_EDUCATION_INFO);
			Session sess = HibernateUtil.currentSession();
			return eduDao.getCountByOpt(sess, ediId, gsId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取指定条件的教材记录条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Education> listInfoByOpt(Integer ediId, Integer gradeId,
			String eduVolume) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eduDao = (EducationDao) DaoFactory.instance(null).getDao(Constants.DAO_EDUCATION_INFO);
			Session sess = HibernateUtil.currentSession();
			return eduDao.findInfoByOpt(sess, ediId, gradeId, eduVolume);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据出版社编号、年级学科编号、卷册获取教材信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Education> listInfoByOpt(Integer ediId, String gName,
			Integer subId, String eduVolume) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eduDao = (EducationDao) DaoFactory.instance(null).getDao(Constants.DAO_EDUCATION_INFO);
			Session sess = HibernateUtil.currentSession();
			return eduDao.findInfoByOpt(sess, ediId, gName, subId, eduVolume);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据出版社编号、年级名称、科目编号、上/下册获取教材列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Education> listInfoByOpt(Integer ediId, Integer gsId)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			eduDao = (EducationDao) DaoFactory.instance(null).getDao(Constants.DAO_EDUCATION_INFO);
			Session sess = HibernateUtil.currentSession();
			return eduDao.findInfoByOpt(sess, ediId, gsId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据出版社编号、年级学科编号获取教材列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
