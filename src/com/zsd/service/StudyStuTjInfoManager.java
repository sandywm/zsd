package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.StudyStuTjInfo;

/** 
 * @author zong
 * @version 2019-6-10 上午08:38:55
 */
public interface StudyStuTjInfoManager {
	/**
	 * 根据用户编号,科目编号,开始,结束时间获取学生学习统计记录
	 * @author zong
	 * 2019-6-10上午10:15:13
	 * @param userId 用户编号
	 * @param subId 科目编号
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 * @throws WEBException
	 */
	List<StudyStuTjInfo> listInfoByOpt(Integer userId,Integer subId,String startTime,String endTime)throws WEBException;
	/**
	 * 根据学生编号,学科编号,学习时间获取学生学习统计记录
	 * @author zong
	 * 2019年6月13日下午5:56:51
	 * @param userId 学生编号
	 * @param subId 学科编号
	 * @param studyDate 学习时间
	 * @return
	 * @throws WEBException
	 */
	List<StudyStuTjInfo> listInfoByOption(Integer userId,Integer subId,String studyDate)throws WEBException;
	/**
	 *  根据主键修改学习统计记录
	 * @author zong
	 * 2019年6月14日上午8:15:29
	 * @param id
	 * @param questionType 类型(了解、理解、应用)
	 * @param liaojieSuccFlag 当前了解正确标记
	 * @param lijieSuccFlag 当前理解正确标记
	 * @param yySuccFlag 当前应用正确标记
	 * @return
	 * @throws WEBException
	 */
	boolean updateSSTById(Integer id, String questionType, boolean liaojieSuccFlag,boolean lijieSuccFlag, boolean yySuccFlag)throws WEBException; 
	/**
	 * 添加学生学习记录统计
	 * @author zong
	 * 2019年6月14日上午8:23:06
	 * @param studyDate 学习时间
	 * @param userId 用户编号
	 * @param subId 学科编号
	 * @param questionType 问题类型
	 * @param result 
	 * @return
	 * @throws WEBException
	 */
	Integer addSST(String studyDate, Integer userId, Integer subId, String questionType, Integer result)throws WEBException; 
}
