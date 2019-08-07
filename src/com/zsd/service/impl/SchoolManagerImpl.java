package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.SchoolDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.School;
import com.zsd.service.SchoolManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class SchoolManagerImpl implements SchoolManager{

	SchoolDao sDao = null;
	Transaction tran = null;
	@Override
	public Integer addSchool(String schoolName, String prov, String city,
			String county, String town, Integer schoolType, Integer yearSystem)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			sDao = (SchoolDao) DaoFactory.instance(null).getDao(Constants.DAO_SCHOOL_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			School sch = new School(schoolName, prov, city, county,
					town, schoolType, yearSystem,0);
			sDao.save(sess, sch);
			tran.commit();
			return sch.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加学校时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean updateSchoolInfoById(Integer id, String schoolName, String prov, String city,
			String county, String town, Integer schoolType, Integer yearSystem,
			Integer showStatus) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sDao = (SchoolDao) DaoFactory.instance(null).getDao(Constants.DAO_SCHOOL_INFO);
			Session sess  = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			School sch = sDao.get(sess, id);
			if(sch != null){
				sch.setSchoolName(schoolName);
				sch.setProv(prov);
				sch.setCity(city);
				sch.setCounty(county);
				sch.setTown(town);
				sch.setSchoolType(schoolType);
				sch.setYearSystem(yearSystem);
				sch.setShowStatus(showStatus);
				sDao.update(sess, sch);
				tran.commit();
				return true;
			}
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("修改学校信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public boolean delSchoolById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<School> listPageInfoByOpt(String schoolName, String prov,
			String city, String county, String town, Integer schoolType,
			Integer showStatus, Integer pageNo, Integer pageSize)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			sDao = (SchoolDao) DaoFactory.instance(null).getDao(Constants.DAO_SCHOOL_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sDao.findPageInfoByOpt(sess, schoolName, prov, city, county, town, schoolType, showStatus, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件分页获取学校信息列表信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByOpt(String schoolName, String prov, String city,
			String county, String town, Integer schoolType, Integer showStatus)
			throws WEBException {
		// TODO Auto-generated method stub
		try {
			sDao = (SchoolDao) DaoFactory.instance(null).getDao(Constants.DAO_SCHOOL_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sDao.getCountByOpt(sess, schoolName, prov, city, county, town, schoolType, showStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("据条件获取学校记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<School> listInfoById(Integer id) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sDao = (SchoolDao) DaoFactory.instance(null).getDao(Constants.DAO_SCHOOL_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sDao.findSpecInfoById(sess, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("获取指定学校详细信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<School> listInfoByOpt(String prov, String city, String county,
			String town, Integer schoolType,Integer yearSystem) throws WEBException {
		try {
			sDao = (SchoolDao) DaoFactory.instance(null).getDao(Constants.DAO_SCHOOL_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sDao.findInfoByOpt(sess,prov,city,county,town,schoolType,yearSystem);
		} catch (Exception e) {
			e.printStackTrace();
			throw new WEBException("根据条件获取学校列表信息列表时出现异常!(下拉列表使用)");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<School> listInfoBySName(String sName) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sDao = (SchoolDao) DaoFactory.instance(null).getDao(Constants.DAO_SCHOOL_INFO);
			Session sess  = HibernateUtil.currentSession();
			return sDao.findInfoBySName(sess, sName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据学校名称获取学校信息列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
