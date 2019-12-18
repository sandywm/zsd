package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.ClassInfo;

public interface ClassInfoManager {
	/**
	 * 添加班级信息
	 * @author zong
	 * 2019-5-7上午09:26:07
	 * @param scId 学校编号
	 * @param className 班级名称
	 * @param buildeClassDate 创建时间
	 * @return
	 * @throws WEBException
	 */
	Integer addClassInfo(Integer scId,String className, String buildeClassDate)throws WEBException;
	
	
	/**
	 * 通过指定学校指定年级(通过当前时间换算)的班级名称获取该班级信息
	 * @author zong
	 * @date  2019-5-6 上午08:48:37
	 * @param gradeId 年级编号
	 * @param currentTime 当前时间
	 * @param schoolId 学校编号
	 * @param className 班级名称
	 * @return 班级实体
	 * @throws WEBException
	 */
	List<ClassInfo> listClassInfoByOption( Integer gradeId,
			String currentTime, Integer schoolId, String className)throws WEBException;
	/**
	 * 根据主键获取班级信息
	 * @author zong
	 * 2019-5-11上午11:39:51
	 * @param cId 主键
	 * @return 班级信息
	 * @throws WEBException
	 */
	List<ClassInfo> listClassInfoById( Integer cId)throws WEBException;
	
	/**
	 * 根据学校编号，班级名称，创建班级时间获取该班级信息
	 * @author sandy
	 * @date 2019-12-18 上午08:50:23
	 * @param schoolId 学校编号
	 * @param className 班级名称
	 * @param buildClassDate 班级创建时间
	 * @return
	 * @throws WEBException
	 */
	List<ClassInfo> listClassInfoByOption(Integer schoolId, String className, String buildClassDate)throws WEBException;
}
