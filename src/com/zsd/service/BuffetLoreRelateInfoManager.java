package com.zsd.service;

import java.util.List;

import com.zsd.module.BuffetLoreRelateInfo;
import com.zsd.exception.WEBException;

public interface BuffetLoreRelateInfoManager {

	Integer addBLR(Integer buffetId,Integer loreId) throws WEBException;
	
	boolean delBLRById(Integer blrId) throws WEBException;
	
	/**
	 * 按照关联知识编码降序排列
	 * @author wm
	 * @date 2019-5-18 上午10:51:24
	 * @param buffetId 自助餐编号
	 * @return
	 * @throws WEBException
	 */
	List<BuffetLoreRelateInfo> listInfoByBuffetId(Integer buffetId)throws WEBException;
	
	
	
	
}
