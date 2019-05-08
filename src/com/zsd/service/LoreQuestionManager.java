package com.zsd.service;


import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.LoreQuestion;
import com.zsd.module.LoreQuestionSubInfo;

public interface LoreQuestionManager {

	/**
	 * 增加知识点题库
	 * @author wm
	 * @date 2019-5-7 上午11:44:54
	 * @param loreId 知识点编号
	 * @param loreTypeName 知识点类型（巩固训练,针对性诊断,再次诊断）
	 * @param queNum 题库顺序(每个类型下从1开始)
	 * @param queTitle 题库标题
	 * @param queSub 题干
	 * @param queAnswer 答案
	 * @param queTipId 提示(lore_question_sub表的主键)
	 * @param lexId 词库编号
	 * @param queResolution 解析
	 * @param queType 题型一[单选题、多选题（答案无序）、问答题、判断题、填空题，填空选择题（答案有序）]
	 * @param queOrder 题库整体顺序[(知识清单)1,(点拨指导)2,(解题示范)3-10,(巩固训练)11-60,(针对性诊断)61-110,(再次诊断)111-160,(知识讲解)170]
	 * @param queType2 题型二(了解，理解，应用)
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @param operateUserName 操作人员姓名
	 * @param operateDate 操作时间
	 * @param queClassTeaId 班级老师编号(系统的默认是0,当不等于0时，表示该题为班内老师上传)
	 * @return
	 * @throws WEBException
	 */
	Integer addLoreQuestion(Integer loreId,String loreTypeName,Integer queNum,
			String queTitle,String queSub,String queAnswer,Integer queTipId,Integer lexId,String queResolution,String queType,
			Integer queOrder,String queType2,String a,String b,String c,String d,String e,String f,
			String operateUserName,String operateDate,Integer queClassTeaId)throws WEBException;
	
	/**
	 * 修改指定题库（巩固训练,针对性诊断,再次诊断）的内容
	 * @author wm
	 * @date 2019-5-7 上午11:49:25
	 * @param id
	 * @param queSub 题干
	 * @param queAnswer 答案
	 * @param queTips 提示(lore_question_sub表的主键)
	 * @param lexId 词库编号
	 * @param queResolution 解析
	 * @param queType 题型一[单选题、多选题（答案无序）、问答题、判断题、填空题，填空选择题（答案有序）]
	 * @param queType2 题型二(了解，理解，应用)
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @param operateUserName 操作人员姓名
	 * @param operateDate 操作时间
	 * @return
	 * @throws WEBException
	 */
	boolean updateLoreQuestion(Integer id,String queSub,String queAnswer,Integer queTipId,Integer lexId,String queResolution,String queType,
			String queType2,String a,String b,String c,String d,String e,String f,
			String operateUserName,String operateDate)throws WEBException;
	
	/**
	 * 增加解题示范、知识讲解内容
	 * @author wm
	 * @date 2019-5-8 上午10:01:18
	 * @param loreId 知识点编号
	 * @param loreType 知识点类型（解题示范、知识讲解）
	 * @param queTitle 题库标题
	 * @param queSub 题干
	 * @param queAnswer 答案（解题示范）(知识讲解是为视频地址)
	 * @param queResolution 解析（解题示范）
	 * @param queOrder 排序（解题示范,知识讲解是为1）
	 * @param operateUserName 操作人
	 * @param operateDate 操作日期
	 * @return
	 * @throws WEBException
	 */
	Integer addSimpleLoreQuestion(Integer loreId,String loreType,String queTitle,String queSub,Integer queOrder, String queAnswer,String queResolution,
			String operateUserName,String operateDate)throws WEBException;
	
	/**
	 * 修改解题示范、知识讲解内容
	 * @author wm
	 * @date 2019-5-8 上午10:11:54
	 * @param lqId 题库编号
	 * @param queSub 题干
	 * @param queAnswer 答案（解题示范）""不修改
	 * @param queResolution 解析（解题示范）""不修改
	 * @param videoPath 视频地址（知识讲解）""不修改
	 * @param operateUserName 操作人
	 * @param operateDate 操作日期
	 * @return
	 */
	boolean updateSimpleLoreQuestionByLqId(Integer lqId,String queSub,String queAnswer,String queResolution,
			String videoPath,String operateUserName,String operateDate);
	
	/**
	 * 增加知识清单，点拨指导题库信息
	 * @author wm
	 * @date 2019-5-8 上午10:09:15
	 * @param loreId 知识点编号
	 * @param loreType 知识点类型（解题示范、知识讲解）
	 * @param queSub 题干
	 * @param queTitle 标题
	 * @param operateUserName 操作人
	 * @param operateDate 操作日期
	 * @return
	 * @throws WEBException
	 */
	Integer addSimpleLoreQuestion(Integer loreId,String loreType,String queTitle,String queSub,String operateUserName,String operateDate)throws WEBException;
	
	/**
	 * 修改指定编号的知识清单、点拨指导题库信息
	 * @author wm
	 * @date 2019-5-8 上午10:26:58
	 * @param lqId 主键
	 * @param loreId 知识点编号
	 * @param loreType 知识点类型（解题示范、知识讲解）
	 * @param queSub 题干
	 * @param queTitle 标题
	 * @param operateUserName 操作人
	 * @param operateDate 操作日期
	 * @return
	 * @throws WEBException
	 */
	boolean updateSimpleLoreQuestionByLqId(Integer lqId,Integer loreId,String loreType,String queTitle,String queSub,String operateUserName,String operateDate)throws WEBException;
	
	/**
	 * 增加知识清单、点拨指导题库子表信息
	 * @author wm
	 * @date 2019-5-8 上午09:44:25
	 * @param loreQuestionId 知识点编号
	 * @param loreType 知识点类型（知识清单,点拨指导）
	 * @param queTitle 题库标题
	 * @param queSub 题干
	 * @param order 排序
	 * @param operateUserName 操作人
	 * @param operateDate 操作时间
	 * @param queAnswer
	 * @return
	 */
	Integer addLoreQuestionSubInfo(Integer loreQuestionId,String loreType,String queTitle,String queSub,Integer order, String operateUserName,String operateDate)throws WEBException;
	
	/**
	 * 修改指定编号的知识点题库子表的信息
	 * @author wm
	 * @date 2019-5-8 上午10:32:44
	 * @param lqsId 编号
	 * @param queTitle 题库标题
	 * @param queSub 题干
	 * @param operateUserName 操作人
	 * @param operateDate 操作时间
	 * @return
	 * @throws WEBException
	 */
	boolean updateLoreQuestionSubByLqsId(Integer lqsId,String queTitle,String queSub,String operateUserName,String operateDate)throws WEBException;
	
	/**
	 * 删除指定题库子表信息
	 * @author wm
	 * @date 2019-5-8 上午10:38:09
	 * @param lqsId
	 * @return
	 * @throws WEBException
	 */
	boolean delLoreQuestionSubByLqsId(Integer lqsId)throws WEBException;
	
	
	/**
	 * 修改指定题库的有/无效状态
	 * @author wm
	 * @date 2019-5-7 上午11:56:14
	 * @param id 主键
	 * @param inUse （0：有效，1：无效）
	 * @param operateUserName 操作人员姓名
	 * @param operateDate 操作时间
	 * @return
	 * @throws WEBException
	 */
	boolean updateInUseStatusById(Integer id,Integer inUse,String operateUserName,String operateDate)throws WEBException;
	
	
	/**
	 * 根据知识点编号、知识点类型(可为空),有效状态,获取题库记录列表
	 * @author wm
	 * @date 2019-5-7 上午11:51:51
	 * @param loreId 知识点编号
	 * @param loreType 知识点类型(""表示全部，知识清单,点拨指导,解题示范,巩固训练,针对性诊断,再次诊断,知识讲解)
	 * @param inUse 有效状态(-1：表示全部,0：有效，1：无效)
	 * @return
	 * @throws WEBException
	 */
	List<LoreQuestion> listInfoByLoreId(Integer loreId,String loreType,Integer inUse)throws WEBException;
	
	/**
	 * 分页获取指定知识点下的题库列表
	 * @author wm
	 * @date 2019-5-7 上午11:53:05
	 * @param loreId 知识点编号
	 * @return
	 * @throws WEBException
	 */
	List<LoreQuestion> listPageInfoByLoreId(Integer loreId,Integer pageNo, Integer pageSize)throws WEBException;
	
	/**
	 * 根据知识点编号获取题录记录条数
	 * @author wm
	 * @date 2019-5-7 上午11:53:37
	 * @param loreId 知识点编号
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByLoreId(Integer loreId)throws WEBException;
	
	/**
	 * 根据题库编号获取指定题库详情
	 * @author wm
	 * @date 2019-5-7 上午11:54:35
	 * @param id 题库编号
	 * @return
	 * @throws WEBException
	 */
	LoreQuestion getEntityById(Integer id)throws WEBException;
	
	/**
	 * 根据知识点题库编号获取知识点题库子表信息列表
	 * @author wm
	 * @date 2019-5-8 上午08:27:50
	 * @param lqId 题库编号
	 * @return
	 * @throws WEBException
	 */
	LoreQuestionSubInfo listLQSInfoByLqId(Integer lqId)throws WEBException;
	
	
}
