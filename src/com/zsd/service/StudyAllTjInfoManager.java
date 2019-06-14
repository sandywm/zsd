package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.StudyAllTjInfo;

/** 
 * @author zong
 * @version 2019-6-10 上午08:39:14
 */
public interface StudyAllTjInfoManager {
	/**
	 * 根据科目编号,开始,结束时间获取全平台知识点记录
	 * @author zong
	 * 2019-6-10上午10:46:41
	 * @param sess
	 * @param subId 科目编号
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 * @throws WEBException
	 */
	List<StudyAllTjInfo> findInfoByOpt(Integer subId,String startTime,String endTime)throws WEBException;
	/**
	 * 根据学科编号  学习时间获取全平台知识点记录
	 * @author zong
	 * 2019年6月14日上午8:42:29
	 * @param studyDate 学习时间
	 * @param subId 学科编号
	 * @return
	 * @throws WEBException
	 */
	List<StudyAllTjInfo> listInfoByOption(String studyDate, Integer subId)throws WEBException ;
	/**
	 * 根据主键修改全平台学习统计记录
	 * @author zong
	 * 2019年6月14日上午8:43:53
	 * @param id
	 * @param questionType 题目类型（了解、理解、应用）
	 * @param liaojieSuccFlag 正确标记
	 * @param lijieSuccFlag 正确标记
	 * @param yySuccFlag 正确标记
	 * @return
	 * @throws WEBException
	 */
	boolean updateSATById(Integer id, String questionType, boolean liaojieSuccFlag,boolean lijieSuccFlag, boolean yySuccFlag) throws WEBException ;
	/**
	 * 添加全平台学习统计记录
	 * @author zong
	 * 2019年6月14日上午8:50:46
	 * @param studyDate 学习时间
	 * @param subId 学科编号
	 * @param questionType  题目类型
	 * @param result 
	 * @return
	 * @throws WEBException
	 */
	Integer addSAT(String studyDate, Integer subId,String questionType, Integer result)throws WEBException; 
}
