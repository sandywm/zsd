package com.zsd.service;

import java.util.List;

import org.hibernate.Session;

import com.zsd.exception.WEBException;
import com.zsd.module.StudyDetailInfo;

public interface StudyDetailManager {

	/**
	 * 增加学习记录详情
	 * @author wm
	 * @date 2019-6-2 下午03:48:01
	 * @param userId 学生编号
	 * @param studyLogId 学习记录编号
	 * @param loreId 知识点编号
	 * @param loreQuestionId 题库编号
	 * @param questionStep 问题步骤
	 * @param realAnswer 正确答案
	 * @param result 答题结果
	 * @param addTime 添加时间
	 * @param myAnswer 我的答案
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @param completeTimes 完成次数
	 * @return
	 * @throws WEBException
	 */
	Integer addStudyDetail(Integer userId,Integer studyLogId,Integer loreId,Integer loreQuestionId,
			Integer questionStep,String realAnswer,Integer result,String addTime,String myAnswer,
			String a,String b,String c,String d,String e,String f,Integer completeTimes) throws WEBException;
	
	/**
	 * 根据学习记录编号获取学习记录详情列表
	 * @author wm
	 * @date 2019-6-2 下午03:49:04
	 * @param studyLogId 学习记录编号
	 * @return
	 * @throws WEBException
	 */
	List<StudyDetailInfo> listInfoByLogId(Integer studyLogId) throws WEBException;
	
	/**
	 * 根据学习记录编号获取当前级知识点所有答对的指定答题类型的记录列表
	 * @author wm
	 * @date 2019-6-3 上午11:56:23
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param loreId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @return
	 * @throws WEBException
	 */
	List<StudyDetailInfo> listCurrentRightInfoByLogId(Integer studyLogId, Integer loreId, String loreTypeName) throws WEBException;
	
	/**
	 * 根据学习记录编号和知识点编号和知识点诊断类型获取最后一条数据
	 * @author wm
	 * @date 2019-6-3 下午04:48:53
	 * @param studyLogId 学习记录编号
	 * @param loreId 知识点编号(0不查询)
	 * @param loreTypeName 答题类型(""不查询)
	 * @return
	 * @throws WEBException
	 */
	List<StudyDetailInfo> listLastInfoByLogId(Integer studyLogId, Integer loreId, String loreTypeName) throws WEBException;
	
	/**
	 * 根据学习记录编号获取当前级知识点所有答对的再次诊断并且不是当前阶段所做的记录列表
	 * @author wm
	 * @date 2019-6-3 下午05:13:29
	 * @param studyLogId 学习记录编号
	 * @param logId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @param completeTimes 完成次数
	 * @return 所有答对的再次诊断记录列表
	 * @throws WEBException
	 */
	List<StudyDetailInfo> listPretRightInfoByLogId(Integer studyLogId,Integer loreId,String loreTypeName,Integer completeTimes) throws WEBException;
}
