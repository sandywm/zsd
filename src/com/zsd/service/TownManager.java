package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.TownInfo;

public interface TownManager {

	/**
	 * 增加乡镇数据
	 * @author wm
	 * @date 2019-6-21 下午04:34:55
	 * @param countyCode 县编码
	 * @param countyName 县名称
	 * @param townCode 乡镇编码
	 * @param townName 乡镇名称
	 * @return
	 * @throws WEBException
	 */
	Integer addInfo(String countyCode,String countyName,String townCode,String townName) throws WEBException;
	
	/**
	 * 根据县编码获取乡镇列表
	 * @author wm
	 * @date 2019-6-21 下午04:35:38
	 * @param countyCode 县编码
	 * @return
	 * @throws WEBException
	 */
	List<TownInfo> listInfoByCountyCode(String countyCode) throws WEBException;
	
	/**
	 * 根据县名称获取乡镇列表
	 * @author wm
	 * @date 2020-1-3 下午03:29:53
	 * @param countyName 县名称
	 * @return
	 * @throws WEBException
	 */
	List<TownInfo> listInfoByCountyName(String countyName) throws WEBException;
}
