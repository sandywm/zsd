package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.BuffetMindRelationInfo;

public interface BuffetMindRelationInfoDao {
	
	/**
	 * 根据主键加载自助餐思维类型关联信息
	 * @author zdf
	 * 2019-7-4 上午09:42:21
	 * @param sess
	 * @param id 主键值
	 * @return
	 */
	BuffetMindRelationInfo get(Session sess,int id);
	/**
	 * 保存自助餐思维类型关联信息实体
	 * @author zdf
	 * 2019-7-4 上午09:42:50
	 * @param sess
	 * @param bmrInfo 自助餐思维类型关联实体
	 */
	void save(Session sess,BuffetMindRelationInfo bmrInfo);
	
	/**
	 * 删除自助餐思维类型关联信息实体
	 * @author zdf
	 * 2019-7-4 上午09:43:15
	 * @param sess
	 * @param bmrInfo 自助餐思维类型关联实体
	 */
	void delete(Session sess,BuffetMindRelationInfo bmrInfo);
	
	/**
	 * 根据主键删除自助餐思维类型关联信息实体，删除一条自助餐思维类型关联信息记录
	 * @description
	 * @author zdf
	 * 2019-7-4 上午09:43:35
	 * @param sess
	 * @param id 需要删除自助餐思维类型关联信息的主键
	 */
	void delete(Session sess,int id);
	/**
	 * 更新一条自助餐思维类型关联信息记录
	 * @description
	 * @author zdf
	 * 2019-7-4 上午09:44:00
	 * @param sess
	 * @param bmrInfo 需要更新的自助餐思维类型关联信息
	 */
	void update(Session sess,BuffetMindRelationInfo bmrInfo);
	/**
	 * 根据自助餐题库编号获取自助餐思维类型关联信息
	 * @author zdf
	 * 2019-7-4 上午10:04:47
	 * @param sess
	 * @param bqId 自助餐题库编号
	 * @return
	 */
	List<BuffetMindRelationInfo> findBMRInfoByBuffetQueId(Session sess,Integer bqId);
}
