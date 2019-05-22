package com.zsd.service;

import com.zsd.exception.WEBException;
import com.zsd.module.JoinLoreRelation;

public interface JoinLoreRelationManager {

	/**
	 * 增加合并知识点记录
	 * @author wm
	 * @date 2019-5-22 上午11:47:45
	 * @param loreIdArr 合并知识点，逗号隔开
	 * @return
	 * @throws WEBException
	 */
	Integer addJLR(String loreIdArr)throws WEBException;
	
	/**
	 * 删除合并知识点（当合并记录有多个变成1个知识点时）
	 * @author wm
	 * @date 2019-5-22 上午11:48:12
	 * @param jlrId 主键
	 * @return
	 * @throws WEBException
	 */
	boolean delJLR(Integer jlrId)throws WEBException;
	
	/**
	 * 修改合并知识点
	 * @author wm
	 * @date 2019-5-22 上午11:48:46
	 * @param jlrId 主键
	 * @param loreIdArr 新修改的知识点合并记录
	 * @return
	 * @throws WEBException
	 */
	boolean updateJLR(Integer jlrId,String loreIdArr)throws WEBException;
	
	/**
	 * 根据知识点编号查询合并记录（一个知识点最多只能有一条合并记录）
	 * @author wm
	 * @date 2019-5-22 上午11:49:10
	 * @param loreId 知识点编号
	 * @return
	 * @throws WEBException
	 */
	JoinLoreRelation getInfoByLoreId(Integer loreId)throws WEBException;
}
