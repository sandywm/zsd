package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.HwStudyDetailInfo;

public interface HwStudyDetailManager {

	/**
	 * 批量增加家庭作业题库（学生点击家庭作业时自动创建）
	 * @author wm
	 * @date 2019-7-28 下午04:43:09
	 * @param hwTjId 家庭作业统计编号
	 * @param queIdStr 题库编号（多个用逗号隔开）
	 * @param queAreaStr 题库范围（hw,sys,tea）
	 * @return
	 * @throws WEBException
	 */
	boolean addBatchHWSD(Integer hwTjId,String queIdStr,String queAreaStr) throws WEBException; 
	
	/**
	 * 根据主键修改答题记录
	 * @author wm
	 * @date 2019-7-28 下午04:51:51
	 * @param id
	 * @param myAnser 我的答案
	 * @param result 答题结果（0:错,1:对）
	 * @return
	 * @throws WEBException
	 */
	boolean updateInfoById(Integer id,String myAnser,Integer result) throws WEBException; 
	
	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-7-28 下午04:52:34
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	HwStudyDetailInfo getEntityById(Integer id) throws WEBException; 
	
	/**
	 * 根据条件获取做题记录列表
	 * @author wm
	 * @date 2019-7-28 下午04:52:42
	 * @param sendHwId 发布家庭作业编号
	 * @param hwTjId 家庭作业统计编号(0表示全部)
	 * @param queId 题库编号（0表示全部）
	 * @param queArea 题库范围(（""表示全部）)
	 * @return
	 * @throws WEBException
	 */
	List<HwStudyDetailInfo> listInfoByOpt(Integer sendHwId,Integer hwTjId,Integer queId,String queArea) throws WEBException; 
}
