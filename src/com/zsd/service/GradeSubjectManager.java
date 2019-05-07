package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.GradeSubject;

public interface GradeSubjectManager {

	/**
	 * @description 增加年级学科
	 * @author wm
	 * @date 2019-4-28 下午09:53:07 
	 * @param gName 年级名称
	 * @param subId 学科编号
	 * @param schoolType 学段编号
	 * @return
	 * @throws WEBException
	 */
	Integer addGSub(String gName,Integer subId,Integer schoolType) throws WEBException;
	
	/**
	 * @description 修改指定编号的信息
	 * @author wm
	 * @date 2019-4-28 下午09:53:35 
	 * @param id 主键
	 * @param gName 年级名称
	 * @param subId 学科编号
	 * @param schoolType 学段编号
	 * @param showStatus 显示状态
	 * @return
	 * @throws WEBException
	 */
	boolean updateGSub(Integer id,String gName,Integer subId,Integer schoolType,Integer showStatus) throws WEBException;
	
	/**
	 * 获取指定编号的年级学科详情
	 * @author wm
	 * @date 2019-4-28 下午10:35:57 
	 * @param id 主键
	 * @return
	 * @throws WEBException
	 */
	List<GradeSubject> listSpecInfoById(Integer id) throws WEBException;
	
	/**
	 * @description 根据条件分页显示年级学科列表
	 * @author wm
	 * @date 2019-4-28 下午09:56:25 
	 * @param gName 年级名称(""表示全部)
	 * @param subId 学科编号（0表示全部）
	 * @param schoolType 学段编号（0：全部,1：小学,2：初中,3：高中）
	 * @param showStatus 显示状态（-1：全部,0：显示，1：隐藏）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<GradeSubject> listPageInfoByOpt(String gradeName,Integer subId,Integer shoolType,
			Integer showStatus,Integer pageNo,Integer pageSize) throws WEBException;
	
	/**
	 * 根据条件获取年级学科记录条数
	 * @author wm
	 * @date 2019-4-28 下午09:57:11 
	 * @param gName 年级名称(""表示全部)
	 * @param subId 学科编号（0表示全部）
	 * @param schoolType 学段编号（0：全部,1：小学,2：初中,3：高中）
	 * @param showStatus 显示状态（-1：全部,0：显示，1：隐藏）
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByOpt(String gradeName,Integer subId,Integer shoolType,Integer showStatus) throws WEBException;
	
	/**
	 * 通过学科获取学科年级列表
	 * @author wm
	 * @date 2019-4-28 下午11:11:20 
	 * @param subId 学科编号
	 * @return
	 * @throws WEBException
	 */
	List<GradeSubject> listSpecInfoBySubId(Integer subId) throws WEBException;
	
	/**
	 * 根据年级、学科编号、学段获取年级学科列表
	 * @author Administrator
	 * @date 2019-5-5 上午10:11:48
	 * @param gradeName 年级名称
	 * @param subId 学科编号
	 * @param shoolType 学段
	 * @return
	 * @throws WEBException
	 */
	List<GradeSubject> listSpecInfoByOpt(String gradeName,Integer subId,Integer shoolType) throws WEBException;
	
	/**
	 * 根据年级名称获取年级学科列表
	 * @author wm
	 * @date 2019-5-7 上午09:55:08
	 * @param gradeName 年级名称
	 * @return
	 * @throws WEBException
	 */
	List<GradeSubject> listSpecInfoByGname(String gradeName) throws WEBException;
}
