package com.zsd.dao;


import org.hibernate.Session;

import com.zsd.module.HwTraceStudyLogInfo;

public interface HwTraceStudyLogDao {

	/**
	 * 根据主键获取家庭作业溯源学习记录表实体
	 * @author wm
	 * @date 2019-8-16 上午10:24:37
	 * @param sess
	 * @param id
	 * @return
	 */
	HwTraceStudyLogInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加
	 * @author wm
	 * @date 2019-8-16 上午10:25:16
	 * @param sess
	 * @param sl
	 */
	void save(Session sess,HwTraceStudyLogInfo sl);
	
	/**
	 * 修改
	 * @author wm
	 * @date 2019-8-16 上午10:25:22
	 * @param sess
	 * @param sl
	 */
	void update(Session sess,HwTraceStudyLogInfo sl);
	
	/**
	 * 根据家庭作业统计编号获取家庭作业溯源学习记录表实体
	 * @author wm
	 * @date 2019-8-16 上午10:24:54
	 * @param sess
	 * @param tjId 家庭作业统计编号
	 * @return
	 */
	HwTraceStudyLogInfo getEntityByTjId(Session sess,Integer tjId);
}
