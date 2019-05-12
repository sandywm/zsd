package com.zsd.service;

import java.sql.Timestamp;
import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.InviteCodeInfo;

/** 
 * @author zong
 * @version 2019-5-6 上午10:00:01
 */
public interface InviteCodeInfoManager {
	/**
	 * 根据邀请码获取邀请码信息
	 * @param sess
	 * @param inviteCode 邀请码
	 * @return
	 * @throws WEBException
	 */
	List<InviteCodeInfo> listIcInfoByicCode(String inviteCode)throws WEBException;
	/**
	 * 添加邀请码信息
	 * @param inviteId  邀请编号
	 * @param inviteType 邀请码类型
	 * @param inviteCode 邀请码
	 * @param createDate 创建时间
	 * @return
	 * @throws WEBException
	 */
	Integer addInviteCodeInfo( Integer inviteId,String inviteType,String inviteCode, Timestamp createDate) throws WEBException;
}
