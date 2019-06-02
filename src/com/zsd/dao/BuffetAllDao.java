package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.BuffetAbilityRelationInfo;
import com.zsd.module.BuffetAbilityTypeInfo;
import com.zsd.module.BuffetMindRelationInfo;
import com.zsd.module.BuffetMindTypeInfo;
import com.zsd.module.BuffetTypeInfo;

/**
 * 自助餐基础类型、思维类型、能力类型、思维关联、能力关联总体
 * @author Administrator
 * @createDate 2019-5-17
 */
public interface BuffetAllDao {

	/**
	 * 根据主键获取自助餐基础实体信息
	 * @author wm
	 * @date 2019-5-17 下午04:20:44
	 * @param sess
	 * @param id
	 * @return
	 */
	BuffetTypeInfo getBTEntityById(Session sess,Integer btId);
	
	/**
	 * 获取所有自助餐基础类型列表
	 * @author wm
	 * @date 2019-5-17 下午04:21:09
	 * @param sess
	 * @return
	 */
	List<BuffetTypeInfo> findBTInfo(Session sess);
	
	/**
	 * 根据主键获取自助餐思维类型实体信息
	 * @author wm
	 * @date 2019-5-17 下午04:21:24
	 * @param sess
	 * @param id
	 * @return
	 */
	BuffetMindTypeInfo getBMTEntityById(Session sess,Integer bmtId);
	
	/**
	 * 获取所有自助餐思维类型列表
	 * @author wm
	 * @date 2019-5-17 下午04:21:42
	 * @param sess
	 * @return
	 */
	List<BuffetMindTypeInfo> findBMTInfo(Session sess);
	
	/**
	 * 根据主键获取自助餐能力类型实体信息
	 * @author wm
	 * @date 2019-5-17 下午04:22:20
	 * @param sess
	 * @param id
	 * @return
	 */
	BuffetAbilityTypeInfo getBATEntityById(Session sess,Integer batId);
	
	/**
	 * 获取所有自助餐能力类型列表
	 * @author wm
	 * @date 2019-5-17 下午04:22:58
	 * @param sess
	 * @return
	 */
	List<BuffetAbilityTypeInfo> findBATInfo(Session sess);
	
	/**
	 * 增加自助餐思维关联信息
	 * @author wm
	 * @date 2019-5-17 下午04:33:01
	 * @param sess
	 * @param bmrInfo
	 */
	void saveBMR(Session sess,BuffetMindRelationInfo bmrInfo);
	
	/**
	 * 根据主键删除自助餐思维关联信息
	 * @author wm
	 * @date 2019-5-17 下午04:34:42
	 * @param sess
	 * @param bmrId
	 */
	void delBMRById(Session sess,Integer bmrId);
	
	/**
	 * 根据自助餐编号获取自助餐思维关联信息
	 * @author wm
	 * @date 2019-5-17 下午04:37:16
	 * @param sess
	 * @param buffetId 自助餐编号
	 * @return
	 */
	List<BuffetMindRelationInfo> findBMRInfoByBuffetId(Session sess,Integer buffetId);
	
	/**
	 * 增加自助餐能力关联信息
	 * @author wm
	 * @date 2019-5-17 下午04:31:55
	 * @param sess
	 * @param barInfo
	 */
	void saveBAR(Session sess,BuffetAbilityRelationInfo barInfo);
	
	/**
	 * 根据主键删除自助餐能力关联信息
	 * @author wm
	 * @date 2019-5-17 下午04:38:22
	 * @param sess
	 * @param barId
	 */
	void delBARById(Session sess,Integer barId);
	
	/**
	 * 根据自助餐编号获取自助餐能力关联信息
	 * @author wm
	 * @date 2019-5-17 下午04:39:30
	 * @param sess
	 * @param buffetId 自助餐编号
	 * @return
	 */
	List<BuffetAbilityRelationInfo> findBARInfoByBuffetId(Session sess,Integer buffetId);
	
}
