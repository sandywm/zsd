package com.zsd.dao;

import org.hibernate.Session;

import com.zsd.module.JoinLoreRelation;

public interface JoinLoreRelationDao {

	/**
	 * 根据主键获取合并知识点信息实体
	 * @author wm
	 * @date 2019-5-22 上午11:37:02
	 * @param sess
	 * @param id
	 * @return
	 */
	JoinLoreRelation getEntityById(Session sess,Integer id);
	
	/**
	 * 增加合并知识点信息
	 * @author wm
	 * @date 2019-5-22 上午11:37:22
	 * @param sess
	 * @param jlr
	 */
	void save(Session sess,JoinLoreRelation jlr);
	
	/**
	 * 删除合并知识点信息
	 * @author wm
	 * @date 2019-5-22 上午11:37:38
	 * @param sess
	 * @param jlr
	 */
	void delete(Session sess,JoinLoreRelation jlr);
	
	/**
	 * 修改合并知识点信息
	 * @author wm
	 * @date 2019-5-22 上午11:37:46
	 * @param sess
	 * @param jlr
	 */
	void update(Session sess,JoinLoreRelation jlr);
	
	/**
	 * 根据知识点编号查询合并知识点信息
	 * @author wm
	 * @date 2019-5-22 上午11:37:53
	 * @param loreId 知识点编号
	 * @return
	 */
	JoinLoreRelation getInfoByLoreId(Session sess,Integer loreId);
}
