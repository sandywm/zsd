package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.BuffetQueInfo;

public interface BuffetQueInfoDao {

	/**
	 * 根据主键获取自助餐题库实体信息
	 * @author wm
	 * @date 2019-5-17 下午05:00:52
	 * @param sess
	 * @param id
	 * @return
	 */
	BuffetQueInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加自助餐题库信息
	 * @author wm
	 * @date 2019-5-17 下午05:01:10
	 * @param sess
	 * @param bq
	 */
	void save(Session sess,BuffetQueInfo bq);
	
	/**
	 * 根据主键删除自助餐题库信息
	 * @author wm
	 * @date 2019-5-17 下午05:01:25
	 * @param sess
	 * @param bq
	 */
	void delete(Session sess,BuffetQueInfo bq);
	
	/**
	 * 根据主键修改自助餐题库信息
	 * @author wm
	 * @date 2019-5-18 上午09:15:24
	 * @param sess
	 * @param bq
	 */
	void update(Session sess,BuffetQueInfo bq);
	
	/**
	 * 根据知识点编号分页后去自助餐题库信息列表
	 * @author wm
	 * @date 2019-5-17 下午05:02:02
	 * @param sess
	 * @param loreId 知识点编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<BuffetQueInfo> findPageInfoByLoreId(Session sess,Integer loreId,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据知识点编号获取自助餐题库记录条数
	 * @author wm
	 * @date 2019-5-17 下午05:02:35
	 * @param sess
	 * @param loreId
	 * @return
	 */
	Integer getCountByLoreId(Session sess,Integer loreId);
	
	/**
	 * 根据条件获取自助餐题库列表
	 * @author wm
	 * @date 2019-5-17 下午05:02:56
	 * @param sess
	 * @param loreId 知识点编号
	 * @param btId 自助餐类型编号（0表示全部）
	 * @param inUse 有效状态(-1：表示全部,0：有效，1：无效)
	 * @param currNumFlag 是否获取当前类型下最大的题序号和排序号（true:是,false的时候按照order升序）
	 * @return
	 */
	List<BuffetQueInfo> findInfoByOpt(Session sess,Integer loreId,Integer btId,Integer inUse,boolean currNumFlag);
	/**
	 * 根据知识点编号,有效状态获取自助餐题库列表
	 * @author zdf
	 * 2019-7-5 上午11:51:41
	 * @param sess
	 * @param loreId 知识点编号
	 * @param inUse  有效状态(-1：表示全部,0：有效，1：无效)
	 * @return
	 */
	List<BuffetQueInfo> findInfoByOption(Session sess,Integer loreId, Integer inUse);
	/**
	 * 根据指定编号,自助餐类型获取自助餐题库列表
	 * @author zdf
	 * 2019-7-8 下午05:17:01
	 * @param sess
	 * @param loreId 知识点编号
	 * @param buffetTypeName 自助餐类型
	 * @return
	 */
	List<BuffetQueInfo> findInfoByLoreAndBuffetType(Session sess,Integer loreId, String buffetTypeName);
}
