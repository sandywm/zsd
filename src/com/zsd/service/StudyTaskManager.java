package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.StudyTaskInfo;

public interface StudyTaskManager {
	
	/**
	 * 增加学习任务表
	 * @author wm
	 * @date 2019-5-29 下午05:44:56
	 * @param taskNum 任务序号
	 * @param studyLogId 学习记录编号
	 * @param taskName 任务名称
	 * @param coin 金币
	 * @return
	 * @throws WEBException
	 */
	Integer addSTask(Integer taskNum,Integer studyLogId,String taskName,Integer coin) throws WEBException;
	
	/**
	 * 修改学习任务金币奖励数
	 * @author wm
	 * @date 2019-5-29 下午05:45:31
	 * @param id 主键
	 * @param newAddCoin 新增加的金币
	 * @return
	 * @throws WEBException
	 */
	boolean updateCoinInfoById(Integer id,Integer newAddCoin) throws WEBException;
	
	/**
	 * 根据学习记录编号、学习任务获取学习任务记录列表
	 * @author wm
	 * @date 2019-5-29 下午05:45:55
	 * @param sLogId 学习记录编号
	 * @param taskName 学习任务
	 * @return
	 * @throws WEBException
	 */
	List<StudyTaskInfo> listTaskInfoByOpt(Integer sLogId,String taskName) throws WEBException;
	
	/**
	 * 获取最后一次的任务
	 * @author wm
	 * @date 2019-6-5 下午04:40:38
	 * @param studyLogId 学习记录编号
	 * @return
	 * @throws WEBException
	 */
	StudyTaskInfo  getLastInfoByLogId(Integer studyLogId) throws WEBException;
}
