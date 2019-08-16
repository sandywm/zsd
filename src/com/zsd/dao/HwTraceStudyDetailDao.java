package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.HwTraceStudyDetailInfo;

public interface HwTraceStudyDetailDao {

	/**
	 * 根据主键获取家庭作业溯源学习记录明细
	 * @author wm
	 * @date 2019-8-16 上午11:14:59
	 * @param sess
	 * @param id
	 * @return
	 */
	HwTraceStudyDetailInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加家庭作业溯源学习记录明细
	 * @author wm
	 * @date 2019-8-16 上午11:15:37
	 * @param sess
	 * @param sd
	 */
	void save(Session sess,HwTraceStudyDetailInfo sd);
	
	/**
	 * 根据学习记录编号获取家庭作业溯源学习记录明细列表
	 * @author wm
	 * @date 2019-8-16 上午11:15:46
	 * @param sess
	 * @param logId 学习记录编号
	 * @return
	 */
	List<HwTraceStudyDetailInfo> findInfoByLogId(Session sess,Integer logId);
	
	/**
	 * 根据学习记录编号获取当前级知识点所有答对的再次诊断记录列表
	 * @author wm
	 * @date 2019-8-16 上午11:16:12
	 * @param sess
	 * @param logId 学习记录编号
	 * @param loreId 当前知识典编号(0不查询)
	 * @param loreTypeName 答题类型(""不查询)
	 * @return
	 */
	List<HwTraceStudyDetailInfo> findCurrentRightInfoByLogId(Session sess,Integer logId,Integer loreId,String loreTypeName);
	
	/**
	 * 根据学习记录编号获取最后一条学习详情列表
	 * @author wm
	 * @date 2019-8-16 上午11:17:08
	 * @param sess
	 * @param logId 学习记录编号
	 * @return
	 */
	List<HwTraceStudyDetailInfo> findLastInfoByLogId(Session sess,Integer logId);
	
	/**
	 * 根据学习记录编号和知识点编号和知识点诊断类型获和完成次数取最后一次答题数据列表
	 * @author wm
	 * @date 2019-8-16 上午11:18:13
	 * @param sess
	 * @param logId 学习记录编号
	 * @param logId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @param completeTimes 完成次数
	 * @return
	 */
	List<HwTraceStudyDetailInfo> findLastInfoByOpt(Session sess,Integer logId,Integer loreId,String loreTypeName,Integer completeTimes);
	
	/**
	 * 根据学习记录编号获取当前级知识点所有答对的再次诊断并且不是当前阶段所做的记录列表
	 * @author wm
	 * @date 2019-8-16 上午11:18:48
	 * @param sess
	 * @param logId 学习记录编号
	 * @param loreId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @param completeTimes 完成次数
	 * @return 所有答对的再次诊断记录列表
	 * @return
	 */
	List<HwTraceStudyDetailInfo> findPretRightInfoByLogId(Session sess,Integer logId,Integer loreId,String loreTypeName,Integer completeTimes);
	
	/**
	 * 根据学习记录编号获取当前知识点有无指定类型的答题记录
	 * @author wm
	 * @date 2019-8-16 上午11:18:52
	 * @param sess
	 * @param logId 学习记录编号
	 * @param loreId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @return
	 */
	List<HwTraceStudyDetailInfo> findExistInfoByLogId(Session sess,Integer logId,Integer loreId,String loreTypeName);
	
	/**
	 * 根据学习记录编号和问题编号获取做该题的次数
	 * @author wm
	 * @date 2019-8-16 上午11:18:55
	 * @param sess
	 * @param logId 学习记录编号
	 * @param lqId 知识点题库编号
	 * @return
	 */
	Integer getQuestionNumberByOption(Session sess,Integer logId,Integer lqId);
}
