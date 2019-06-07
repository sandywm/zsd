package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.RoleInfo;

public interface RoleInfoManager {
	/**
	 * 添加角色信息
	 * @author zong
	 * @date  2019-5-4 上午10:09:13
	 * @param roleName 角色名称
	 * @return 角色主键值
	 * @throws WEBException
	 */
	Integer addRoleInfo(String roleName) throws WEBException;

	/**
	 * 更新指定角色信息
	 * @author zong
	 * @date  2019-5-4 上午10:10:29
	 * @param id 角色编号
	 * @param roleName 角色编号
	 * @return
	 * @throws WEBException
	 */
	boolean updateUser(Integer id, String roleName) throws WEBException;
	/**
	 * 根据角色名称获取角色信息
	 * @author zong
	 * @date  2019-5-5 下午04:03:07
	 * @param roleName 角色名称
	 * @return
	 * @throws WEBException
	 */
	public List<RoleInfo> listRoleInfo(String roleName) throws WEBException;
	/**
	 * 获取所有角色信息
	 * @author zong
	 * 2019-6-6下午05:35:53
	 * @return
	 * @throws WEBException
	 */
	public List<RoleInfo> listAllRoleInfo() throws WEBException;

}
