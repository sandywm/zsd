package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.SysFeeInfo;

public interface SysFeeManager {

	/**
	 * 根据主键获取系统费用实体
	 * @author wm
	 * @date 2019-9-6 下午05:39:54
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	SysFeeInfo getEntityById(Integer id) throws WEBException;
	
	/**
	 * 根据费用类型、学段、有效状态获取系统费用列表
	 * @author wm
	 * @date 2019-9-6 下午05:40:07
	 * @param feeType 费用类型(0:全部,1-导师费,2-会员费)
	 * @param schoolType 学段(0:全部,1-小学,2-初中,3-高中)
	 * @param showStatus 有效状态(0:全部,1-有效,2-无效)
	 * @return
	 * @throws WEBException
	 */
	List<SysFeeInfo> listInfoByopt(Integer feeType,Integer schoolType,Integer showStatus) throws WEBException;
}
