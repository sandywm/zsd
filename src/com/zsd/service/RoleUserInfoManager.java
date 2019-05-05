package com.zsd.service;

import com.zsd.exception.WEBException;

public interface RoleUserInfoManager {
	/**
	 * 添加角色用户信息
	 * @author zong
	 * @date  2019-5-4 上午11:03:23
	 * @param userId 用户编号
	 * @param roleId 角色编号
	 * @return  角色用户主键值
	 * @throws WEBException
	 */
	Integer addRoleUserInfo(Integer userId,Integer roleId)throws WEBException;
	
	/**
	 * 修改角色用户信息
	 * @author zong
	 * @date  2019-5-4 上午11:04:55
	 * @param id 角色用户编号
	 * @param userId 用户编号
	 * @param roleId 角色编号
	 * @return 
	 * @throws WEBException
	 */
	boolean updateRoleUserById(Integer id,Integer userId,Integer roleId)throws WEBException;
	
	
}
