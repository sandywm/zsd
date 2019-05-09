package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.LoreQuestionSubDao;
import com.zsd.module.LoreQuestionSubInfo;

@SuppressWarnings("unchecked")
public class LoreQuestionSubDaoImpl implements LoreQuestionSubDao{

	@Override
	public LoreQuestionSubInfo get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (LoreQuestionSubInfo) sess.load(LoreQuestionSubInfo.class, id);
	}

	@Override
	public void save(Session sess, LoreQuestionSubInfo lqs) {
		// TODO Auto-generated method stub
		sess.save(lqs);
	}

	@Override
	public void delete(Session sess, LoreQuestionSubInfo lqs) {
		// TODO Auto-generated method stub
		sess.delete(lqs);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, LoreQuestionSubInfo lqs) {
		// TODO Auto-generated method stub
		sess.update(lqs);
	}

	@Override
	public List<LoreQuestionSubInfo> findInfoByOpt(Session sess, Integer lqId) {
		// TODO Auto-generated method stub
		String hql = " from LoreQuestionSubInfo as lqs where lqs.loreQuestion.id = "+lqId;
		return sess.createQuery(hql).list();
	}

	@Override
	public LoreQuestionSubInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from LoreQuestionSubInfo as lqs where lqs.id = "+id;
		List<LoreQuestionSubInfo> lqsList = sess.createQuery(hql).list();
		if(lqsList.size() > 0){
			return lqsList.get(0);
		}
		return null;
	}

}
