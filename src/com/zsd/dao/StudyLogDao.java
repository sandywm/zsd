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
	/**
	 * 根据用户编号,学科编号,完成状态,时间段获取学习记录
	 * @author zdf
	 * 2019-6-19 下午04:58:21
	 * @param userId 学生编号
	 * @param subId 学科编号
	 * @param logType 学习记录类型 (1:自学,2:家庭作业)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @param isfinish  完成状态
	 * @return
	 */
	List<StudyLogInfo> findStuLogByOption(Session sess,Integer userId,Integer subId, Integer isfinish, Integer logType,String sDate, String eDate);
	/**
	 * 根据学生编号,时间段获取学生完成的学习记录
	 * @author zdf
	 * 2019-6-28 下午04:26:18
	 * @param sess
	 * @param userId 导师用户编号
	 * @param subId 学科编号
	 * @param stuIdStr 学生编号(多个用逗号隔开)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 */
	List<StudyLogInfo> findStuLogByOption(Session sess,Integer userId,Integer subId,String stuId,String sDate, String eDate);
	/**
	 * 学生跟踪指导信息
	 * @author zdf
	 * 2019-9-17 上午09:14:27
	 * @param sess
	 * @param stuId  学生编号
	 * @param isFinish 完成状态
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 */
	List<StudyLogInfo> findStuLogByStu(Session sess,Integer teaId,Integer stuId,/*Integer subId,*/Integer guideSta,String sDate, String eDate);
	
	/**
	 * 获取指定学生最近未完成的指定数量的记录列表
	 * @author wm
	 * @date 2019-10-18 上午09:46:49
	 * @param sess
	 * @param stuId 学生编号
	 * @param limitNumber 限制条数
	 * @return
	 */
	List<StudyLogInfo> findLimitUnComInfoByStuId(Session sess,Integer stuId,Integer limitNumber);
	
}
