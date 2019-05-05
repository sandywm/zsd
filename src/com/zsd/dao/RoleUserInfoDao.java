package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.RoleUserInfo;

public interface RoleUserInfoDao {
	/**
	 * 根据主键加载用户角色信息实体
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 用户角色主键值
	 * @return 用户角色信息PO
	 */
	 RoleUserInfo get(Session sess,int id);
	
	/**
	 * 保存用户角色信息信息实体，新增一条用户角色信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param ruInfo 保存的用户角色信息实例
	 */
	void save(Session sess,RoleUserInfo ruInfo);
	
	/**
	 * 删除用户角色信息实体，删除一条用户角色信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param ruInfo 删除的用户角色信息实体
	 */
	void delete(Session sess,RoleUserInfo ruInfo);
	
	/**
	 * 根据主键删除用户角色信息实体，删除一条用户角色信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除用户角色信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条用户角色信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param ruInfo 需要更新的用户角色信息
	 */
	void update(Session sess,RoleUserInfo ruInfo);
	
	/**
	 * 根据用户编号获取用户角色信息
	 * @author zong
	 * @date  2019-4-30 下午03:59:29
	 * @param sess
	 * @param userId 用户编号
	 * @return
	 */
	List<RoleUserInfo> findUserRoleInfo(Session sess,Integer userId);

}
