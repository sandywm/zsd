package com.zsd.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.TeaQueDao;
import com.zsd.module.TeaQueInfo;
import com.zsd.tools.CommonTools;

@SuppressWarnings("unchecked")
public class TeaQueDaoImpl implements TeaQueDao{

	@Override
	public TeaQueInfo getEntityById(Session sess, Integer id) {
		// TODO Auto-generated method stub
		String hql = " from TeaQueInfo as tq where tq.id = "+id;
		List<TeaQueInfo> tqList = sess.createQuery(hql).list();
		if(tqList.size() > 0){
			return tqList.get(0);
		}
		return null;
	}

	@Override
	public void save(Session sess, TeaQueInfo tq) {
		// TODO Auto-generated method stub
		sess.save(tq);
	}

	@Override
	public void update(Session sess, TeaQueInfo tq) {
		// TODO Auto-generated method stub
		sess.update(tq);
	}

	@Override
	public List<TeaQueInfo> findInfoByOpt(Session sess, Integer loreId,
			Integer teaId, boolean pageFlag, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		String hql = " from TeaQueInfo as tq where 1 = 1";
		if(loreId > 0){
			hql += " and tq.loreInfo.id = "+loreId;
		}
		if(teaId > 0){
			hql += " and tq.user.id = "+teaId;
		}
		if(pageFlag){
			int offset = (pageNo - 1) * pageSize;
			if (offset < 0) {
				offset = 0;
			}
			return sess.createQuery(hql).setFirstResult(offset).setMaxResults(pageSize).list();
		}else{
			return sess.createQuery(hql).list();
		}
	}

	@Override
	public Integer getCountByOpt(Session sess, Integer loreId, Integer teaId) {
		// TODO Auto-generated method stub
		String hql = "select count(tq.id) from TeaQueInfo as tq where 1 = 1";
		if(loreId > 0){
			hql += " and tq.loreInfo.id = "+loreId;
		}
		if(teaId > 0){
			hql += " and tq.user.id = "+teaId;
		}
		Object countObj = sess.createQuery(hql).uniqueResult();
		return CommonTools.longToInt(countObj);
	}

}
