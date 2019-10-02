package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.Email;

public interface EmailDao {

	/**
	 * 根据主键加载邮件实体
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param id 需要加载的邮件的主键值
	 * @return 加载的邮件PO
	 */
	Email getEntity(Session sess,int id);
	
	/**
	 * 保存邮件实体，新增一条邮件记录
	 * @param email 保存的邮件实例
	 */
	void save(Session sess,Email email);
	
	/**
	 * 删除邮件实体，删除一条邮件记录
	 * @param email 删除的邮件实体
	 */
	void delete(Session sess,Email email);
	
	/**
	 * 根据主键删除邮件实体，删除一条邮件记录
	 * @param id 需要删除邮件的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条邮件记录
	 * @param email 需要更新的邮件
	 */
	void update(Session sess,Email email);
	/**
	 * 根据用户编号获取发送的邮件
	 * @param sess
	 * @param userId 用户编号
	 * @return
	 */
	List<Email> findPageInfoByOpt(Session sess, Integer userId,String title,String sDate,String eDate,String emailType,int pageNo,int pageSize);
	/**
	 * 根据用户编号获取发送的邮件总记录数
	 * @param sess
	 * @param userId 用户编号
	 * @return
	 */
	Integer getCountByOpt(Session sess,Integer userId,String title,String sDate,String eDate,String emailType);
	
	/**
	 * 获取用户下的未读邮件条数
	 * @author wm
	 * @date 2019-10-2 上午09:10:25
	 * @param sess
	 * @param userId 用户编号
	 * @return
	 */
	Integer getUnReadCount(Session sess,Integer userId);
}
