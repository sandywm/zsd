package com.zsd.service;


import com.zsd.exception.WEBException;
import com.zsd.module.BuffetLoreStudyLogInfo;

public interface BuffetLoreStudyLogManager {

	/**
	 * 增加自助餐知识点学习记录信息
	 * @author wm
	 * @date 2019-6-28 上午10:41:40
	 * @param userId 学生编号
	 * @param buffetStudyDetailId 巴菲特学习记录编号
	 * @param step 答题阶段（共5段）
	 * @param stepComplete 该阶段完成状态（题有无做完）
	 * @param type 答题类型（1：课后，2：课前预习）
	 * @param isFinish 该知识点完成状态
	 * @param result 学习结果(系统发送评定)
	 * @param currentGold 当前阶段答题分数
	 * @param access 打开次数(暂不用)
	 * @param addTime 添加时间
	 * @param taskNumber 任务个数
	 * @return
	 * @throws WEBException
	 */
	Integer addBLSLog(Integer userId,Integer buffetStudyDetailId,Integer subjectId,Integer step,Integer stepComplete,Integer isFinish,
			Integer currentGold,Integer access,String addTime,Integer taskNumber) throws WEBException;
	
	/**
	 * 根据主键获取自助餐知识点学习记录详情
	 * @author wm
	 * @date 2019-6-28 上午10:43:54
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	BuffetLoreStudyLogInfo getEntityById(Integer id) throws WEBException;
	
	/**
	 * 根据自助餐学习详情编号获取自助餐知识点学习记录详情
	 * @author wm
	 * @date 2019-6-28 上午10:44:06
	 * @param bsdId 自助餐学习详情编号
	 * @return
	 * @throws WEBException
	 */
	BuffetLoreStudyLogInfo getEntityByBsdId(Integer bsdId) throws WEBException;
	
	/**
	 * 修改学习记录
	 * @author wm
	 * @date 2019-6-28 上午10:44:10
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
	 * @date 2019-6-28 上午10:44:14
	 * @param id
	 * @param step 阶段(为0时不修改)
	 * @param taskNumber 任务数(为0时不修改)
	 * @param stepComplete 该阶段完成状态（题有无做完）
	 * @param isFinish 该知识点完成状态
	 * @param access 本级知识点完成情况-1不修改
	 * @return
	 * @throws WEBException
	 */
	boolean updateLogStatus(Integer id, Integer step,Integer stepComplete,
			Integer isFinish,Integer access,Integer taskNumber) throws WEBException; 
	
}
