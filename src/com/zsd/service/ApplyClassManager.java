package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.ApplyClassInfo;

public interface ApplyClassManager {

	/**
	 * 增加老师接班信息表
	 * @author wm
	 * @date 2019-6-22 上午09:36:49
	 * @param userId 用户编号
	 * @param classId 班级编号
	 * @param classDetail 申请时的班级详细信息（年级班级学科）
	 * @param checkUserId 原班老师
	 * @param applyOpt 申请状态（0：临时，1：永久）
	 * @return
	 * @throws WEBException
	 */
	Integer addApplyClassInfo(Integer userId,Integer classId,String classDetail,Integer checkUserId,Integer applyOpt) throws WEBException;
	
	/**
	 * 审核老师接班信息
	 * @author wm
	 * @date 2019-6-22 上午09:37:12
	 * @param id 主键编号
	 * @param checkStatus 审核状态（1：同意，2：拒绝）
	 * @param checkRemark 备注
	 * @return
	 * @throws WEBException
	 */
	boolean setCancleInfo(Integer id,Integer checkStatus,String checkRemark) throws WEBException;
	
	/**
	 * 根据申请老师编号、被申请老师编号、审核状态分页获取信息列表
	 * @author wm
	 * @date 2019-6-22 上午09:38:28
	 * @param userId 申请老师用户编号（0表示全部）
	 * @param toUserId 被接管班级老师编号（0表示全部）
	 * @param checkStatus 审核状态(0:未处理，1：同意，2：拒绝)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 * @throws WEBException
	 */
	List<ApplyClassInfo> listPageInfoByOpt(Integer userId,Integer toUserId,Integer checkStatus,String sDate,String eDate,Integer pageNo,Integer pageSize) throws WEBException;
	
	/**
	 * 根据申请老师编号、被申请老师编号、审核状态获取信息记录条数
	 * @author wm
	 * @date 2019-7-27 上午11:35:01
	 * @param userId 申请老师用户编号（0表示全部）
	 * @param toUserId 被接管班级老师编号（0表示全部）
	 * @param checkStatus 审核状态(-1:全部,0:未处理，1：同意，2：拒绝)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByOpt(Integer userId,Integer toUserId,Integer checkStatus,String sDate,String eDate) throws WEBException;
	
	/**
	 * 获取未处理的接班申请列表
	 * @author wm
	 * @date 2019-7-27 上午11:59:31
	 * @param toUserId 被申请接班的老师编号
	 * @param classId 班级编号(0时不查询)
	 * @return
	 * @throws WEBException
	 */
	List<ApplyClassInfo> listMyUnCheckApplyInfo(Integer toUserId,Integer classId) throws WEBException;
	
	/**
	 * 获取指定编号的实体信息
	 * @author wm
	 * @date 2019-7-29 上午09:40:17
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	ApplyClassInfo getEntityById(Integer id) throws WEBException;
	
	/**
	 * 获取平台所有未处理的申请列表
	 * @author wm
	 * @date 2019-7-31 上午09:56:55
	 * @return
	 * @throws WEBException
	 */
	List<ApplyClassInfo> listAllUnCheckApplyInfo() throws WEBException;
	
	/**
	 * 获取指定班级指定申请老师成功被申请的记录
	 * @author wm
	 * @date 2019-8-28 下午05:44:56
	 * @param applyUserId 申请老师
	 * @param classId 接管班级
	 * @return
	 * @throws WEBException
	 */
	ApplyClassInfo getEntityByOpt(Integer applyUserId,Integer classId) throws WEBException;
	
	/**
	 * 获取指定条件的接班申请列表
	 * @author wm
	 * @date 2019-8-29 下午05:40:21
	 * @param applyUserId 申请老师(0表示不查询)
	 * @param toUserId 被申请接班老师(0表示不查询)
	 * @param classId 接管班级(0表示不查询)
	 * @param checkStatus 审核状态(-1：全部,0:未处理，1：同意，2：拒绝)
	 * @return
	 * @throws WEBException
	 */
	List<ApplyClassInfo> listInfoByOpt(Integer applyUserId,Integer toUserId,Integer classId,Integer checkStatus) throws WEBException;
}
