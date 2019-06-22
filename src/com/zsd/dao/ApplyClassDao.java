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
	 * 根据老师编号、有效状态获取信息列表
	 * @author wm
	 * @date 2019-6-21 下午07:00:14
	 * @param sess
	 * @param userId 老师编号
	 * @param validStatus 有效状态(0:正在接班,1:被取消)
	 * @return
	 */
	List<ApplyClassInfo> findInfoByOpt(Session sess,Integer userId,Integer validStatus);
}
