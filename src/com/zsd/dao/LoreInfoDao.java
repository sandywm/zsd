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
	
	/**
	 * 根据知识点名称或者拼音码获取知识点列表
	 * @author wm
	 * @date 2019-5-6 上午08:24:36
	 * @param sess
	 * @param lorePyCode 知识点拼音码(""不查)
	 * @param loreName 知识点名称(""不查)
	 * @param ediId 出版社编号(0表示全部)
	 * @return
	 */
	List<LoreInfo> findInfoByOpt(Session sess,String lorePyCode,String loreName,Integer ediId);
	
	/**
	 * 获取指定章节下指定知识点名称的知识点信息列表（同一章节下知识点名称不能重名）
	 * @author wm
	 * @date 2019-5-6 上午08:50:24
	 * @param sess
	 * @param cptId 章节编号
	 * @param loreName 知识点名称
	 * @return
	 */
	List<LoreInfo> findInfoByOpt(Session sess,Integer cptId,String loreName);
	
	/**
	 * 重写根据主键获取实体信息
	 * @author wm
	 * @date 2019-5-6 上午09:01:24
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	LoreInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 根据主知识点（通用版）获取所有子知识点信息列表
	 * @author wm
	 * @date 2019-5-6 上午09:06:17
	 * @param sess
	 * @param mainLoreId 主知识点
	 * @return
	 */
	List<LoreInfo> findInfoByMainLoreId(Session sess,Integer mainLoreId);
	
	/**
	 * 根据引用知识点编号（通用版），其他出版社获取知识点
	 * @author wm
	 * @date 2019-5-16 上午09:09:20
	 * @param sess
	 * @param mainLoreId 引用知识点编号（通用版）
	 * @param ediId 其他出版社编号（通用版除外）
	 * @return
	 */
	LoreInfo getLoreInfoByOpt(Session sess,Integer mainLoreId,Integer ediId);
	
	/**
	 * 获取全部数据（自动修改编码时用）
	 * @author wm
	 * @date 2019-5-16 下午04:50:01
	 * @param sess
	 * @return
	 */
	List<LoreInfo> findAllInfo(Session sess);
}
