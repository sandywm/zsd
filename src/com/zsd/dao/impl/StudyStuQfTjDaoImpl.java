package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.StudyStuQfTjDao;
import com.zsd.module.StudyStuQfTjInfo;

@SuppressWarnings("unchecked")
public class StudyStuQfTjDaoImpl implements StudyStuQfTjDao{

	@Override
	public StudyStuQfTjInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from StudyStuQfTjInfo as qftj where qftj.id = "+id;
		List<StudyStuQfTjInfo> list = sess.createQuery(hql).list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, StudyStuQfTjInfo qftj) {
		// TODO Auto-generated method stub
		sess.save(qftj);
	}

	@Override
	public void update(Session sess, StudyStuQfTjInfo qftj) {
		// TODO Auto-generated method stub
		sess.update(qftj);
	}

	@Override
	public List<StudyStuQfTjInfo> findInfoByOpt(Session sess, Integer userId,
			Integer subId, String sDate, String eDate, String prov,
			String city, String county, String town,Integer schoolType, Integer schoolId,
			String gradeName, Integer classId) {
		// TODO Auto-generated method stub
		String hql = " from StudyStuQfTjInfo as qftj where qftj.studyDate >= '"+sDate+"' and qftj.studyDate <= '"+eDate+"'";
		if(userId > 0){
			hql += " and qftj.user.id = "+userId;
		}
		if(subId > 0){
			hql += " and qftj.subject.id = "+subId;
		}
		if(!prov.equals("")){
			hql += " and qftj.prov = '"+prov+"'";
		}
		if(!city.equals("")){
			hql += " and qftj.city = '"+city+"'";
		}
		if(!county.equals("")){
			hql += " and qftj.county = '"+county+"'";
		}
		if(!town.equals("")){
			hql += " and qftj.town = '"+town+"'";
		}
		if(schoolType > 0){
			hql += " and qftj.schoolType = "+schoolType;
		}
		if(schoolId > 0){
			hql += " and qftj.school.id = "+schoolId;
		}
		if(!gradeName.equals("")){
			hql += " and qftj.gradeName = '"+gradeName+"'";
		}
		if(classId > 0){
			hql += " and qftj.classInfo.id = "+classId;
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public StudyStuQfTjInfo getEntityByOpt(Session sess, Integer userId,
			Integer subId, String addDate) {
		// TODO Auto-generated method stub
		String hql = " from StudyStuQfTjInfo as qftj where qftj.studyDate = '"+addDate+"'";
		hql += " and qftj.user.id = "+userId + " and qftj.subject.id = "+subId;
		List<StudyStuQfTjInfo> list = sess.createQuery(hql).list();
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
