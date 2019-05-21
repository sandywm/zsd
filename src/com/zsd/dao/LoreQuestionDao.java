package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.LoreQuestion;

public interface LoreQuestionDao {
	/**
	 * 根据主键加载知识点题库实体
	 * @param sess 持久化操作所需要的Hiberate Session
	 * @param id 需要加载的知识点题库的主键值
	 * @return 加载的知识点题库PO
	 */
	LoreQuestion get(Session sess,int id);
	
	/**
	 * 保存知识点题库实体，新增一条知识点题库记录
	 * @param LoreQuestion 保存的知识点题库实例
	 */
	void save(Session sess,LoreQuestion loreQuestion);
	
	/**
	 * 删除知识点题库实体，删除一条知识点题库记录
	 * @param LoreQuestion 删除的知识点题库实体
	 */
	void delete(Session sess,LoreQuestion loreQuestion);
	
	/**
	 * 根据主键删除知识点题库实体，删除一条知识点题库记录
	 * @param id 需要删除知识点题库的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条知识点题库记录
	 * @param LoreQuestion 需要更新的知识点题库
	 */
	void update(Session sess,LoreQuestion loreQuestion);
	
	/**
	 * 根据主键加载知识点题库实体
	 * @author wm
	 * @date 2019-5-6 下午05:26:39
	 * @param sess
	 * @param id
	 * @return
	 */
	LoreQuestion getEntityById(Session sess,Integer id);
	
	/**
	 * 根据知识点编号分页获取题库列表
	 * @author wm
	 * @date 2019-5-6 下午05:28:39
	 * @param sess
	 * @param loreId 知识点编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<LoreQuestion> findPageInfoByLoreId(Session sess,Integer loreId,Integer pageNo, Integer pageSize);
	
	/**
	 * 根据知识点编号获取题库列表记录条数
	 * @author wm
	 * @date 2019-5-6 下午05:30:11
	 * @param sess
	 * @param loreId 知识点编号
	 * @return
	 */
	Integer getCountByLoreId(Session sess,Integer loreId);
	
	/**
	 * 根据知识点编号、知识点类型(可为空),有效状态,获取题库记录列表
	 * @author wm
	 * @date 2019-5-6 下午05:30:53
	 * @param sess
	 * @param loreId 知识点编号
	 * @param loreType 知识点类型(""表示全部，知识清单,点拨指导,解题示范,巩固训练,针对性诊断,再次诊断,知识讲解)
	 * @param inUse 有效状态(-1：表示全部,0：有效，1：无效)
	 * @return
	 */
	List<LoreQuestion> findInfoByOpt(Session sess,Integer loreId,String loreType,Integer inUse);
	
	/**
	 * 根据词库编号获取题库列表
	 * @author wm
	 * @date 2019-5-21 上午11:49:06
	 * @param sess
	 * @param lexId 词库编号 
	 * @return
	 */
	List<LoreQuestion> findInfoByLexId(Session sess,Integer lexId);
	
	/**
	 * 根据提示编号获取词库列表
	 * @author wm
	 * @date 2019-5-21 上午11:50:34
	 * @param sess
	 * @param tipsId 提示编号
	 * @return
	 */
	List<LoreQuestion> findInfoByTipsId(Session sess,Integer tipsId);
}
