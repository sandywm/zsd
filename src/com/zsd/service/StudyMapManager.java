package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.StudyMapInfo;

public interface StudyMapManager {

	/**
	 * 增加学习地图记录
	 * @author wm
	 * @date 2019-6-10 上午10:16:51
	 * @param stuId 学生编号
	 * @param loreId 知识点编号
	 * @param currStep 当前层数
	 * @return
	 * @throws WEBException
	 */
	Integer addSM(Integer stuId,Integer loreId,Integer currStep) throws WEBException;
	
	/**
	 * 修改指定学习地图的层数
	 * @author wm
	 * @date 2019-6-10 上午10:17:29
	 * @param id 主键
	 * @param currStep 层数
	 * @return
	 * @throws WEBException
	 */
	boolean updateStepById(Integer id,Integer currStep) throws WEBException;
	
	/**
	 * 根据学生编号，知识点编号获取学习地图信息列表
	 * @author wm
	 * @date 2019-6-10 上午10:18:35
	 * @param stuId 学生编号
	 * @param loreId 知识点编号
	 * @return
	 * @throws WEBException
	 */
	List<StudyMapInfo> listInfoByOpt(Integer stuId,Integer loreId) throws WEBException;
	
	/**
	 * 根据主键获取学习地图实体信息
	 * @author wm
	 * @date 2019-6-10 上午10:19:08
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	StudyMapInfo getEntityById(Integer id) throws WEBException;
}
