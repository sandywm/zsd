package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.HwAbilityRelationInfo;

public interface HwAbilityRelationManager {
	
	/**
	 * 增加系统家庭作业能力类型关联信息
	 * @author wm
	 * @date 2019-7-22 下午05:23:23
	 * @param batIdStr 能力类型编号
	 * @param hwId 家庭作业编号
	 * @return
	 * @throws WEBException
	 */
	boolean addHAR(String batIdStr,Integer hwId) throws WEBException;
	
	/**
	 * 根据能力类型编号、家庭作业编号获取系统家庭作业能力类型关联信息列表
	 * @author wm
	 * @date 2019-7-22 下午05:23:54
	 * @param batId 能力类型编号
	 * @param hwId 家庭作业编号
	 * @return
	 * @throws WEBException
	 */
	List<HwAbilityRelationInfo> listInfoByOpt(Integer batId,Integer hwId) throws WEBException;
	
	/**
	 * 根据家庭作业编号批量删除系统家庭作业能力类型关联信息记录
	 * @author wm
	 * @date 2019-7-22 下午05:24:25
	 * @param hwId 家庭作业编号
	 * @return
	 * @throws WEBException
	 */
	boolean delHAR(Integer hwId) throws WEBException;
}
