package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.LoreQuestionSubInfo;

public interface LoreQuestionSubDao {
	/**
	 * 根据主键加载知识点题库子表信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的知识点题库子表信息的主键值
	 * @return 加载的知识点题库子表信息PO
	 */
	LoreQuestionSubInfo get(Session sess,int id);
	
	/**
	 * 保存知识点题库子表信息信息实体，新增一条知识点题库子表信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param lqs 保存的知识点题库子表信息实例
	 */
	void save(Session sess,LoreQuestionSubInfo lqs);
	
	/**
	 * 删除知识点题库子表信息实体，删除一条知识点题库子表信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param lqs 删除的知识点题库子表信息实体
	 */
	void delete(Session sess,LoreQuestionSubInfo lqs);
	
	/**
	 * 根据主键删除知识点题库子表信息实体，删除一条知识点题库子表信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除知识点题库子表信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条知识点题库子表信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param lqs 需要更新的知识点题库子表信息
	 */
	void update(Session sess,LoreQuestionSubInfo lqs);
	
	/**
	 * 根据知识点题库编号、题库类型获取知识点子表信息列表
	 * @author wm
	 * @date 2019-5-7 下午09:52:48 
	 * @param sess
	 * @param lqId 知识点题库编号
	 * @param loreTypeName 知识点题库类型
	 * @return
	 */
	List<LoreQuestionSubInfo> findInfoByOpt(Session sess,Integer lqId,String loreTypeName);
	
	/**
	 * 根据主键获取知识点题库子表详情
	 * @author wm
	 * @date 2019-5-7 下午09:58:53 
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	LoreQuestionSubInfo getEntityById(Session sess,Integer id);
} 
