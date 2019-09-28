package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.ParentClubRelationInfo;

public interface ParentClubRelationManager {
	/**
	 * 添加家长群列表信息
	 * @author zdf
	 * 2019-9-27 下午05:03:44
	 * @param userId 用户编号
	 * @param pcId 家长群编号
	 * @param addTime 添加时间
	 * @param outTime 离开时间
	 * @return
	 * @throws WEBException
	 */
	Integer addParentClubRelation(Integer userId,Integer pcId, String addTime,String outTime)throws WEBException;
	/**
	 * 根据家长群编号获取家长群列表信息
	 * @author zdf
	 * 2019-9-27 下午05:06:05
	 * @param pcId 家长群主键
	 * @return
	 * @throws WEBException
	 */
	List<ParentClubRelationInfo> listInfoByParentCludId(Integer pcId)throws WEBException;
	/**
	 * 根据用户编号获取家长群列表
	 * @author zdf
	 * 2019-9-27 下午05:06:55
	 * @param userId  用户编号
	 * @return
	 * @throws WEBException
	 */
	List<ParentClubRelationInfo> listInfoByUserId(Integer userId)throws WEBException;
}
