package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.HwStudyTjInfo;

public interface HwStudyTjManager {

	/**
	 * 批量增加家庭作业做题统计表
	 * @author wm
	 * @date 2019-7-28 上午10:20:24
	 * @param hwSendId 发送家庭作业记录编号
	 * @param stuIdStr 学生编号（多个用逗号隔开）
	 * @param allNum 发送的题库数量
	 * @throws WEBException
	 */
	boolean addBatchHwStudyTj(Integer hwSendId,String stuIdStr,Integer allNum) throws WEBException;
	
	/**
	 * 修改家庭作业做题统计表信息(分数降序排列)
	 * @author wm
	 * @date 2019-7-28 上午10:28:44
	 * @param id
	 * @param conStatus 完成状态（-1不修改，0-未完成，1-按时完成，2-补做完成）
	 * @param hwScore 作业得分(0不修改)作业完成时才修改
	 * @param succNum 正确题数(1增加，0不修改)
	 * @param errorNum 错误题数(1增加，0不修改)
	 * @param hwsdAddStatus 自动创建题库标记(-1表示不修改,0：未创建，1：已创建)
	 * @return
	 * @throws WEBException
	 */
	boolean updateInfoById(Integer id,Integer conStatus,Integer hwScore,Integer succNum,Integer errorNum,Integer hwsdAddStatus) throws WEBException;
	
	/**
	 * 根据条件获取家庭作业统计记录列表
	 * @author wm
	 * @date 2019-7-28 上午10:49:01
	 * @param hwSendId 发送家庭作业记录编号（0表示全部）
	 * @param stuId 学生编号（0表示全部）
	 * @param comStatus 完成状态（-1表示全部，0-未完成，1-按时完成，2-补做完成）
	 * @param pageFlag 分页标记（true-分页）
	 * @return
	 * @throws WEBException
	 */
	List<HwStudyTjInfo> listInfoByOpt(Integer hwSendId,Integer stuId,Integer comStatus,boolean pageFlag,Integer pageNo,Integer pageSize) throws WEBException;
	
	/**
	 * 根据条件获取家庭作业统计记录条数
	 * @author wm
	 * @date 2019-7-28 上午11:25:09
	 * @param hwSendId 发送家庭作业记录编号（0表示全部）
	 * @param stuId 学生编号（0表示全部）
	 * @param comStatus 完成状态（-1表示全部，0-未完成，1-按时完成，2-补做完成）
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByOpt(Integer hwSendId,Integer stuId,Integer comStatus) throws WEBException;
}