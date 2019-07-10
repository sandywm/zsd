package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.BuffetQueInfo;

public interface BuffetQueInfoManager {

	/**
	 * 增加自助餐题库
	 * @author wm
	 * @date 2019-5-17 下午07:06:42
	 * @param btId 巴菲特基础类型
	 * @param loreId 知识点编号
	 * @param num 题号
	 * @param title 标题
	 * @param subject 题干
	 * @param answer 答案
	 * @param lexId 关联词库编号
	 * @param tipsId 提示编号
	 * @param resolution 解析
	 * @param queType 题干类型
	 * @param order 排序号
	 * @param answerA
	 * @param answerB
	 * @param answerC
	 * @param answerD
	 * @param answerE
	 * @param answerF
	 * @param operateUserName
	 * @param operateDate
	 * @return
	 * @throws WEBException
	 */
	Integer addBQ(Integer btId,Integer loreId,Integer num,String title,String subject,String answer,
			Integer lexId,Integer tipsId,String resolution,String queType,Integer order,
			String answerA,String answerB,String answerC,String answerD,String answerE,String answerF,
			String operateUserName,String operateDate) throws WEBException;
	
	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-5-17 下午07:08:06
	 * @param buffetId 自助餐主键编号
	 * @return
	 * @throws WEBException
	 */
	BuffetQueInfo getEntityById(Integer buffetId) throws WEBException;
	
	/**
	 * 根据知识点名称、自助餐基础类型、开启状态获取自助餐题库列表（按照order升序）
	 * @author wm
	 * @date 2019-5-17 下午07:08:26
	 * @param loreId 知识点编号
	 * @param buffetType 自助餐基础类型（0表示全部）
	 * @param inUse 开启状态（-1:表示全部，0：有效，1：无效）
	 * @return
	 * @throws WEBException
	 */
	List<BuffetQueInfo> listInfoByOpt(Integer loreId,Integer buffetType,Integer inUse)throws WEBException;
	
	/**
	 * 分页获取指定知识点目录下的自助餐题库列表
	 * @author wm
	 * @date 2019-5-17 下午07:10:15
	 * @param loreId 知识点目录编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<BuffetQueInfo> listPageInfoByLoreId(Integer loreId,int pageNo, int pageSize)throws WEBException;
	
	/**
	 * 获取指定知识点目录下的自助餐题库记录条数
	 * @author wm
	 * @date 2019-5-17 下午07:10:41
	 * @param loreId 知识点目录编号
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByLoreId(Integer loreId)throws WEBException;
	
	/**
	 * 修改自助餐题库有/无效状态
	 * @author wm
	 * @date 2019-5-17 下午07:11:09
	 * @param buffetId 自助餐编号
	 * @param inUse 开启状态（0：有效，1：无效）
	 * @param operateUserName
	 * @param operateDate
	 * @return
	 * @throws WEBException
	 */
	boolean updateInUseStatusById(Integer buffetId,Integer inUse,String operateUserName,String operateDate)throws WEBException;
	
	/**
	 * 修改自助餐的关联词条信息
	 * @author wm
	 * @date 2019-6-3 下午04:23:43
	 * @param buffetId 自助餐编号
	 * @param lexId 词条编号
	 * @return
	 * @throws WEBException
	 */
	boolean updateLexInfoById(Integer buffetId,Integer lexId)throws WEBException;
	
	/**
	 * 获取指定知识点编号和指定基础类型下的最后一个巴菲特题记录(获取下一个最大的的num和order)
	 * @author wm
	 * @date 2019-5-17 下午07:06:14
	 * @param loreId 知识点编号
	 * @param btId 基础类型编号
	 * @return 最后一个巴菲特题的num值和order
	 * @throws WEBException
	 */
	BuffetQueInfo getCurrMaxNumAndOrderByOpt(Integer loreId,Integer btId)throws WEBException;
	
	/**
	 * 修改自助餐信息
	 * @author wm
	 * @date 2019-5-23 上午11:40:33
	 * @param id 主键
	 * @param queSub 题干
	 * @param queAnswer 答案
	 * @param queTipId 提示编号()
	 * @param lexId 词库编号()
	 * @param queResolution 解析
	 * @param queType 题干类型
	 * @param a 选项A
	 * @param b 选项B
	 * @param c 选项C
	 * @param d 选项D
	 * @param e 选项E
	 * @param f 选项F
	 * @param operateUserName 修改人
	 * @param operateDate 修改时间
	 * @return
	 * @throws WEBException
	 */
	boolean updateInfoById(Integer id,String queSub,String queAnswer,Integer queTipId,Integer lexId,String queResolution,String queType,
			String a,String b,String c,String d,String e,String f,String operateUserName,String operateDate)throws WEBException;
	/**
	 * 根据知识点编号,开启状态获取自助餐信息
	 * @author zdf
	 * 2019-7-5 下午04:40:50
	 * @param loreId 知识点编号
	 * @param inUse 开启状态（0：有效，1：无效）（0：有效，1：无效）
	 * @return
	 * @throws WEBException
	 */
	List<BuffetQueInfo> listInfoByOption(Integer loreId,Integer inUse)throws WEBException;
	/**
	 * 根据知识点编号,自助餐类型获取自助餐信息
	 * @author zdf
	 * 2019-7-8 下午05:22:01
	 * @param loreId 知识点编号
	 * @param buffetTypeName 自助餐类型
	 * @return
	 */
	List<BuffetQueInfo> listInfoByLoreAndBuffetType(Integer loreId,String buffetTypeName)throws WEBException;
	
}
