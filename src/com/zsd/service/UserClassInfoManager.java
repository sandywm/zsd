package com.zsd.service;

import java.util.List;


import com.zsd.exception.WEBException;
import com.zsd.module.UserClassInfo;

public interface UserClassInfoManager {
	/**
	 *  添加用户班级信息（学生绑定班级）
	 * @author zong
	 * @date  2019-5-5 下午04:53:49
	 * @param userId 用户编号
	 * @param classId 班级编号
	 * @param roleId 角色编号
	 * @return 用户班级主键值
	 * @throws WEBException
	 */
	Integer addUcInfo(Integer userId,Integer classId,Integer roleId) throws WEBException;
	/**
	 * 添加用户班级信息（班内老师绑定班级）
	 * @author zdf
	 * 2019-8-6 上午10:06:05
	 * @param userId 用户编号
	 * @param classId 班级编号
	 * @param roleId 角色编号
	 * @param subId 科目编号
	 * @param subName 科目名称
	 * @return
	 * @throws WEBException
	 */
	Integer addUcInfo(Integer userId,Integer classId,Integer roleId,Integer subId,String subName) throws WEBException;
	/**
	 * 根据用户编号获取用户班级信息
	 * @author zong
	 * 2019-5-21下午05:40:23
	 * @param userId 用户编号
	 * @return
	 * @throws WEBException
	 */
	List<UserClassInfo> listUcInfoByUserId(Integer userId)throws WEBException;
	
	/**
	 * 根据用户编号，角色编号获取用户班级信息实体
	 * @author wm
	 * @date 2019-5-24 上午11:20:21
	 * @param userId 用户编号
	 * @param roleId 角色编号
	 * @return
	 * @throws WEBException
	 */
	UserClassInfo getEntityByOpt(Integer userId,Integer roleId)throws WEBException;
	
	/**
	 * 根据班级编号,角色编号获取用户班级信息
	 * @author zong
	 * 2019-6-2上午09:09:49
	 * @param sess
	 * @param classId 班级编号
	 * @param roleId 角色编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<UserClassInfo> listUcInfoByOpt(Integer classId,Integer roleId,Integer pageNo,Integer pageSize)throws WEBException;
	/**
	 * 根据班级编号,角色编号获取用户班级信息记录数
	 * @author zong
	 * 2019-6-2上午09:09:49
	 * @param sess
	 * @param classId 班级编号
	 * @param roleId 角色编号
	 * @return
	 */
	Integer getUciByOpt(Integer classId,Integer roleId)throws WEBException;
	
	/**
	 * 获取指定老师的班级列表(包括临时、永久接班的老师)
	 * @author wm
	 * @date 2019-6-21 下午06:45:17
	 * @param roleId
	 * @param userId
	 * @return
	 * @throws WEBException
	 */
	List<UserClassInfo> listTeaInfoByOpt(Integer userId,Integer roleId)throws WEBException;
	
	/**
	 * 根据主键修改班级老师、接班老师编号、接班老师信息、接班状态信息
	 * @author wm
	 * @date 2019-7-29 上午10:24:42
	 * @param id
	 * @param userId 班级老师（0不修改）
	 * @param applyUserId 接班老师编号（0不修改）
	 * @param applyUsreName 接班老师信息（""不修改）
	 * @param status 接班状态信息（0不修改）
	 * @return
	 * @throws WEBException
	 */
	boolean updateInfoByOpt(Integer id,Integer userId,Integer applyUserId,String applyUsreName,Integer status)throws WEBException;
	
	/**
	 * 根据老师编号、班级编号、角色编号获取老师班级信息表
	 * @author wm
	 * @date 2019-7-29 上午10:36:06
	 * @param userId  老师编号
	 * @param classId 班级编号
	 * @param roleId 角色编号
	 * @return
	 * @throws WEBException
	 */
	UserClassInfo getEntityByOpt(Integer userId,Integer classId,Integer roleId)throws WEBException ;
}
