package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.LoreQuestionDao;
import com.zsd.module.LoreQuestion;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class LoreQuestionDaoImpl implements LoreQuestionDao{

	@Override
	public LoreQuestion get(Session sess, int id) {
		// TODO Auto-generated method stub
		return (LoreQuestion) sess.load(LoreQuestion.class, id);
	}

	@Override
	public void save(Session sess, LoreQuestion loreQuestion) {
		// TODO Auto-generated method stub
		sess.save(loreQuestion);
	}

	@Override
	public void delete(Session sess, LoreQuestion loreQuestion) {
		// TODO Auto-generated method stub
		sess.delete(loreQuestion);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public void update(Session sess, LoreQuestion loreQuestion) {
		// TODO Auto-generated method stub
		sess.update(loreQuestion);
	}

	@Override
	public LoreQuestion getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from LoreQuestion as lq where lq.id = "+id;
		List<LoreQuestion> lqList = sess.createQuery(hql).list();
		if(lqList.size() > 0){
			return lqList.get(0);
		}
		return null;
	}

	@Override
	public List<LoreQuestion> findPageInfoByLoreId(Session sess,
			Integer loreId, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from LoreQuestion as lq where lq.loreInfo.id = "+loreId + " order by lq.queOrder asc";
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByLoreId(Session sess, Integer loreId) {
		// TODO Auto-generated method stub
		String hql = "select count(lq.id) from LoreQuestion as lq where lq.loreInfo.id = "+loreId;
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public List<LoreQuestion> findInfoByOpt(Session sess, Integer loreId,
			String loreType, Integer inUse) {
		// TODO Auto-generated method stub
		String hql = " from LoreQuestion as lq where lq.loreInfo.id = "+loreId;
		if(!loreType.equals("")){
			hql += " and lq.loreTypeName = '"+loreType+"'";
		}
		if(!inUse.equals(-1)){
			hql += " and lq.inUse = " + inUse;
		}
		hql += " order by lq.queOrder asc";
		return sess.createQuery(hql).list();
	}

	@Override
	public List<LoreQuestion> findInfoByLexId(Session sess, Integer lexId) {
		// TODO Auto-generated method stub
		String hql = " from LoreQuestion as lq where lq.lexId = "+lexId;
		return sess.createQuery(hql).list();
	}

	@Override
	public List<LoreQuestion> findInfoByTipsId(Session sess, Integer tipsId) {
		// TODO Auto-generated method stub
		String hql = " from LoreQuestion as lq where lq.queTips = "+tipsId;
		return sess.createQuery(hql).list();
	}

	@Override
	public LoreQuestion getMaxNumInfoByOpt(Session sess, Integer loreId,
			String loreType, Integer inUse) {
		// TODO Auto-generated method stub
		String hql = " from LoreQuestion as lq where lq.loreInfo.id = "+loreId;
		if(!loreType.equals("")){
			hql += " and lq.loreTypeName = '"+loreType+"'";
		}
		if(!inUse.equals(-1)){
			hql += " and lq.inUse = " + inUse;
		}
		hql += " order by lq.queNum desc";
		List<LoreQuestion> lqList = sess.createQuery(hql).list();
		if(lqList.size() > 0){
			return lqList.get(0);
		}
		return null;
	}

	@Override
	public List<LoreQuestion> findInfoByOpt(Session sess, Integer loreId,
			String loreType, Integer inUse, Integer queClassTeaId) {
		// TODO Auto-generated method stub
		return null;
	}

}
