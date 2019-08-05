package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.NetTeacherStudioInfo;

public interface NetTeacherStudioManager {
	/**
	 * 添加网络老师工作室信息
	 * @author zdf
	 * 2019-7-25 上午11:44:22
	 * @param teaId 网络导师编号
	 * @param studioName 工作室名称
	 * @param studioCode 工作室邀请码
	 * @param maxNum  最大人数
	 * @param studioProfile  工作室简介
	 * @return
	 * @throws WEBException
	 */
	Integer addNTStudio( Integer teaId, String studioName,String studioCode, Integer maxNum,String studioProfile)throws WEBException;
	/**
	 * 更新网络老师工作室
	 * @author zdf
	 * 2019-7-25 上午11:44:27
	 * @param id 主键编号
	 * @param studioName 工作室名称
	 * @param studioCode 工作室邀请码
	 * @param maxNum 最大人数
	 * @param studioProfile 工作室简介
	 * @return
	 */
	boolean  updateNTStudio(Integer id , String studioName, Integer maxNum,String studioProfile)throws WEBException;
	/**
	 * 根据用户编号获取网络导师工作室信息
	 * @author zdf
	 * 2019-7-28 上午10:16:02
	 * @param userId 用户编号
	 * @return
	 * @throws WEBException
	 */
	 List<NetTeacherStudioInfo> listNTStudioByuId(Integer userId)throws WEBException;
	 /**
	  * 根据工作室邀请码获取工作室信息
	  * @author zdf
	  * 2019-7-31 上午09:55:41
	  * @param studioCode 邀请码
	  * @return
	  * @throws WEBException
	  */
	 List<NetTeacherStudioInfo> listNTStudioBystudioCode(String studioCode)throws WEBException;

}
