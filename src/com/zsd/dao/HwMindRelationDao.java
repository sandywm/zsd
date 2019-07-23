package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.HwMindRelationInfo;

public interface HwMindRelationDao {

	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-7-22 上午09:12:39
	 * @param sess
	 * @param id
	 * @return
	 */
	HwMindRelationInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加系统家庭作业思维类型关联信息
	 * @author wm
	 * @date 2019-7-22 上午09:12:47
	 * @param sess
	 * @param hmr
	 */
	void save(Session sess,HwMindRelationInfo hmr);
	
	/**
	 * 修改系统家庭作业思维类型关联信息
	 * @author wm
	 * @date 2019-7-22 上午09:13:06
	 * @param sess
	 * @param hmr
	 */
	void update(Session sess,HwMindRelationInfo hmr);
	
	/**
	 * 删除指定家庭作业思维类型关联信息
	 * @author wm
	 * @date 2019-7-23 上午11:05:27
	 * @param sess
	 * @param hmr
	 */
	void delete(Session sess,HwMindRelationInfo hmr);
	
	/**
	 * 根据条件获取系统家庭作业思维类型关联信息列表
	 * @author wm
	 * @date 2019-7-22 上午09:13:13
	 * @param sess
	 * @param hwId 系统家庭作业编号(0表示全部)
	 * @param bmtId 思维类型编号(0表示全部)
	 * @return
	 */
	List<HwMindRelationInfo> findInfoByOpt(Session sess,Integer hwId,Integer bmtId);
	
}
