package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.HwQueInfo;

public interface HwQueDao {

	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-7-22 上午08:51:40
	 * @param sess
	 * @param id
	 * @return
	 */
	HwQueInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加系统家庭作业
	 * @author wm
	 * @date 2019-7-22 上午08:51:51
	 * @param sess
	 * @param hq
	 */
	void save(Session sess,HwQueInfo hq);
	
	/**
	 * 修改系统家庭主页
	 * @author wm
	 * @date 2019-7-22 上午08:52:19
	 * @param sess
	 * @param hq
	 */
	void update(Session sess,HwQueInfo hq);
	
	/**
	 * 根据知识点编号分页获取系统家庭作业列表
	 * @author wm
	 * @date 2019-7-22 上午08:52:28
	 * @param sess
	 * @param loreId 知识点编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<HwQueInfo> findPageInfoByOpt(Session sess,Integer loreId,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据知识点编号获取系统家庭作业记录条数
	 * @author wm
	 * @date 2019-7-22 上午08:53:01
	 * @param sess
	 * @param loreId 知识点编号
	 * @return
	 */
	Integer getCountByLoreId(Session sess,Integer loreId);
	
	/**
	 * 根据条件获取系统家庭作业记录列表
	 * @author wm
	 * @date 2019-7-22 上午08:53:31
	 * @param sess
	 * @param loreId 知识点编号
	 * @param btId 自助餐类型编号（0表示全部）
	 * @param inUse 有效状态(-1：表示全部,0：有效，1：无效)
	 * @param currNumFlag 是否获取当前类型下最大的题序号和排序号（true:是,false的时候按照order升序）
	 * @return
	 */
	List<HwQueInfo> findInfoByOpt(Session sess,Integer loreId,Integer btId,Integer inUse,boolean currNumFlag);
	
	/**
	 * 根据指定编号,自助餐类型获取自助餐题库列表
	 * @author wm
	 * @date 2019-7-22 上午08:55:02
	 * @param sess
	 * @param loreId 知识点编号
	 * @param buffetTypeName 自助餐类型
	 * @return
	 */
	List<HwQueInfo> findInfoByLoreAndBuffetType(Session sess,Integer loreId, String buffetTypeName);
}
