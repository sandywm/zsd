package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.ApplyClassInfo;

public interface ApplyClassDao {

	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-6-21 下午06:59:33
	 * @param sess
	 * @param id
	 * @return
	 */
	ApplyClassInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加老师接班/取消班级信息
	 * @author wm
	 * @date 2019-6-21 下午06:59:44
	 * @param sess
	 * @param ac
	 */
	void save(Session sess,ApplyClassInfo ac);
	
	/**
	 * 修改老师接班/取消班级信息
	 * @author wm
	 * @date 2019-6-21 下午07:00:00
	 * @param sess
	 * @param ac
	 */
	void update(Session sess,ApplyClassInfo ac);
	
	/**
	 * 根据申请老师编号、被申请老师编号、审核状态分页获取信息列表
	 * @author wm
	 * @date 2019-6-21 下午07:00:14
	 * @param sess
	 * @param userId 申请老师用户编号（0表示全部）
	 * @param toUserId 被接管班级老师编号（0表示全部）
	 * @param checkStatus 审核状态(-1:全部,0:未处理，1：同意，2：拒绝)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 */
	List<ApplyClassInfo> findPageInfoByOpt(Session sess,Integer userId,Integer toUserId,Integer checkStatus,String sDate,String eDate,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据申请老师编号、被申请老师编号、审核状态获取信息记录条数
	 * @author wm
	 * @date 2019-7-27 下午04:12:39
	 * @param sess
	 * @param userId 申请老师用户编号（0表示全部）
	 * @param toUserId 被接管班级老师编号（0表示全部）
	 * @param checkStatus 审核状态(0:未处理，1：同意，2：拒绝)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 */
	Integer getCountByOpt(Session sess,Integer userId,Integer toUserId,Integer checkStatus,String sDate,String eDate);
	
	/**
	 * 获取未处理的接班申请列表
	 * @author wm
	 * @date 2019-7-27 下午04:14:08
	 * @param sess
	 * @param toUserId 被申请接班的老师编号
	 * @param classId 班级编号(0时不查询)
	 * @return
	 */
	List<ApplyClassInfo> findMyUnCheckApplyInfo(Session sess,Integer toUserId,Integer classId);
	
	/**
	 * 获取平台所有未处理的申请列表
	 * @author wm
	 * @date 2019-7-31 上午09:55:53
	 * @param sess
	 * @return
	 */
	List<ApplyClassInfo> findAllUnCheckApplyInfo(Session sess);
	
	/**
	 * 获取指定班级指定申请老师成功被申请的记录
	 * @author wm
	 * @date 2019-8-28 下午05:42:11
	 * @param sess
	 * @param applyUserId 申请老师
	 * @param classId 接管班级
	 * @return
	 */
	ApplyClassInfo findInfoByOpt(Session sess,Integer applyUserId,Integer classId);
	
	/**
	 * 获取指定条件的接班申请列表
	 * @author wm
	 * @date 2019-8-29 下午05:37:30
	 * @param sess
	 * @param applyUserId 申请老师(0表示不查询)
	 * @param toUserId 被申请接班老师(0表示不查询)
	 * @param classId 接管班级(0表示不查询)
	 * @param checkStatus 审核状态(-1：全部,0:未处理，1：同意，2：拒绝)
	 * @return
	 */
	List<ApplyClassInfo> findApplyInfo(Session sess,Integer applyUserId,Integer toUserId,Integer classId,Integer checkStatus);
}
