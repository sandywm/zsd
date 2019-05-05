package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.LoreInfo;

public interface LoreInfoDao {
	/**
	 * 根据主键加载知识点信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的知识点信息的主键值
	 * @return 加载的知识点信息PO
	 */
	LoreInfo get(Session sess,int id);
	
	/**
	 * 保存知识点信息信息实体，新增一条知识点信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param lore 保存的知识点信息实例
	 */
	void save(Session sess,LoreInfo lore);
	
	/**
	 * 删除知识点信息实体，删除一条知识点信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param lore 删除的知识点信息实体
	 */
	void delete(Session sess,LoreInfo lore);
	
	/**
	 * 根据主键删除知识点信息实体，删除一条知识点信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除知识点信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条知识点信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param lore 需要更新的知识点信息
	 */
	void update(Session sess,LoreInfo lore);
	
	/**
	 * 分页获取指定章节的知识点列表
	 * @author wm
	 * @date 2019-5-4 下午11:04:51 
	 * @param sess
	 * @param cptId 章节编号
	 * @return
	 */
	List<LoreInfo> findPageInfoByCptId(Session sess,Integer cptId,Integer pageNo,Integer pageSize);
	
	/**
	 * 获取指定章节的知识点记录条数
	 * @author wm
	 * @date 2019-5-4 下午11:05:16 
	 * @param sess
	 * @param cptId 章节编号
	 * @return
	 */
	Integer getCountByCptId(Session sess,Integer cptId);
	
	/**
	 * 获取指定章节的知识点(有效状态)列表
	 * @author wm
	 * @date 2019-5-4 下午11:04:51 
	 * @param sess
	 * @param cptId 章节编号
	 * @return
	 */
	List<LoreInfo> findInfoByCptId(Session sess,Integer cptId);
}
