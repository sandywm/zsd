package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.RelationZdResult;

public interface RelationZdResultDao {

	/**
	 * 根据主键获取关联知识点诊断结果
	 * @author wm
	 * @date 2019-5-30 上午09:57:01
	 * @param sess
	 * @param id
	 * @return
	 */
	RelationZdResult getEntityById(Session sess,Integer id);
	
	/**
	 * 增加关联知识点的诊断结果信息
	 * @author wm
	 * @date 2019-5-30 上午09:57:25
	 * @param sess
	 * @param rz
	 */
	void save(Session sess,RelationZdResult rz);
	
	/**
	 * 修改指定的关联知识点诊断结果信息
	 * @author wm
	 * @date 2019-5-30 上午09:57:41
	 * @param sess
	 * @param rz
	 */
	void update(Session sess,RelationZdResult rz);
	
	/**
	 * 根据学习记录编号、指定关联知识点的诊断结果列表
	 * @author wm
	 * @date 2019-5-30 上午09:57:45
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param loreId 关联知识点编号
	 * @return
	 */
	List<RelationZdResult> findInfoByOpt(Session sess,Integer studyLogId,Integer loreId);
}
