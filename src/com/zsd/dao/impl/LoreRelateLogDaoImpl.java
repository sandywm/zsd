package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.LoreRelateLogDao;
import com.zsd.module.LoreRelateLogInfo;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class LoreRelateLogDaoImpl implements LoreRelateLogDao{

	@Override
	public LoreRelateLogInfo get(Session sess, int id) {
		// TODO Auto-generated method stub
		String hql = " from LoreRelateLogInfo as lrl where lrl.id = "+id;
		List<LoreRelateLogInfo> lrlList = sess.createQuery(hql).list();
		if(lrlList.size() > 0){
			return lrlList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, LoreRelateLogInfo lrl) {
		// TODO Auto-generated method stub
		sess.save(lrl);
	}

	@Override
	public void delete(Session sess, LoreRelateLogInfo lrl) {
		// TODO Auto-generated method stub
		sess.delete(lrl);
	}

	@Override
	public void delete(Session sess, int id) {
		// TODO Auto-generated method stub
		sess.delete(this.get(sess, id));
	}

	@Override
	public List<LoreRelateLogInfo> findPageInfoByOpt(Session sess,
			String lorePyCode, String loreName, Integer ediId, Integer pageNo,
			Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from LoreRelateLogInfo as lrl where 1 = 1";
		if(ediId > 0){
			hql += " and lrl.loreInfo.chapter.education.edition.id = "+ediId;
		}
		if(!lorePyCode.equals("")){
			hql += " and lrl.loreInfo.lorePyCode like '%"+lorePyCode+"%'";
		}
		if(!loreName.equals("")){
			hql += " and lrl.loreInfo.loreName like '%"+loreName+"%'";
		}
		int offset = (pageNo - 1) * pageSize;
		if (offset < 0) {
			offset = 0;
		}
		return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
	}

	@Override
	public Integer getCountByOpt(Session sess, String lorePyCode,
			String loreName, Integer ediId) {
		// TODO Auto-generated method stub
		String hql = "select count(lrl.id) from LoreRelateLogInfo as lrl where 1 = 1";
		if(ediId > 0){
			hql += " and lrl.loreInfo.chapter.education.edition.id = "+ediId;
		}
		if(!lorePyCode.equals("")){
			hql += " and lrl.loreInfo.lorePyCode like '%"+lorePyCode+"%'";
		}
		if(!loreName.equals("")){
			hql += " and lrl.loreInfo.loreName like '%"+loreName+"%'";
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

}
