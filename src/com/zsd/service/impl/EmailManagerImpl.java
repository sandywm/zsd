package com.zsd.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.zsd.dao.EmailDao;
import com.zsd.dao.UserDao;
import com.zsd.exception.WEBException;
import com.zsd.factory.DaoFactory;
import com.zsd.module.Email;
import com.zsd.service.EmailManager;
import com.zsd.tools.CurrentTime;
import com.zsd.tools.HibernateUtil;
import com.zsd.util.Constants;

public class EmailManagerImpl implements EmailManager{

	UserDao sendUserDao = null;
	UserDao toUserDao = null;
	EmailDao eDao = null;
	Transaction tran = null;
	@Override
	public Integer addEmail(Integer sendUserId, String title, String content,
			String emailType, Integer toUserId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			sendUserDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			toUserDao = (UserDao) DaoFactory.instance(null).getDao(Constants.DAO_USER_INFO);
			eDao = (EmailDao) DaoFactory.instance(null).getDao(Constants.DAO_EMAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			Email email = new Email(sendUserDao.getEntityById(sess, sendUserId).get(0), toUserDao.getEntityById(sess, toUserId).get(0), title,
					content, CurrentTime.getCurrentTime(), emailType,0);
			eDao.save(sess, email);
			tran.commit();
			return email.getId();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("增加邮件时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void delBatchInfoByIdStr(String emailIdStr,Integer userId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eDao = (EmailDao) DaoFactory.instance(null).getDao(Constants.DAO_EMAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			if(!emailIdStr.equals("")){
				String[] emailIdArr = emailIdStr.split(",");
				for(Integer i = 0 ; i < emailIdArr.length ; i++){
					Integer emailId = Integer.parseInt(emailIdArr[i]);
					Email email = eDao.getEntity(sess, emailId);
					if(email != null){
						if(email.getUserByToUserId().equals(userId)){
							eDao.delete(sess, emailId);
							if(i % 10 == 0){//批插入的对象立即写入数据库并释放内存
								sess.flush();
								sess.clear();
								tran.commit();
								tran = sess.beginTransaction();
							}
						}
					}
				}
				tran.commit();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("批量删除邮件时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Email> listPageInfoByOpt(Integer userId, String title,
			String sDate, String eDate, String emailType, int pageNo,
			int pageSize) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eDao = (EmailDao) DaoFactory.instance(null).getDao(Constants.DAO_EMAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return eDao.findPageInfoByOpt(sess, userId, title, sDate, eDate, emailType, pageNo, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件分页获取邮件列表时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getCountByOpt(Integer userId, String title, String sDate,
			String eDate, String emailType) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eDao = (EmailDao) DaoFactory.instance(null).getDao(Constants.DAO_EMAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return eDao.getCountByOpt(sess, userId, title, sDate, eDate, emailType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("根据条件获取邮件记录时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public void updateBatchInfoByIdStr(String emailIdStr,Integer userId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eDao = (EmailDao) DaoFactory.instance(null).getDao(Constants.DAO_EMAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			tran = sess.beginTransaction();
			if(!emailIdStr.equals("")){
				String[] emailIdArr = emailIdStr.split(",");
				for(Integer i = 0 ; i < emailIdArr.length ; i++){
					Integer emailId = Integer.parseInt(emailIdArr[i]);
					Email email = eDao.getEntity(sess, emailId);
					if(email != null){
						if(email.getUserByToUserId().equals(userId)){
							email.setReadStatus(1);
							eDao.update(sess, email);
							if(i % 10 == 0){//批插入的对象立即写入数据库并释放内存
								sess.flush();
								sess.clear();
								tran.commit();
								tran = sess.beginTransaction();
							}
						}
					}
				}
				tran.commit();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException(" 批量修改消息已读标识时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Integer getUnReadCount(Integer userId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eDao = (EmailDao) DaoFactory.instance(null).getDao(Constants.DAO_EMAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return eDao.getUnReadCount(sess, userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取用户下的未读邮件条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public Email getEntityById(Integer emailId) throws WEBException {
		// TODO Auto-generated method stub
		try {
			eDao = (EmailDao) DaoFactory.instance(null).getDao(Constants.DAO_EMAIL_INFO);
			Session sess = HibernateUtil.currentSession();
			return eDao.getEntity(sess, emailId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WEBException("获取用户下的未读邮件条数时出现异常!");
		} finally{
			HibernateUtil.closeSession();
		}
	}

}
