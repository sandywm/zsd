package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.StuSubjectEduInfo;

public interface StuSubjectEduManager {

	/**
	 * 增加学生学科教材信息
	 * @author wm
	 * @date 2019-5-24 下午05:21:35
	 * @param stuId 学生编号
	 * @param subId 学科编号
	 * @param eduId 教材编号
	 * @return
	 * @throws WEBException
	 */
	Integer addSSE(Integer stuId,Integer subId,Integer eduId) throws WEBException;
	
	/**
	 * 修改学生学科教材信息
	 * @author wm
	 * @date 2019-5-24 下午05:21:57
	 * @param sseId 主键
	 * @param eduId 教材编号
	 * @return
	 * @throws WEBException
	 */
	boolean updateSSEById(Integer sseId,Integer eduId) throws WEBException;
	
	/**
	 * 根据学生编号、学科编号获取学生学科教材信息列表
	 * @author wm
	 * @date 2019-5-24 下午05:22:20
	 * @param stuId 学生编号
	 * @param subId 学科编号
	 * @return
	 * @throws WEBException
	 */
	List<StuSubjectEduInfo> listInfoByOpt(Integer stuId,Integer subId) throws WEBException;
}
