package com.zsd.service;

import java.util.List;

import com.zsd.module.BuffetLoreRelateInfo;
import com.zsd.exception.WEBException;

public interface BuffetLoreRelateInfoManager {

	/**
	 * 增加自助餐知识点关联信息
	 * @author wm
	 * @date 2019-5-21 下午03:33:20
	 * @param buffetId 自助餐编号
	 * @param loreId 知识点编号
	 * @param ediId 出版社编号
	 * @param currLoreId 当前自助餐题属于那个知识点名下
	 * @return
	 * @throws WEBException
	 */
	Integer addBLR(Integer buffetId,Integer loreId,Integer ediId,Integer currLoreId) throws WEBException;
	
	/**
	 * 删除指定主键的自助餐知识点关联信息
	 * @author wm
	 * @date 2019-5-21 下午03:33:53
	 * @param blrId
	 * @return
	 * @throws WEBException
	 */
	boolean delBLRById(Integer blrId) throws WEBException;
	
	/**
	 * 获取指定自助餐提题关联的知识点列表（知识编码降序排列）
	 * @author wm
	 * @date 2019-5-18 上午10:51:24
	 * @param buffetId 自助餐编号
	 * @return
	 * @throws WEBException
	 */
	List<BuffetLoreRelateInfo> listInfoByBuffetId(Integer buffetId)throws WEBException;
	
	/**
	 * 根据自助餐编号、知识点编号获取自助餐知识点关联信息
	 * @author wm
	 * @date 2019-5-21 下午05:46:13
	 * @param buffetId 自助餐编号
	 * @param loreId 知识点编号
	 * @return
	 * @throws WEBException
	 */
	List<BuffetLoreRelateInfo> listInfoByOpt(Integer buffetId,Integer loreId)throws WEBException;
}
