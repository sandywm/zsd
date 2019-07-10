package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;

import com.zsd.dao.SubjectDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.Subject;
import com.zsd.service.SubjectManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class SubjectManagerImpl implements SubjectManager{

	@Override
	public List<Subject> listInfoByDisplayStatus(Integer displayStatus)
			throws WEBException {
		// TODO Auto-generated method stub
		 SubjectDao sDao = null;
		 try {
			sDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			return sDao.findInfoByDisplayStatus(sess, displayStatus);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据显示状态获取科目列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Subject> listInfoBySubName(String subName) throws WEBException {
		 SubjectDao sDao = null;
		 try {
			sDao = (SubjectDao) DaoFactory.instance(null).getDao(Constants.DAO_SUBJECT_INFO);
			Session sess = HibernateUtil.currentSession();
			return sDao.findInfoBySubName(sess, subName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据学科名称获取学科信息时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
