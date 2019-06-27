package com.zsd.dao;

import org.hibernate.Session;

import com.zsd.module.BuffetLoreStudyLogInfo;

public interface BuffetLoreStudyLogDao {

	/**
	 * 根据主键获取自助餐知识点学习记录信息实体
	 * @author wm
	 * @date 2019-6-27 下午05:24:42
	 * @param sess
	 * @param id
	 * @return
	 */
	BuffetLoreStudyLogInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加自助餐知识点学习记录
	 * @author wm
	 * @date 2019-6-27 下午05:25:10
	 * @param sess
	 * @param blsLog
	 */
	void save(Session sess,BuffetLoreStudyLogInfo blsLog);
	
	/**
	 * 修改自助餐知识点学习记录
	 * @author wm
	 * @date 2019-6-27 下午05:25:20
	 * @param sess
	 * @param blsLog
	 */
	void update(Session sess,BuffetLoreStudyLogInfo blsLog);
	
	/**
	 * 根据自助餐学习详情编号获取自助餐知识点学习记录信息实体
	 * @author wm
	 * @date 2019-6-27 下午05:25:28
	 * @param sess
	 * @param bsdId 自助餐学习详情编号
	 * @return
	 */
	BuffetLoreStudyLogInfo getInfoByBsdId(Session sess,Integer bsdId);
}
