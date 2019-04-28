package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.Edition;

public interface EditionManager {

	/**
	 * 增加出版社
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:26:23
	 * @param ediName 出版社名称
	 * @param ediOrder 出版社排序
	 * @return
	 * @throws WEBException
	 */
	Integer addEdi(String ediName,Integer ediOrder)throws WEBException;
	
	/**
	 * 修改出版社信息
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:28:49
	 * @param id 主键
	 * @param ediName 出版社名称
	 * @param ediOrder 出版社排序
	 * @param showStatus 显示状态(-1：不修改,0:显示,1:隐藏)
	 * @return
	 * @throws WEBException
	 */
	boolean updateEdiInfoById(Integer id,String ediName,Integer ediOrder,Integer showStatus)throws WEBException;
	
	/**
	 * 根据条件获取出版社信息列表
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:31:16
	 * @param id 出版社编号(0表示全部)
	 * @param showStatus 显示状态(-1表示全部)
	 * @return
	 * @throws WEBException
	 */
	List<Edition> listInfoByShowStatus(Integer id,Integer showStatus)throws WEBException;
}
