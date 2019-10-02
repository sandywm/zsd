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
	
	/**
	 * 根据条件获取订单列表
	 * @author wm
	 * @date 2019-9-28 上午10:21:04
	 * @param userId 学生编号
	 * @param sDate 开始日期
	 * @param eDate 结束日期
	 * @param comSta 完成状态（-1：全部,0:未完成,1:已完成,2:已取消）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<StudentPayOrderInfo> listOrderPageInfoByOpt(Integer userId,String sDate,String eDate,Integer comSta,Integer pageNo,Integer pageSize)throws WEBException;
	
	/**
	 * 根据条件获取订单记录条数
	 * @author wm
	 * @date 2019-9-28 上午10:22:11
	 * @param userId 学生编号
	 * @param sDate 开始日期
	 * @param eDate 结束日期
	 * @param comSta 完成状态（-1：全部,0:未完成,1:已完成,2:已取消）
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByOpt(Integer userId,String sDate,String eDate,Integer comSta)throws WEBException;
	
	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-9-28 下午02:37:40
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	StudentPayOrderInfo getEntityById(Integer id)throws WEBException;
	
	/**
	 * 增加订单
	 * @author wm
	 * @date 2019-9-28 下午03:23:52
	 * @param stuId 学生编号
	 * @param orderNo 订单号
	 * @param payType 付款类型1(微信),2(支付宝)
	 * @param payMoney 付款金额
	 * @param ntsId 老师学生绑定关系编号
	 * @param buyMonth 购买月份
	 * @param orderDetail 费用详情
	 * @return
	 * @throws WEBException
	 */
	Integer addOrder(Integer stuId,String orderNo,Integer payType,Integer payMoney,Integer ntsId,Integer buyMonth,String orderDetail)throws WEBException;
	
	/**
	 * 
	 * @author wm
	 * @date 2019-9-28 下午03:26:01
	 * @param id 主键
	 * @param payType 付款类型1(微信),2(支付宝)，0不修改
	 * @param payMoney 付款金额 （0不修改）
	 * @param buyMonth 购买月份（0不修改）
	 * @param orderDetail 费用详情（""不修改）
	 * @param comStatus 完成状态 (-1不修改)
	 * @param comDate 完成时间 （""不修稿）
	 * @param payUserId 购买人 （0不修改）
	 * @param payUserRoleId 购买人角色（0不修改）
	 * @return
	 * @throws WEBException
	 */
	boolean updateOrderById(Integer id,Integer payType,Integer payMoney,Integer buyMonth,String orderDetail,Integer comStatus,String comDate,
			Integer payUserId,Integer payUserRoleId)throws WEBException;
	
	/**
	 * 根据条件获取未完成订单
	 * @author wm
	 * @date 2019-10-2 下午05:01:03
	 * @param userId 用户编号
	 * @param ntsId 绑定导师关系编号(大于0表示购买的导师费，等于0表示购买的会员费)
	 * @return
	 * @throws WEBException
	 */
	List<StudentPayOrderInfo> listUnComInfoByOpt(Integer userId,Integer ntsId)throws WEBException;
}
