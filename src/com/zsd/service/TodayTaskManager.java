package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.TodayTask;

public interface TodayTaskManager {

	/**
	 * 增加今日任务
	 * @author sandy
	 * @date 2019-12-10 下午10:05:12 
	 * @param stuId 学生编号
	 * @param loreId 知识点编号
	 * @param zdxzdFlag 针对性诊断标记
	 * @param studyFlag 学习标记
	 * @param zczdFlag 再次诊断标记
	 * @param studyTimes 学习次数
	 * @param zczdTimes 再次诊断次数
	 * @return
	 * @throws WEBException
	 */
	Integer addTTask(Integer stuId,Integer loreId, Integer zdxzdFlag,Integer studyFlag,
			Integer zczdFlag, Integer studyTimes, Integer zczdTimes) throws WEBException;
	
	/**
	 * 修改今日任务
	 * @author sandy
	 * @date 2019-12-10 下午10:05:16 
	 * @param id 主键
	 * @param zdxzdFlag 针对性诊断标记（-1不修改）
	 * @param studyFlag 学习标记（-1不修改）
	 * @param zczdFlag 再次诊断标记（-1不修改）
	 * @param studyTimes 学习次数（-1不修改）
	 * @param zczdTimes 再次诊断次数（-1不修改）
	 * @return
	 * @throws WEBException
	 */
	boolean updateEntityById(Integer id,Integer zdxzdFlag, Integer studyFlag,
			Integer zczdFlag, Integer studyTimes, Integer zczdTimes) throws WEBException;
	
	/**
	 * 修改今日任务复习/确认信息
	 * @author sandy
	 * @date 2019-12-10 下午10:05:21 
	 * @param id 主键
	 * @param reviewDate 复习时间(""时不修改)
	 * @param parentUserId 父母确认时间(""时不修改)
	 * @return
	 * @throws WEBException
	 */
	boolean updateStatusById(Integer id,String reviewDate,Integer parentUserId) throws WEBException;
	
	/**
	 * 根据主键获取信息实体
	 * @author sandy
	 * @date 2019-12-10 下午10:05:25 
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	TodayTask getEntityById(Integer id) throws WEBException;
	
	/**
	 * 根据条件获取今日任务列表
	 * @author sandy
	 * @date 2019-12-10 下午10:05:30 
	 * @param stuId 学生编号
	 * @param loreId 知识点编号
	 * @param addDate 日期
	 * @param reviewStatus 复习状态(0：全部，1:未复习，2：已复习)
	 * @param confirmStatus 父母确认状态(0：全部，1:未确认，2：已确认)
	 * @return
	 * @throws WEBException
	 */
	List<TodayTask> listInfoByOpt(Integer stuId,Integer loreId,String addDate,Integer reviewStatus,Integer confirmStatus) throws WEBException;
}
