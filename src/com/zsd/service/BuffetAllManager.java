package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
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
public interface BuffetAllManager {

	/**
	 * 获取基础类型类表
	 * @author wm
	 * @date 2019-5-17 下午06:10:46
	 * @return
	 * @throws WEBException
	 */
	List<BuffetTypeInfo> listBTInfo() throws WEBException;
	
	/**
	 * 获取思维类型列表
	 * @author wm
	 * @date 2019-5-17 下午06:11:54
	 * @return
	 * @throws WEBException
	 */
	List<BuffetMindTypeInfo> listBMTInfo() throws WEBException;
	
	/**
	 * 获取能力类型列表
	 * @author wm
	 * @date 2019-5-17 下午06:12:04
	 * @return
	 * @throws WEBException
	 */
	List<BuffetAbilityTypeInfo> listBATInfo() throws WEBException;
	
	/**
	 * 增加自助餐思维类型关联信息
	 * @author wm
	 * @date 2019-5-17 下午06:12:23
	 * @param buffetId 自助餐编号
	 * @param bmtIdStr 思维类型编号(多个用逗号隔开)
	 * @return
	 * @throws WEBException
	 */
	boolean addBMR(Integer buffetId,String bmtIdStr) throws WEBException;
	
	/**
	 * 删除指定自助餐下所有的思维关联信息
	 * @author wm
	 * @date 2019-5-17 下午06:22:09
	 * @param buffetId 自助餐编号
	 * @throws WEBException
	 */
	void delBMR(Integer buffetId) throws WEBException;
	
	/**
	 * 获取指定自助餐下的思维关联信息列表
	 * @author wm
	 * @date 2019-5-17 下午06:24:47
	 * @param buffetId 自助餐编号
	 * @return
	 * @throws WEBException
	 */
	List<BuffetMindRelationInfo> listBMRInfoByBuffetId(Integer buffetId) throws WEBException;
	
	/**
	 * 增加自助餐能力类型关联信息
	 * @author wm
	 * @date 2019-5-17 下午06:13:37
	 * @param buffetId 自助餐编号
	 * @param batIdStr 能力类型编号(多个用逗号隔开)
	 * @return
	 * @throws WEBException
	 */
	boolean addBAR(Integer buffetId,String batIdStr) throws WEBException;
	
	/**
	 * 删除指定自助餐下所有的能力类型关联信息
	 * @author wm
	 * @date 2019-5-17 下午06:23:03
	 * @param buffetId 自助餐编号
	 * @throws WEBException
	 */
	void delBAR(Integer buffetId) throws WEBException;
	
	/**
	 * 获取指定自助餐下的能力关联信息列表
	 * @author wm
	 * @date 2019-5-17 下午06:25:12
	 * @param buffetId 自助餐编号
	 * @return
	 * @throws WEBException
	 */
	List<BuffetAbilityRelationInfo> listBARInfoByBuffetId(Integer buffetId) throws WEBException;
	
	
	
}
