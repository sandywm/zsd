package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.ModuleInfo;

public interface ModuleManager {

	/**
	 * 增加模块
	 * @author Administrator
	 * @date 2019-5-5 下午04:27:40
	 * @param modName
	 * @param modUrl
	 * @param mainStatus
	 * @param modOrder
	 * @param modSmallImgPc
	 * @param modBigImgPc
	 * @param modImgApp
	 * @return
	 * @throws WEBException
	 */
	Integer addMod(String modName,String modUrl,Integer mainStatus,Integer modOrder,String modSmallImgPc,String modBigImgPc,
			String modImgApp) throws WEBException;
	
	/**
	 * 修改模块信息
	 * @author Administrator
	 * @date 2019-5-5 下午04:27:46
	 * @param modId
	 * @param modName
	 * @param modUrl
	 * @param mainStatus
	 * @param modOrder
	 * @param modSmallImgPc
	 * @param modBigImgPc
	 * @param modImgApp
	 * @return
	 * @throws WEBException
	 */
	boolean updateMod(Integer modId,String modName,String modUrl,Integer mainStatus,Integer modOrder,String modSmallImgPc,String modBigImgPc,
			String modImgApp) throws WEBException;
	
	/**
	 * 获取所有模块
	 * @author Administrator
	 * @date 2019-5-5 下午04:27:55
	 * @return
	 * @throws WEBException
	 */
	List<ModuleInfo> listAllInfo() throws WEBException;
}
