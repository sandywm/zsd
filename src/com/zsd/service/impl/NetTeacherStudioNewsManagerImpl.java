package com.zsd.service.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.NetTeacherStudioDao;
import com.zsd.dao.NetTeacherStudioNewsDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.NetTeacherStudioInfo;
import com.zsd.module.NetTeacherStudioNewsInfo;
import com.zsd.service.NetTeacherStudioNewsManager;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class NetTeacherStudioNewsManagerImpl implements
		NetTeacherStudioNewsManager {
	NetTeacherStudioNewsDao ntStudioNewsDao = null;
	NetTeacherStudioDao ntStudioDao=null;
	Transaction tran = null;

	@Override
	public Integer addNTStudioNews(Integer ntStudioId, String newsTitle,
			String newsContent, String sendTime) throws WEBException {
			try {
				ntStudioNewsDao = (NetTeacherStudioNewsDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDIO_NEWS);
				ntStudioDao = (NetTeacherStudioDao) DaoFactory.instance(null).getDao(Constants.DAO_NET_TEACHER_STUDIO);
				Session sess  = HibernateUtil.currentSession();
				tran = sess.beginTransaction();
				NetTeacherStudioInfo netTeacherStudioInfo = ntStudioDao.get(sess, ntStudioId);
				NetTeacherStudioNewsInfo ntStudioNews = new NetTeacherStudioNewsInfo(netTeacherStudioInfo, newsTitle, newsContent, sendTime);
				ntStudioNewsDao.save(sess, ntStudioNews);
				tran.commit();
				return ntStudioNews.getId();
			} catch (Exception e) {
				e.printStackTrace();
				throw new WEBException("增加网络导师工作室新闻时出现异常!");
			} finally{
				HibernateUtil.closeSession();
			}
	}

}
