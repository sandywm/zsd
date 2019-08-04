package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.SendHwInfo;

public interface SendHwManager {

	/**
	 * 增加发送家庭作业信息
	 * @author wm
	 * @date 2019-7-28 上午09:36:25
	 * @param sendUserId 发送老师编号
	 * @param hwTitle 家庭作业标题
	 * @param loreId 对应知识点编号（出版社下）
	 * @param classId 班级编号
	 * @param className 年级班级信息
	 * @param subId 科目编号
	 * @param endDate 截止完成日期
	 * @param hwType 作业类型（1-家庭作业,2-课后复习,3-课前预习）
	 * @param sysQueIdArr 系统题库编号
	 * @param hwQueIdArr 系统家庭作业题库
	 * @param teaQueIdArr 老师增加的题库
	 * @param coin 金币奖励
	 * @param traceStatus 溯源标记(0(不溯源),1(溯源))
	 * @return
	 * @throws WEBException
	 */
	Integer addSendHw(Integer sendUserId,String hwTitle,Integer loreId,Integer classId,String className,Integer subId,
			String endDate,Integer hwType,String sysQueIdArr,String hwQueIdArr,String teaQueIdArr,Integer coin,Integer traceStatus) throws WEBException;
	
	/**
	 * 修改家庭作业信息
	 * @author wm
	 * @date 2019-7-28 上午09:38:18
	 * @param id
	 * @param coin 金币奖励（0时不修改）
	 * @param traceStatus 溯源标记（-1时不修改）
	 * @param inUse 0：有效，1：无效（-1时不修改）
	 * @return
	 * @throws WEBException
	 */
	boolean updateInfoById(Integer id,Integer coin,Integer traceStatus,Integer inUse) throws WEBException;
	
	/**
	 * 根据主键获取信息
	 * @author wm
	 * @date 2019-7-28 上午09:38:42
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	SendHwInfo getEntityById(Integer id) throws WEBException;
	
	/**
	 * 根据条件分页获取家庭作业信息列表
	 * @author wm
	 * @date 2019-7-28 上午09:38:54
	 * @param sendUserId 发送老师编号(0表示全部)
	 * @param classId 班级编号（0表示全部）
	 * @param hwType 作业类型（0全部,1-家庭作业,2-课后复习,3-课前预习）
	 * @param inUse  有效状态(-1全部,0：有效，1：无效)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @param pageFlag 分页标记(true：分页)
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<SendHwInfo> listPageInfoByOpt(Integer sendUserId,Integer classId,Integer hwType,Integer inUse,
			String sDate,String eDate,boolean pageFlag, Integer pageNo,Integer pageSize) throws WEBException;
	
	/**
	 * 根据条件获取家庭主页信息记录条数
	 * @author wm
	 * @date 2019-7-28 上午09:44:04
	 * @param sendUserId 发送老师编号(0表示全部)
	 * @param classId 班级编号（0表示全部）
	 * @param hwType 作业类型（0全部,1-家庭作业,2-课后复习,3-课前预习）
	 * @param inUse  有效状态(-1全部,0：有效，1：无效)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByOpt(Integer sendUserId,Integer classId,Integer hwType,Integer inUse,
			String sDate,String eDate) throws WEBException;
}
