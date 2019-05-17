package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.LoreRelateInfo;

public interface LoreRelateDao {
	/**
	 * 根据主键加载知识点关联表信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的知识点关联表信息的主键值
	 * @return 加载的知识点关联表信息PO
	 */
	LoreRelateInfo get(Session sess,int id);
	
	/**
	 * 保存知识点关联表信息信息实体，新增一条知识点关联表信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param lr 保存的知识点关联表信息实例
	 */
	void save(Session sess,LoreRelateInfo lr);
	
	/**
	 * 删除知识点关联表信息实体，删除一条知识点关联表信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param lr 删除的知识点关联表信息实体
	 */
	void delete(Session sess,LoreRelateInfo lr);
	
	/**
	 * 根据主键删除知识点关联表信息实体，删除一条知识点关联表信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除知识点关联表信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条知识点关联表信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param lr 需要更新的知识点关联表信息
	 */
	void update(Session sess,LoreRelateInfo lr);
	
	/**
	 * 根据条件获取关联的子知识点（学生学习时loreInUse=0，后台查看时loreInUse=-1）
	 * @author wm
	 * @date 2019-5-7 下午10:38:31 
	 * @param sess
	 * @param loreId 知识点编号（0表示全部）
	 * @param rootLoreId 子知识点编号（0表示全部）
	 * @param loreInUse 知识点开启状态（-1表示全部）
	 * @param orderOpt 子知识点编码排序状态（asc,desc）""表示无
	 * @return
	 */
	List<LoreRelateInfo> findIndoByLoreId(Session sess,Integer loreId,Integer rootLoreId,Integer loreInUse,String orderOpt);
}
