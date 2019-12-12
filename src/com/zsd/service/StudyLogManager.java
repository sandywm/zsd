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
	 * @param ntId 导师编号(有导师时存在，没有时为0)
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
	Integer addStudyLog(Integer userId,Integer ntId,Integer loreId,Integer subId,Integer step,Integer stepComplete,Integer isFinish,
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
	 * @param step 答题阶段（共5段）-1不修改
	 * @param stepComplete 该阶段完成状态（题有无做完）-1不修改
	 * @param isFinish 该知识典完成状态-1不修改
	 * @param currentGold 当前阶段答题分数-1不修改
	 * @param access 本级知识点完成情况-1不修改
	 * @param addTime 添加时间 （""不修改）
	 * @return
	 * @throws WEBException
	 */
	boolean updateStudyLog(Integer id,Integer step,Integer stepComplete,
			Integer isFinish,Integer currentGold,Integer access,String addTime)throws WEBException;
	
	/**
	 * 根据主键修改答题记录的本阶段的完成状态和整个知识典的完成状态
	 * @author wm
	 * @date 2019-6-14 下午03:39:05
	 * @param id
	 * @param step 阶段(为0时不修改)
	 * @param taskNumber 任务数(为0时不修改)
	 * @param stepComplete 该阶段完成状态（题有无做完）
	 * @param isFinish 该知识点完成状态
	 * @return
	 * @throws WEBException
	 */
	boolean updateLogStatus(Integer id, Integer step,Integer stepComplete,
			Integer isFinish,Integer access,Integer taskNumber) throws WEBException; 
	
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
	
	/**
	 * 增加系统评价
	 * @author wm
	 * @date 2019-6-14 下午04:52:44
	 * @param id 主键
	 * @param sysAssess 系统评价
	 * @param finalScore 分数
	 * @return
	 * @throws WEBException
	 */
	boolean addSysAssess(Integer id,String sysAssess,Integer finalScore)throws WEBException;
	
	/**
	 * 增加网络导师评价
	 * @author wm
	 * @date 2019-6-14 下午04:52:53
	 * @param id 主键
	 * @param teaAssess 网络导师评价
	 * @return
	 * @throws WEBException
	 */
	boolean addTeaAssess(Integer id,String teaAssess)throws WEBException;
	/**
	 * 根据学科编号,学生编号  类型,完成状态,时间段获取学习记录信息
	 * @author zdf
	 * 2019-6-20 上午09:49:02
	  * @param userId 学生编号
	 * @param subId 学科编号
	 * @param logType 学习记录类型 (1:自学,2:家庭作业)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @param isfinish 完成状态 (1:未完成,2:已完成)
	 * @return
	 * @throws WEBException
	 */
	List<StudyLogInfo> listSlInfoByopt(Integer userId,
			Integer subId, Integer isfinish, Integer logType, String sDate,String eDate)throws WEBException;
	/**
	 * 根据导师用户编号,学生编号,时间段获取指定学生完成学习记录
	 * @author zdf
	 * 2019-6-28 下午04:41:37
	 * @param userId 导师用户编号
	 * @param subId 学科编号
	 * @param stuIdStr 学生编号(多个用逗号隔开)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 * @throws WEBException
	 */
	List<StudyLogInfo> listStuLogByOption(Integer userId,Integer subId,String stuIdStr,String sDate, String eDate)throws WEBException;
	/**
	 * 学生跟踪指导信息
	 * @author zdf
	 * 2019-9-17 上午09:20:37
	 * @param sess
	 * @param stuId  学生编号
	 * @param isFinish 完成状态
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 * @throws WEBException
	 */
	List<StudyLogInfo> listStuLogByStu(Integer teaId,Integer stuId,Integer guideSta,String sDate, String eDate)throws WEBException;
	
	/**
	 * 获取指定学生最近未完成的指定数量的记录列表
	 * @author wm
	 * @date 2019-10-18 上午09:52:19
	 * @param stuId 学生编号
	 * @param limitNumber 限制条数
	 * @return
	 * @throws WEBException
	 */
	List<StudyLogInfo> listLimitUnComInfoByStuId(Integer stuId,Integer limitNumber)throws WEBException;
	
}
