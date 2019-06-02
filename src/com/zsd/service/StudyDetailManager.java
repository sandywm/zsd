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
}
