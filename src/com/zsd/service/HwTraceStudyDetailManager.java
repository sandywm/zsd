package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.HwTraceStudyDetailInfo;

public interface HwTraceStudyDetailManager {

	/**
	 * 增加家庭作业溯源学习记录明细
	 * @author wm
	 * @date 2019-8-16 下午04:56:40
	 * @param userId 学生编号
	 * @param studyLogId 学习记录编号
	 * @param lqId 题库编号
	 * @param loreId 知识点编号
	 * @param questionStep 问题步骤
	 * @param realAnswer 真实答案
	 * @param result 结果
	 * @param myAnswer 我的答案
	 * @param a 做题时的答案A选项
	 * @param b 做题时的答案B选项
	 * @param c 做题时的答案C选项
	 * @param d 做题时的答案D选项
	 * @param e 做题时的答案E选项
	 * @param f 做题时的答案F选项
	 * @param completeTimes 做过该问题的次数
	 * @return
	 * @throws WEBException
	 */
	Integer addHTSDetail(Integer userId,Integer studyLogId,Integer lqId,Integer loreId,
			Integer questionStep,String realAnswer,Integer result,String myAnswer,
			String a,String b,String c,String d,String e,String f,Integer completeTimes) throws WEBException;
	
	/**
	 * 获取指定学习记录下最后一次的学习详情列表
	 * @author wm
	 * @date 2019-8-16 下午04:57:45
	 * @param studyLogId 学习记录编号
	 * @return
	 * @throws WEBException
	 */
	List<HwTraceStudyDetailInfo> listLastInfoByLogId(Integer studyLogId) throws WEBException;
	
	/**
	 * 获取指定学习记录下的学习详情列表
	 * @author wm
	 * @date 2019-8-16 下午04:57:51
	 * @param studyLogId 学习记录编号
	 * @return
	 * @throws WEBException
	 */
	List<HwTraceStudyDetailInfo> listInfoByLogId(Integer studyLogId) throws WEBException;
	
	/**
	 * 根据学习记录编号和知识点编号和知识点诊断类型获和完成次数取最后一次答题数据
	 * @author wm
	 * @date 2019-8-16 下午04:57:54
	 * @param buffetLoreStudyLogId 学习记录编号
	 * @param currentLoreId 知识点编号
	 * @param loreType 知识点类型
	 * @param completeTimes 完成次数
	 * @return
	 * @throws WEBException
	 */
	List<HwTraceStudyDetailInfo> listLastInfoByOption(Integer studyLogId,Integer currentLoreId,String loreType,
			Integer completeTimes)throws WEBException;
	
	/**
	 * 根据学习记录编号获取当前答题正确的再次诊断题
	 * @author wm
	 * @date 2019-8-16 下午04:57:56
	 * @param studyLogId 学习记录编号
	 * @param currentLoreId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @return 正确再次诊断题
	 * @return
	 * @throws WEBException
	 */
	List<HwTraceStudyDetailInfo> listCurrentRightInfoByLogId(Integer studyLogId,Integer currentLoreId,String loreTypeName)throws WEBException;
	
	/**
	 * 根据学习记录编号获取有无当前知识点指定类型并且不是当前阶段的答题记录
	 * @author wm
	 * @date 2019-8-16 下午04:57:59
	 *  @param studyLogId 学习记录编号
	 * @param currentLoreId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @param completeTimes 完成次数
	 * @return 答题记录列表
	 * @throws WEBException
	 */
	List<HwTraceStudyDetailInfo> listPretRightInfoByLogId(Integer studyLogId,Integer currentLoreId,String loreTypeName,Integer completeTimes) throws WEBException;
	
	/**
	 * 根据学习记录编号获取有无当前知识点指定类型的答题记录
	 * @author wm
	 * @date 2019-8-16 下午04:58:02
	 * @param studyLogId 学习记录编号
	 * @param currentLoreId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @return
	 * @throws WEBException
	 */
	List<HwTraceStudyDetailInfo> listExistInfoByOption(Integer studyLogId,Integer currentLoreId,String loreTypeName)throws WEBException;
	
	/**
	 * 根据学习记录编号和问题编号获取做该题的次数
	 * @author wm
	 * @date 2019-8-16 下午04:58:04
	 * @param studyLogId 学习记录编号
	 * @param lqId 题库编号
	 * @return
	 * @throws WEBException
	 */
	Integer getQuestionNumberByOption(Integer studyLogId,Integer lqId)throws WEBException;
	
	/**
	 * 根据主键获取信息实体
	 * @author wm
	 * @date 2019-8-16 下午04:58:07
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	HwTraceStudyDetailInfo getEntityById(Integer id)throws WEBException;
}
