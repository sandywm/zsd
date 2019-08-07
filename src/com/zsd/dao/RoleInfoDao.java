package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.RoleInfo;

public interface RoleInfoDao {

	/**
	 * 根据主键加载角色信息实体
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 角色主键值
	 * @return 角色信息PO
	 */
	 RoleInfo get(Session sess,int id);
	
	/**
	 * 保存角色信息信息实体，新增一条角色信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param roleInfo 保存的角色信息实例
	 */
	void save(Session sess,RoleInfo roleInfo);
	
	/**
	 * 删除角色信息实体，删除一条角色信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param roleInfo 删除的角色信息实体
	 */
	void delete(Session sess,RoleInfo roleInfo);
	
	/**
	 * 根据主键删除角色信息实体，删除一条角色信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除角色信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条角色信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param roleInfo 需要更新的角色信息
	 */
	void update(Session sess,RoleInfo roleInfo);
	
	/**
	 * 获取所有角色列表
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:20:36
	 * @param sess
	 * @return
	 */
	List<RoleInfo> findRoleInfo(Session sess);
	/**
	 * 根据角色名称获取列表
	 * @author zong
	 * @date  2019-5-5 下午04:00:35
	 * @param sess
	 * @param roleName 角色名称
	 * @return
	 */
	List<RoleInfo> findRoleInfo(Session sess,String roleName);
	
	/**
	 * 根据角色编号获取角色信息
	 * @author wm
	 * @date 2019-8-7 下午06:49:13
	 * @param sess
	 * @param roleId
	 * @return
	 */
	RoleInfo getEntityById(Session sess,int roleId);
}
