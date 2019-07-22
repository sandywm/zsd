package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.HwMindRelationInfo;

public interface HwMindRelationManager {

	/**
	 * 增加系统家庭作业思维类型关联信息
	 * @author wm
	 * @date 2019-7-22 下午05:23:23
	 * @param bmtId 思维类型编号
	 * @param hwId 家庭作业编号
	 * @return
	 * @throws WEBException
	 */
	Integer addHMR(Integer bmtId,Integer hwId) throws WEBException;
	
	/**
	 * 根据思维类型编号、家庭作业编号获取系统家庭作业思维类型关联信息列表
	 * @author wm
	 * @date 2019-7-22 下午05:23:54
	 * @param bmtId 思维类型编号
	 * @param hwId 家庭作业编号
	 * @return
	 * @throws WEBException
	 */
	List<HwMindRelationInfo> listInfoByOpt(Integer bmtId,Integer hwId) throws WEBException;
	
	/**
	 * 根据家庭作业编号批量删除系统家庭作业思维类型关联信息记录
	 * @author wm
	 * @date 2019-7-22 下午05:24:25
	 * @param hwId 家庭作业编号
	 * @return
	 * @throws WEBException
	 */
	boolean delHMR(Integer hwId) throws WEBException;
}
