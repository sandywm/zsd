package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.HwQueInfo;

public interface HwQueManager {

	/**
	 * 增加系统家庭作业
	 * @author wm
	 * @date 2019-7-22 上午11:51:10
	 * @param btId 自助餐类型编号
	 * @param loreId 知识点编号(通用版)
	 * @param num 题库顺序
	 * @param title 标题
	 * @param subject 题干(包括选项)
	 * @param answer 答案
	 * @param resolution 解析
	 * @param queType 题型一
	 * @param orders 排序
	 * @param operateUserName 操作人员姓名
	 * @return
	 * @throws WEBException
	 */
	Integer addHW(Integer btId,Integer loreId,Integer num,String title,String subject,String answer,
			String resolution,String queType,Integer orders,
			String operateUserName) throws WEBException;
	
	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-7-22 上午11:52:40
	 * @param hwId 主键
	 * @return
	 * @throws WEBException
	 */
	HwQueInfo getEntityById(Integer hwId) throws WEBException;
	
	/**
	 * 根据条件获取系统家庭作业记录列表
	 * @author wm
	 * @date 2019-7-22 上午11:53:08
	 * @param loreId 知识点编号（通用）
	 * @param btId 自助餐类型编号（0表示全部）
	 * @param inUse 有效状态(-1：表示全部,0：有效，1：无效)
	 * @param currNumFlag 是否获取当前类型下最大的题序号和排序号（true:是,false的时候按照order升序）
	 * @return
	 * @throws WEBException
	 */
	List<HwQueInfo> listInfoByOpt(Integer loreId,Integer btId,Integer inUse,boolean currNumFlag) throws WEBException;
	
	/**
	 * 根据知识点编号分页获取系统家庭作业列表
	 * @author wm
	 * @date 2019-7-22 上午11:53:54
	 * @param loreId 知识点编号（通用）
	 * @param pageNo 页码
	 * @param pageSize 每页记录条数
	 * @return
	 * @throws WEBException
	 */
	List<HwQueInfo> listPageInfoByLoreId(Integer loreId,Integer pageNo,Integer pageSize) throws WEBException;
	
	/**
	 * 根据知识点编号获取系统家庭作业记录
	 * @author wm
	 * @date 2019-7-22 上午11:57:50
	 * @param loreId 知识点编号（通用）
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByLoreId(Integer loreId) throws WEBException;
	
	/**
	 * 修改指定家庭作业的有效状态
	 * @author wm
	 * @date 2019-7-22 上午11:58:11
	 * @param id 主键
	 * @param inUse 有效状态(-1：表示全部,0：有效，1：无效)
	 * @param operateUserName 操作人员
	 * @return
	 * @throws WEBException
	 */
	boolean updateInUseById(Integer id,Integer inUse,String operateUserName) throws WEBException;
	
	/**
	 * 修改指定家庭作业基本信息
	 * @author wm
	 * @date 2019-7-22 上午11:58:46
	 * @param id 主键
	 * @param queSub 题干
	 * @param queAnswer 答案
	 * @param queResolution 解析
	 * @param queType 类型
	 * @param operateUserName 操作人员
	 * @return
	 * @throws WEBException
	 */
	boolean updateInfoById(Integer id,String queSub,String queAnswer,String queResolution,String queType,
			String operateUserName)throws WEBException;
	
	/**
	 * 根据知识点编号，自助餐类型名称获取系统家庭作业记录列表
	 * @author wm
	 * @date 2019-7-22 上午11:59:23
	 * @param loreId 知识点编号
	 * @param buffetTypeName 自助餐类型名称
	 * @return
	 * @throws WEBException
	 */
	List<HwQueInfo> listInfoByLoreAndBuffetType(Integer loreId,String buffetTypeName)throws WEBException;
}
