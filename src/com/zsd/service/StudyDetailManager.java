package com.zsd.service;

import java.util.List;

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
	 * @param loreId 当前知识典编号(0不查询)
	 * @param loreTypeName 答题类型(""不查询)
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
	
	/**
	 * 根据学习记录编号和知识点编号和知识点诊断类型获取已做题列表
	 * @author wm
	 * @date 2019-6-11 上午10:44:32
	 * @param studyLogId 学习记录编号
	 * @param loreId 知识点编号(0不查询)
	 * @param loreTypeName 答题类型(""不查询)
	 * @return
	 * @throws WEBException
	 */
	List<StudyDetailInfo> listInfoByOpt(Integer studyLogId, Integer loreId, String loreTypeName) throws WEBException;
	
	/**
	 * 根据学习记录编号和知识点编号和知识点诊断类型获和完成次数取最后一次答题数据列表
	 * @author wm
	 * @date 2019-6-11 上午11:49:26
	 * @param studyLogId 学习记录编号
	 * @param logId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @param completeTimes 完成次数
	 * @return
	 * @throws WEBException
	 */
	List<StudyDetailInfo> listLastInfoByOpt(Integer studyLogId,Integer loreId,String loreTypeName,Integer completeTimes) throws WEBException;
	
	/**
	 * 检查同一学习记录当天不能成功2次以上的记录情况【针对性诊断+再次诊断】（防止用户恶意提交赚取金币）
	 * @author wm
	 * @date 2019-6-13 上午10:24:33
	 * @param studyLogId 学习记录编号
	 * @param lqId 题库编号
	 * @param currDate 当前时间
	 * @return true:存在，false:不存在或者做错
	 * @throws WEBException
	 */
	boolean checkSuccCompleteFlag(Integer studyLogId, Integer lqId, String currDate) throws WEBException;
	
	/**
	 * 根据学习记录编号、题库编号获取做题信息列表
	 * @author wm
	 * @date 2019-6-13 下午05:03:31
	 * @param studyLogId 学习记录编号
	 * @param lqId 题库编号
	 * @return
	 * @throws WEBException
	 */
	List<StudyDetailInfo> listInfoByOpt(Integer studyLogId,Integer lqId) throws WEBException;
	/**
	 * 根据学习记录编号,类型分页获取学习详情信息
	 * @author zdf
	 * 2019-6-25 上午10:45:31
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param typeName  知识点类型
	 * @param pageNo 第几页
	 * @param pageSize 每页多少条
	 * @return
	 * @throws WEBException
	 */
	List<StudyDetailInfo> listInfoByOption(Integer studyLogId, String typeName, int pageNo, int pageSize) throws WEBException;
	/**
	 * 根据学习记录编号,类型获取 记录数
	 * @author zdf
	 * 2019-6-25 下午04:13:00
	 * @param studyLogId 学习记录编号
	 * @param typeName 知识点类型名
	 * @return
	 * @throws WEBException
	 */
	Integer getInfoByOption(Integer studyLogId,String typeName)throws WEBException;
}
