package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.UserClassInfo;

public interface UserClassInfoManager {
	/**
	 *  添加用户编辑信息（学生绑定班级）
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
	 * 获取班内老师的班级列表(包括临时接班的老师)
	 * @author wm
	 * @date 2019-6-21 下午06:45:17
	 * @param classId
	 * @param roleId
	 * @return
	 * @throws WEBException
	 */
	List<UserClassInfo> listTeaInfoByOpt(Integer classId,Integer roleId)throws WEBException;
}
