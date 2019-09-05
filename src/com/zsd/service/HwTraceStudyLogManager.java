package com.zsd.service;

import com.zsd.exception.WEBException;
import com.zsd.module.HwTraceStudyLogInfo;

public interface HwTraceStudyLogManager {

	/**
	 * 增加家庭作业溯源学习记录
	 * @author wm
	 * @date 2019-8-16 上午10:44:31
	 * @param tjId 统计编号
	 * @param stuId 学生编号
	 * @param loreId 发布作业的知识点编号
	 * @param step 答题阶段
	 * @param isFinish 完成状态
	 * @param currentGold 当前阶段答题分数
	 * @param access 本级知识点完成状态
	 * @param taskNumber 任务个数
	 * @return
	 * @throws WEBException
	 */
	Integer addHwStudyLog(Integer tjId,Integer stuId,Integer step,Integer stepComplete,
			Integer currentGold,Integer access,Integer taskNumber) throws WEBException;
	
	/**
	 * 修改家庭作业溯源学习记录信息
	 * @author wm
	 * @date 2019-8-16 上午10:45:42
	 * @param id 主键
	 * @param step 答题阶段（共5段）0不修改
	 * @param stepComplete 该阶段完成状态（题有无做完）-1不修改
	 * @param isFinish 该知识典完成状态-1不修改
	 * @param currentGold 当前阶段答题分数-1不修改
	 * @param access 本级知识点完成情况-1不修改
	 * @param taskNumber 任务个数(为0时不修改)
	 * @param addTime 添加时间 （""不修改）
	 * @return
	 * @throws WEBException
	 */
	boolean updateStudyLog(Integer id,Integer step,Integer stepComplete,Integer isFinish,Integer currentGold,Integer access,Integer taskNumber,String addTime)throws WEBException;
	
	/**
	 * 根据主键获取溯源学习记录
	 * @author wm
	 * @date 2019-8-16 上午10:48:39
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	HwTraceStudyLogInfo getEntityById(Integer id)throws WEBException;
	
	/**
	 * 根据统计编号获取溯源学习记录
	 * @author wm
	 * @date 2019-8-16 上午10:48:55
	 * @param tjId
	 * @return
	 * @throws WEBException
	 */
	HwTraceStudyLogInfo getEntityByTjId(Integer tjId)throws WEBException;
}
