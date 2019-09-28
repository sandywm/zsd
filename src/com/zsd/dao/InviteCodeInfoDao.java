package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.InviteCodeInfo;

public interface InviteCodeInfoDao {
	/**
	 * 根据主键加载邀请码信息实体
	 * @description
	 * @author zong
	 * @param sess
	 * @param id 邀请码主键值
	 * @return 邀请码信息PO
	 */
	 InviteCodeInfo get(Session sess,int id);
	
	/**
	 * 保存邀请码信息信息实体，新增一条邀请码信息记录
	 * @description
	 * @author zong
	 * @param sess
	 * @param invitecInfo 保存的邀请码信息实例
	 */
	void save(Session sess,InviteCodeInfo invitecInfo);
	
	/**
	 * 删除邀请码信息实体，删除一条邀请码信息记录
	 * @description
	 * @author zong
	 * @param sess
	 * @param invitecInfo 删除的邀请码信息实体
	 */
	void delete(Session sess,InviteCodeInfo invitecInfo);
	
	/**
	 * 根据主键删除邀请码信息实体，删除一条邀请码信息记录
	 * @description
	 * @author zong
	 * @param sess
	 * @param id 需要删除邀请码信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条邀请码信息记录
	 * @description
	 * @author zong
	 * @param sess
	 * @param invitecInfo 需要更新的邀请码信息
	 */
	void update(Session sess,InviteCodeInfo invitecInfo);
	
	/**
	 * 获取所有邀请码列表
	 * @description
	 * @author zong
	 * @param sess
	 * @return
	 */
	List<InviteCodeInfo> findInviteCodeInfo(Session sess);
	/**
	 * 根据邀请码获取列表
	 * @author zong
	 * @param sess
	 * @param inviteCode 邀请码
	 * @param inviteType 邀请类型
	 * @return
	 */
	List<InviteCodeInfo> findIcInfoByOpt(Session sess,String inviteCode,String inviteType);
	/**
	 * 根据邀请编号,邀请码类型获取信息
	 * @author zdf
	 * 2019-9-17 下午04:06:14
	 * @param sess
	 * @param inviteId 邀请编号
	 * @param inviteType 邀请类型
	 * @return
	 */
	List<InviteCodeInfo> findIcInfoByOption(Session sess,Integer inviteId,String inviteType);
}
