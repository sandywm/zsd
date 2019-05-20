package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.LexInfoDao;
import com.zsd.module.LexInfo;
import com.zsd.module.LexLoreRelateInfo;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class LexInfoDaoImpl implements LexInfoDao{

	@Override
	public LexInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from LexInfo as lex where lex.id = "+id;
		List<LexInfo> lexList = sess.createQuery(hql).list();
		if(lexList.size() > 0){
			return lexList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, LexInfo lex) {
		// TODO Auto-generated method stub
		sess.save(lex);
	}

	@Override
	public void update(Session sess, LexInfo lex) {
		// TODO Auto-generated method stub
		sess.update(lex);
	}

	@Override
	public void delete(Session sess, LexInfo lex) {
		// TODO Auto-generated method stub
		sess.delete(lex);
	}

	@Override
	public List<LexInfo> findInfoByOpt(Session sess, String titleName,
			String titlePyCode, String queryOpt) {
		// TODO Auto-generated method stub
		String hql = " from LexInfo as lex where 1=1";
		if(!titleName.equals("") && queryOpt.equals("mc")){
			hql += " and lex.lexTitle like '"+titleName+"'";
		}else if(!titlePyCode.equals("")  && queryOpt.equals("py")){
			hql += " and lex.lexTitlePy like '"+titlePyCode+"'";
		}
		return sess.createQuery(hql).list();
	}

	@Override
	public List<LexInfo> findPageInfoByOpt(Session sess, String titleName,
			String titlePyCode, String queryOpt, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from LexInfo as lex where 1=1";
		if(!titleName.equals("") && queryOpt.equals("mc")){
			hql += " and lex.lexTitle like '"+titleName+"'";
		}else if(!titlePyCode.equals("")  && queryOpt.equals("py")){
			hql += " and lex.lexTitlePy like '"+titlePyCode+"'";
		}
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, String titleName,
			String titlePyCode, String queryOpt) {
		// TODO Auto-generated method stub
		String hql = "select count(lex.id) from LexInfo as lex where 1=1";
		if(!titleName.equals("") && queryOpt.equals("mc")){
			hql += " and lex.lexTitle like '"+titleName+"'";
		}else if(!titlePyCode.equals("")  && queryOpt.equals("py")){
			hql += " and lex.lexTitlePy like '"+titlePyCode+"'";
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

	@Override
	public LexLoreRelateInfo getEntityByLlrId(Session sess, Integer llrId) {
		// TODO Auto-generated method stub
		String hql = " from LexLoreRelateInfo as llr where llr.id = "+llrId;
		List<LexLoreRelateInfo> llrList = sess.createQuery(hql).list();
		if(llrList.size() > 0){
			return llrList.get(0);
		}
		return null;
	}

	@Override
	public void saveLlr(Session sess, LexLoreRelateInfo llr) {
		// TODO Auto-generated method stub
		sess.save(llr);
	}

	@Override
	public void updateLlr(Session sess, LexLoreRelateInfo llr) {
		// TODO Auto-generated method stub
		sess.update(llr);
	}

	@Override
	public void deleteLlr(Session sess, LexLoreRelateInfo llr) {
		// TODO Auto-generated method stub
		sess.delete(llr);
	}


	@Override
	public List<LexLoreRelateInfo> findInfoByOpt(Session sess, Integer lexId,
			Integer loreId) {
		// TODO Auto-generated method stub
		String hql = " from LexLoreRelateInfo as llr where 1=1";
		if(lexId > 0){
			hql += " and llr.lexInfo.id = "+lexId;
		}
		if(loreId > 0){
			hql += " and llr.loreInfo.id = "+loreId;
		}
		return sess.createQuery(hql).list();
	}
}
