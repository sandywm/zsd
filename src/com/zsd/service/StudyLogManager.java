package com.zsd.service;


import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.StudyLogInfo;

public interface StudyLogManager {

	/**
	 * 增加学习记录
	 * @author wm
	 * @date 2019-5-29 上午08:52:21
	 * @param userId 学生编号
	 * @param loreId 知识点编号
	 * @param subId 科目编号
	 * @param step 答题阶段（共5段）
	 * @param stepComplete 该阶段完成状态（题有无做完）
	 * @param isFinish 该知识点完成状态
	 * @param sysAssess 学习结果(系统发送评定)
	 * @param currentGold 当前阶段答题分数
	 * @param access 打开次数(暂不用)
	 * @param addTime 添加时间
	 * @param taskNumber 任务个数
	 * @param logType 学习记录类型 (1:自学,2:家庭作业)
	 * @return
	 * @throws WEBException
	 */
	Integer addStudyLog(Integer userId,Integer loreId,Integer subId,Integer step,Integer stepComplete,Integer isFinish,
			String sysAssess,Integer currentGold,Integer access,String addTime,Integer taskNumber,Integer logType) throws WEBException;
	
	/**
	 * 删除学习记录
	 * @author wm
	 * @date 2019-5-29 上午08:55:00
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	boolean deleteStudyLog(Integer id)throws WEBException;
	
	/**
	 * 修改学习记录
	 * @author wm
	 * @date 2019-5-29 上午08:55:19
	 * @param id
	 * @param step 答题阶段（共5段）
	 * @param stepComplete 该阶段完成状态（题有无做完）
	 * @param isFinish 该知识典完成状态
	 * @param currentGold 当前阶段答题分数
	 * @param access 本级知识点完成情况
	 * @param addTime 添加时间
	 * @return
	 * @throws WEBException
	 */
	boolean updateStudyLog(Integer id,Integer step,Integer stepComplete,
			Integer isFinish,Integer currentGold,Integer access,String addTime)throws WEBException;
	
	/**
	 * 根据主键获取学习记录
	 * @author wm
	 * @date 2019-5-29 上午08:59:50
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	StudyLogInfo getEntityById(Integer id)throws WEBException;
	
	/**
	 * 根据条件获取学习记录
	 * @author wm
	 * @date 2019-5-29 上午09:00:53
	 * @param userId 学生编号
	 * @param subId 学科编号
	 * @param logType 学习记录类型 (1:自学,2:家庭作业)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 * @throws WEBException
	 */
	List<StudyLogInfo> listStudyLogInfoByOpt(Integer userId,Integer subId, Integer logType,String sDate, String eDate)throws WEBException;
	
	/**
	 * 根据学生编号、知识点编号获取最后一次学习记录
	 * @author wm
	 * @date 2019-5-29 上午09:02:35
	 * @param userId 学生编号
	 * @param loreId 知识点编号
	 * @param logType 学习记录类型 (1:自学,2:家庭作业)
	 * @return
	 * @throws WEBException
	 */
	List<StudyLogInfo> listLastStudyInfoByOpt(Integer userId,Integer loreId,Integer logType)throws WEBException;
	
}