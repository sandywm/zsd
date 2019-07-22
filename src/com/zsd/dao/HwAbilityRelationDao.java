package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.HwAbilityRelationInfo;

public interface HwAbilityRelationDao {
	
	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-7-22 上午09:12:39
	 * @param sess
	 * @param id
	 * @return
	 */
	HwAbilityRelationInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加系统家庭作业能力类型关联信息
	 * @author wm
	 * @date 2019-7-22 上午09:12:47
	 * @param sess
	 * @param hmr
	 */
	void save(Session sess,HwAbilityRelationInfo hmr);
	
	/**
	 * 修改系统家庭作业能力类型关联信息
	 * @author wm
	 * @date 2019-7-22 上午09:13:06
	 * @param sess
	 * @param hmr
	 */
	void update(Session sess,HwAbilityRelationInfo hmr);
	
	/**
	 * 根据条件获取系统家庭作业能力类型关联信息列表
	 * @author wm
	 * @date 2019-7-22 上午09:13:13
	 * @param sess
	 * @param hwId 系统家庭作业编号(0表示全部)
	 * @param batId 能力类型编号(0表示全部)
	 * @return
	 */
	List<HwAbilityRelationInfo> findInfoByOpt(Session sess,Integer hwId,Integer batId);
}
