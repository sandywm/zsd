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
	 * @return
	 * @throws WEBException
	 */
	Integer addApplyClassInfo(Integer userId,Integer classId,String classDetail,Integer checkUserId) throws WEBException;
	
	/**
	 * 审核老师接班信息
	 * @author wm
	 * @date 2019-6-22 上午09:37:12
	 * @param id 主键编号
	 * @param checkUserId 原班老师
	 * @param checkStatus 审核状态（1：同意，2：拒绝）
	 * @param checkRemark 备注
	 * @return
	 * @throws WEBException
	 */
	boolean setCancleInfo(Integer id,Integer checkUserId,Integer checkStatus,String checkRemark) throws WEBException;
	
	/**
	 * 根据老师编号、有效状态获取信息列表
	 * @author wm
	 * @date 2019-6-22 上午09:38:28
	 * @param userId 老师用户编号
	 * @param validStatus 有效状态(0:正在接班,1:被取消)
	 * @return
	 * @throws WEBException
	 */
	List<ApplyClassInfo> listInfoByOpt(Integer userId,Integer validStatus) throws WEBException;
}
