package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.BuffetAbilityRelationInfo;

public interface BuffetAbilityRelationInfoDao {
	/**
	 * 根据主键加载自助餐能力类型关联信息
	 * @author zdf
	 * 2019-7-4 上午09:42:21
	 * @param sess
	 * @param id 主键值
	 * @return
	 */
	BuffetAbilityRelationInfo get(Session sess,int id);
	/**
	 * 保存自助餐能力类型关联信息实体
	 * @author zdf
	 * 2019-7-4 上午09:42:50
	 * @param sess
	 * @param barInfo 自助餐能力类型关联实体
	 */
	void save(Session sess,BuffetAbilityRelationInfo barInfo);
	
	/**
	 * 删除自助餐能力类型关联信息实体
	 * @author zdf
	 * 2019-7-4 上午09:43:15
	 * @param sess
	 * @param barInfo 自助餐能力类型关联实体
	 */
	void delete(Session sess,BuffetAbilityRelationInfo barInfo);
	
	/**
	 * 根据主键删除自助餐能力类型关联信息实体，删除一条自助餐能力类型关联信息记录
	 * @description
	 * @author zdf
	 * 2019-7-4 上午09:43:35
	 * @param sess
	 * @param id 需要删除自助餐能力类型关联信息的主键
	 */
	void delete(Session sess,int id);
	/**
	 * 更新一条自助餐能力类型关联信息记录
	 * @description
	 * @author zdf
	 * 2019-7-4 上午09:44:00
	 * @param sess
	 * @param barInfo 需要更新的自助餐能力类型关联信息
	 */
	void update(Session sess,BuffetAbilityRelationInfo barInfo);
	/**
	 * 根据自助餐题库编号获取自助餐能力类型关联信息
	 * @author zdf
	 * 2019-7-4 上午10:07:33
	 * @param sess
	 * @param bqId 自助餐题库编号
	 * @return
	 */
	List<BuffetAbilityRelationInfo> findBARInfo(Session sess, Integer bqId);
} 
