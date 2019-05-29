package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.StudyLogInfo;

public interface StudyLogDao {

	/**
	 * 根据主键获取学习记录实体
	 * @author wm
	 * @date 2019-5-29 上午08:36:04
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	StudyLogInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加学习记录
	 * @author wm
	 * @date 2019-5-29 上午08:36:20
	 * @param sess
	 * @param sl
	 */
	void save(Session sess,StudyLogInfo sl);
	
	/**
	 * 修改学习记录
	 * @author wm
	 * @date 2019-5-29 上午08:36:27
	 * @param sess
	 * @param sl
	 */
	void update(Session sess,StudyLogInfo sl);
	
	/**
	 * 删除学习记录
	 * @author wm
	 * @date 2019-5-29 上午08:36:40
	 * @param sess
	 * @param sl
	 */
	void delete(Session sess,StudyLogInfo sl);
	
	/**
	 * 根据条件获取学习记录
	 * @author wm
	 * @date 2019-5-29 上午08:36:50
	 * @param sess
	 * @param userId 学生编号
	 * @param subId 学科编号
	 * @param logType 学习记录类型 (1:自学,2:家庭作业)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 */
	List<StudyLogInfo> findStuLogByOpt(Session sess,Integer userId,Integer subId, Integer logType,String sDate, String eDate);
	
	/**
	 * 根据学生编号、知识点编号获取最后一次学习记录
	 * @author wm
	 * @date 2019-5-29 上午08:37:23
	 * @param sess
	 * @param userId 学生编号
	 * @param loreId 知识点编号
	 * @param logType 学习记录类型 (1:自学,2:家庭作业)
	 * @return
	 */
	List<StudyLogInfo> findLastStudyInfoByOpt(Session sess,Integer userId,Integer loreId,Integer logType);
}
