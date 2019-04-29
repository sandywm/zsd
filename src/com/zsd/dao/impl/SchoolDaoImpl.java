package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.SchoolDao;
import com.zsd.module.School;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class SchoolDaoImpl implements SchoolDao{

	@Override
	public School get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (School) sess.load(School.class, id);
	}

	@Override
	public void save(Session sess, School sch) {
		// TODO Auto-generated method stub
		sess.save(sch);
	}

	@Override
	public void delete(Session sess, School sch) {
		// TODO Auto-generated method stub
		sess.delete(sch);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, School sch) {
		// TODO Auto-generated method stub
		sess.update(sch);
	}

	@Override
	public List<School> findPageInfoByOpt(Session sess, String schoolName,
			String prov, String city, String county, String town,
			Integer schoolType, Integer showStatus, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from School as sch where 1=1";
		if(!schoolName.equals("")){
			hql += " and sch.schoolName like '"+schoolName+"'";
		}
		if(!prov.equals("")){
			hql += " and sch.prov = '"+prov+"'";
		}
		if(!city.equals("")){
			hql += " and sch.city = '"+city+"'";
		}
		if(!county.equals("")){
			hql += " and sch.county = '"+county+"'";
		}
		if(!town.equals("")){
			hql += " and sch.town = '"+town+"'";
		}
		if(schoolType > 0){
			hql += " and sch.schoolType = "+schoolType;
		}
		if(showStatus >= 0){
			hql += " and sch.showStatus = "+showStatus;
		}
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, String schoolName, String prov,
			String city, String county, String town, Integer schoolType,
			Integer showStatus) {
		// TODO Auto-generated method stub
		String hql = " from School as sch where 1=1";
		if(!schoolName.equals("")){
			hql += " and sch.schoolName like '"+schoolName+"'";
		}
		if(!prov.equals("")){
			hql += " and sch.prov = '"+prov+"'";
		}
		if(!city.equals("")){
			hql += " and sch.city = '"+city+"'";
		}
		if(!county.equals("")){
			hql += " and sch.county = '"+county+"'";
		}
		if(!town.equals("")){
			hql += " and sch.town = '"+town+"'";
		}
		if(schoolType > 0){
			hql += " and sch.schoolType = "+schoolType;
		}
		if(showStatus >= 0){
			hql += " and sch.showStatus = "+showStatus;
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<School> findSpecInfoById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from School as sch where sch.id = "+id;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<School> findInfoByOpt(Session sess, String prov, String city,
			String county, String town, Integer schoolType) {
		// TODO Auto-generated method stub
		String hql = " from School as sch where sch.showStatus = 0";
		if(!prov.equals("")){
			hql += " and sch.prov = '"+prov+"'";
		}
		if(!city.equals("")){
			hql += " and sch.city = '"+city+"'";
		}
		if(!county.equals("")){
			hql += " and sch.county = '"+county+"'";
		}
		if(!town.equals("")){
			hql += " and sch.town = '"+town+"'";
		}
		if(schoolType > 0){
			hql += " and sch.schoolType = "+schoolType;
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public List<School> findInfoBySName(Session sess, String sName) {
		// TODO Auto-generated method stub
		String hql = " from School as sch where sch.schoolName = '"+sName+"'";
		return sess.createQuery(hql).list();
	}

}
