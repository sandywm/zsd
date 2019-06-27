package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.BuffetLoreStudyDetailInfo;

public interface BuffetLoreStudyDetailDao {

	/**
	 * 根据主键获取自助餐知识点学习记录详情信息实体
	 * @author wm
	 * @date 2019-6-27 下午05:53:12
	 * @param sess
	 * @param id
	 * @return
	 */
	BuffetLoreStudyDetailInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加自助餐知识点学习记录详情
	 * @author wm
	 * @date 2019-6-27 下午05:53:36
	 * @param sess
	 * @param blsd
	 */
	void save(Session sess,BuffetLoreStudyDetailInfo blsd);
	
	/**
	 * 修改自助餐知识点学习记录详情
	 * @author wm
	 * @date 2019-6-27 下午05:53:50
	 * @param sess
	 * @param blsd
	 */
	void update(Session sess,BuffetLoreStudyDetailInfo blsd);
	
	/**
	 * 根据学习记录编号获取学习详情列表
	 * @author wm
	 * @date 2019-6-27 下午05:54:00
	 * @param sess
	 * @param blsdId 学习记录编号
	 * @return
	 */
	List<BuffetLoreStudyDetailInfo> findInfoByLogId(Session sess,Integer blsdId);
	
	/**
	 * 根据学习记录编号获取当前级知识点所有答对的再次诊断记录列表
	 * @author wm
	 * @date 2019-6-27 下午05:54:03
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param loreId 当前知识典编号(0不查询)
	 * @param loreTypeName 答题类型(""不查询)
	 * @return
	 */
	List<BuffetLoreStudyDetailInfo> findCurrentRightInfoByLogId(Session sess,Integer studyLogId,Integer loreId,String loreTypeName);
	
	/**
	 * 根据学习记录编号获取当前级知识点所有答对的再次诊断并且不是当前阶段所做的记录列表
	 * @author wm
	 * @date 2019-6-27 下午05:54:06
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param logId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @param completeTimes 完成次数
	 * @return 所有答对的再次诊断记录列表
	 */
	List<BuffetLoreStudyDetailInfo> findPretRightInfoByLogId(Session sess,Integer studyLogId,Integer loreId,String loreTypeName,Integer completeTimes);
	
	/**
	 * 根据学习记录编号和知识点编号和知识点诊断类型获和完成次数取最后一次答题数据列表
	 * @author wm
	 * @date 2019-6-27 下午05:54:09
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param logId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @param completeTimes 完成次数
	 * @return
	 */
	List<BuffetLoreStudyDetailInfo> findLastInfoByOpt(Session sess,Integer studyLogId,Integer loreId,String loreTypeName,Integer completeTimes);
}
