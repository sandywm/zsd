package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.ActRoleInfo;

public interface ActRoleInfoDao {
	/**
	 * 根据主键加载动作角色信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的动作角色信息的主键值
	 * @return 加载的动作角色信息PO
	 */
	ActRoleInfo get(Session sess,int id);
	
	/**
	 * 保存动作角色信息信息实体，新增一条动作角色信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param ar 保存的动作角色信息实例
	 */
	void save(Session sess,ActRoleInfo ar);
	
	/**
	 * 删除动作角色信息实体，删除一条动作角色信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param ar 删除的动作角色信息实体
	 */
	void delete(Session sess,ActRoleInfo ar);
	
	/**
	 * 根据主键删除动作角色信息实体，删除一条动作角色信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除动作角色信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 根据角色编号、模块动作编号获取动作角色列表
	 * @author Administrator
	 * @date 2019-5-5 下午04:09:54
	 * @param sess
	 * @param roleId 角色编号(0表示全部)
	 * @param actId 模块动作编号(0表示全部)
	 * @return
	 */
	List<ActRoleInfo> findInfoByOpt(Session sess,Integer roleId,Integer actId);
}
