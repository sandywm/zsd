package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.StudyDetailInfo;

public interface StudyDetailDao {

	/**
	 * 根据主键获取学习详情实体
	 * @author wm
	 * @date 2019-6-2 上午10:53:50
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	StudyDetailInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加学习详情
	 * @author wm
	 * @date 2019-6-2 上午10:54:03
	 * @param sess
	 * @param sd
	 */
	void save(Session sess,StudyDetailInfo sd);
	
	/**
	 * 删除指定学习详情
	 * @author wm
	 * @date 2019-6-2 上午10:54:12
	 * @param sess
	 * @param sd
	 */
	void delete(Session sess,StudyDetailInfo sd);
	
	/**
	 * 修改学习详情
	 * @author wm
	 * @date 2019-6-2 上午10:54:22
	 * @param sess
	 * @param sd
	 */
	void update(Session sess,StudyDetailInfo sd);
	
	/**
	 * 根据学习记录编号获取学习详情列表
	 * @author wm
	 * @date 2019-6-2 上午10:54:29
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @return
	 */
	List<StudyDetailInfo> findInfoByLogId(Session sess,Integer studyLogId);
	
	/**
	 * 根据学习记录编号获取当前级知识点所有答对的再次诊断记录列表
	 * @author wm
	 * @date 2019-6-3 上午11:52:43
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param loreId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @return
	 */
	List<StudyDetailInfo> findCurrentRightInfoByLogId(Session sess,Integer studyLogId,Integer loreId,String loreTypeName);
	
	/**
	 * 根据学习记录编号和知识点编号和知识点诊断类型获取最后一条数据
	 * @author wm
	 * @date 2019-6-3 下午04:45:49
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param loreId 知识点编号(0不查询)
	 * @param loreTypeName 答题类型(""不查询)
	 * @return
	 */
	List<StudyDetailInfo> findLastInfoByLogId(Session sess,Integer studyLogId,Integer loreId,String loreTypeName);
	
	/**
	 * 根据学习记录编号获取当前级知识点所有答对的再次诊断并且不是当前阶段所做的记录列表
	 * @author wm
	 * @date 2019-6-3 下午05:10:18
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param logId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @param completeTimes 完成次数
	 * @return 所有答对的再次诊断记录列表
	 */
	List<StudyDetailInfo> findPretRightInfoByLogId(Session sess,Integer studyLogId,Integer loreId,String loreTypeName,Integer completeTimes);
	
	/**
	 * 根据学习记录，知识点编号，题库类型获取已做过的题库列表
	 * @author wm
	 * @date 2019-6-11 上午10:42:47
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param logId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @return
	 */
	List<StudyDetailInfo> findInfoByOpt(Session sess,Integer studyLogId,Integer loreId,String loreTypeName);
	
	/**
	 * 根据学习记录编号和知识点编号和知识点诊断类型获和完成次数取最后一次答题数据列表
	 * @author wm
	 * @date 2019-6-11 上午11:46:35
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param logId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @param completeTimes 完成次数
	 * @return
	 */
	List<StudyDetailInfo> findLastInfoByOpt(Session sess,Integer studyLogId,Integer loreId,String loreTypeName,Integer completeTimes);
	
	/**
	 * 检查同一学习记录当天不能成功2次以上的记录情况【针对性诊断+再次诊断】（防止用户恶意提交赚取金币）
	 * @author wm
	 * @date 2019-6-13 上午10:26:19
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param lqId 题库编号
	 * @param currDate 当前时间
	 * @return
	 */
	boolean checkSuccCompleteFlag(Session sess,Integer studyLogId, Integer lqId, String currDate);
	
}
