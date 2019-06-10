package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.StudyMapInfo;


public interface StudyMapDao {

	/**
	 * 根据主键获取学习地图实体
	 * @author wm
	 * @date 2019-6-10 上午10:08:44
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	StudyMapInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加学习地图记录
	 * @author wm
	 * @date 2019-6-10 上午10:08:57
	 * @param sess
	 * @param sm
	 */
	void save(Session sess,StudyMapInfo sm);
	
	/**
	 * 修改学习地图记录
	 * @author wm
	 * @date 2019-6-10 上午10:09:11
	 * @param sess
	 * @param sm
	 */
	void update(Session sess,StudyMapInfo sm);
	
	/**
	 * 根据知识点编号、学生编号获取学习地图记录
	 * @author wm
	 * @date 2019-6-10 上午10:09:19
	 * @param sess
	 * @param loreId 知识点编号（0时不查询）
	 * @param userId 学生编号
	 * @return
	 */
	List<StudyMapInfo> findInfoByOptions(Session sess,Integer loreId,Integer userId);
}
