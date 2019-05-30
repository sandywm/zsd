package com.zsd.service;

import com.zsd.exception.WEBException;
import com.zsd.module.RelationZdResult;

public interface RelationZdResultManager {

	/**
	 * 增加关联知识点诊断记录结果
	 * @author wm
	 * @date 2019-5-30 上午10:07:52
	 * @param studyLogId 学习记录编号
	 * @param loreId 关联知识点编号
	 * @param zdxzdFlag 针对性诊断标记
	 * @param studyFlag 学习标记
	 * @param zczdFlag 再次诊断标记
	 * @param studyTimes 学习次数
	 * @param zczdTimes 再次诊断次数
	 * @return
	 * @throws WEBException
	 */
	Integer addRZR(Integer studyLogId, Integer loreId, Integer zdxzdFlag,Integer studyFlag,
			Integer zczdFlag, Integer studyTimes, Integer zczdTimes) throws WEBException;
	
	/**
	 * 修改关联知识点诊断记录信息
	 * @author wm
	 * @date 2019-5-30 上午10:08:39
	 * @param id 主键
	 * @param zdxzdFlag 针对性诊断标记（-2不修改）
	 * @param studyFlag 学习标记（-2不修改）
	 * @param zczdFlag 再次诊断标记（-2不修改）
	 * @param studyTimes 学习次数（-2不修改）
	 * @param zczdTimes 再次诊断次数（-2不修改）
	 * @return
	 * @throws WEBException
	 */
	boolean updateEntity(Integer id,Integer zdxzdFlag, Integer studyFlag,
			Integer zczdFlag, Integer studyTimes, Integer zczdTimes) throws WEBException;
	
	/**
	 * 获取指定学习记录，指定关联知识点的诊断记录信息
	 * @author wm
	 * @date 2019-5-30 上午10:09:45
	 * @param studyLogId 学习记录编号
	 * @param loreId 关联知识点编号
	 * @return
	 * @throws WEBException
	 */
	RelationZdResult getEntityByOpt(Integer studyLogId,Integer loreId) throws WEBException;
}
