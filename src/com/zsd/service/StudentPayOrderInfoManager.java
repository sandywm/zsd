package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.StudentPayOrderInfo;

/** 
 * @author zong
 * @version 2019-5-25 上午10:20:53
 */
public interface StudentPayOrderInfoManager {
	/**
	 * 根据绑定关系,完成状态获取学生购买订单信息
	 * @author zong
	 * 2019-5-25上午10:21:36
	 * @param ntsId 老师学生绑定主键(0时不查询)
	 * @param comSta 完成状态(-1时不查询)
	 * @return
	 * @throws WEBException
	 */
	List<StudentPayOrderInfo> findSpayOrderInfoByOpt(Integer ntsId, Integer comSta)throws WEBException;
	/**
	 * 根据绑定关系,完成状态分页获取学生购买订单信息
	 * @author zong
	 * 2019-5-27下午04:49:49
	 * @param ntsId 网络导师学生绑定主键
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<StudentPayOrderInfo> listSpayOrderInfoByOpt(Integer ntsId, Integer pageNo, Integer pageSize)throws WEBException;
	/**
	 * 根据绑定关系,获取学生购买订单记录数
	 * @author zong
	 * 2019-5-27下午04:50:17
	 * @param ntsId
	 * @return
	 * @throws WEBException
	 */
	Integer getspOrderInfoCount(Integer ntsId)throws WEBException;
	
	/**
	 * 删除所有未完成的订单(定时操作用)
	 * @author wm
	 * @date 2019-8-30 上午11:24:35
	 * @throws WEBException
	 */
	void delBatchUnComPayOrder()throws WEBException;
	
	/**
	 * 删除指定未完成的订单
	 * @author wm
	 * @date 2019-8-30 上午11:25:38
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	boolean delSpecUnComPayOrder(Integer id)throws WEBException;
}
