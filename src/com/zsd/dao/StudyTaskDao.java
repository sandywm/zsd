package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.StudyTaskInfo;

public interface StudyTaskDao {

	/**
	 * 获取指定主键的学习任务实体
	 * @author wm
	 * @date 2019-5-29 下午05:54:59
	 * @param sess
	 * @param id
	 * @return
	 */
	StudyTaskInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加学习任务记录
	 * @author wm
	 * @date 2019-5-29 下午05:36:22
	 * @param sess
	 * @param st
	 */
	void save(Session sess,StudyTaskInfo st);
	
	/**
	 * 修改学习任务记录
	 * @author wm
	 * @date 2019-5-29 下午05:36:32
	 * @param sess
	 * @param st
	 */
	void update(Session sess,StudyTaskInfo st);
	
	/**
	 * 根据学习记录、学习任务列出学习任务表记录列表
	 * @author wm
	 * @date 2019-5-29 下午05:41:32
	 * @param sess
	 * @param sLogId 学习记录编号
	 * @param taskName 学习任务(""不查询)
	 * @return
	 */
	List<StudyTaskInfo> findInfoByOpt(Session sess,Integer sLogId,String taskName);
	
	/**
	 * 获取最后一次的任务
	 * @author wm
	 * @date 2019-6-5 下午04:38:32
	 * @param sess
	 * @param sLogId 学习记录编号
	 * @return
	 */
	StudyTaskInfo getLastInfoByLogId(Session sess,Integer sLogId);
}
