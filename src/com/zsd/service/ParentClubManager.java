package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.ParentClubInfo;

public interface ParentClubManager {
	/**
	 * 添加家长群信息
	 * @author zdf
	 * 2019-9-27 上午11:49:19
	 * @param userId 用户编号(家长)
	 * @param clubName 群名称
	 * @param clubCode 群邀请码
	 * @param maxNum   最大群成员
	 * @param clubProfile 群简介
	 * @return
	 * @throws WEBException
	 */
	Integer addParentClub( Integer userId, String clubName,String clubCode, Integer maxNum,String clubProfile)throws WEBException;
	/**
	 * 更新家长群信息
	 * @author zdf
	 * 2019-9-27 下午04:45:37
	 * @param id 家长群主键
	 * @param clubName 家长群名
	 * @param clubProfile 家长群简介
	 * @return
	 * @throws WEBException
	 */
	boolean  updateParentClub (Integer id , String clubName, String clubProfile)throws WEBException;
	/**
	 * 根据用户编号获取家长群信息
	 * @author zdf
	 * 2019-9-27 下午04:46:28
	 * @param userId 用户编号
	 * @return
	 * @throws WEBException
	 */
	List<ParentClubInfo> listParentClubByuId(Integer userId)throws WEBException;
	/**
	 * 根据群邀请码获取家长群信息
	 * @author zdf
	 * 2019-9-27 下午04:47:04
	 * @param clubCode 群邀请码
	 * @return
	 * @throws WEBException
	 */
	List<ParentClubInfo> listParentClubByClubCode(String clubCode)throws WEBException;


}
