/**
 * 
 */
package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.ChapterDao;
import com.zsd.module.Chapter;

@SuppressWarnings("unchecked")
public class ChapterDaoImpl implements ChapterDao{

	@Override
	public Chapter get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (Chapter) sess.load(Chapter.class, id);
	}

	@Override
	public void save(Session sess, Chapter cpt) {
		// TODO Auto-generated method stub
		sess.save(cpt);
	}

	@Override
	public void delete(Session sess, Chapter cpt) {
		// TODO Auto-generated method stub
		sess.delete(cpt);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, Chapter cpt) {
		// TODO Auto-generated method stub
		sess.update(cpt);
	}

	@Override
	public List<Chapter> findInfoByOpt(Session sess, Integer subId,String gradeName,Integer ediId,String eduVolume) {
		// TODO Auto-generated method stub
		String hql = " from Chapter as c where c.education.gradeSubject.subject.id = "+subId;
		hql += " and c.education.gradeSubject.gradeName = '"+gradeName+"'";
		hql += " and c.education.edition.id = "+ediId + " and c.education.eduVolume = '"+eduVolume+"'";
		hql += " order by c.chapterOrder asc";
		return sess.createQuery(hql).list();
	}

	@Override
	public List<Chapter> findInfoByEduId(Session sess, Integer eduId) {
		// TODO Auto-generated method stub
		String hql = " from Chapter as c where c.education.id = "+eduId + " order by c.chapterOrder asc";
		return sess.createQuery(hql).list();
	}

	@Override
	public List<Chapter> findInfoByOpt(Session sess, Integer eduId,
			String cptName) {
		// TODO Auto-generated method stub
		String hql = " from Chapter as c where c.education.id = "+eduId + " and c.chapterName = '"+cptName+"'"; 
		return sess.createQuery(hql).list();
	}

}
