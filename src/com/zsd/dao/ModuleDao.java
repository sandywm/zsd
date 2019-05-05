package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.ModuleInfo;

public interface ModuleDao {
	/**
	 * 根据主键加载模块信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的模块信息的主键值
	 * @return 加载的模块信息PO
	 */
	ModuleInfo get(Session sess,int id);
	
	/**
	 * 保存模块信息信息实体，新增一条模块信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param mod 保存的模块信息实例
	 */
	void save(Session sess,ModuleInfo mod);
	
	/**
	 * 删除模块信息实体，删除一条模块信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param mod 删除的模块信息实体
	 */
	void delete(Session sess,ModuleInfo mod);
	
	/**
	 * 根据主键删除模块信息实体，删除一条模块信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除模块信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 获取全平台所有模块列表
	 * @author Administrator
	 * @date 2019-5-5 下午03:48:10
	 * @param sess
	 * @return
	 */
	List<ModuleInfo> findAllInfo(Session sess);
}
