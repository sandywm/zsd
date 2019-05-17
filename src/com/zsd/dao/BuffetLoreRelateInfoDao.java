package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.BuffetLoreRelateInfo;

public interface BuffetLoreRelateInfoDao {
	
	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-5-17 下午05:47:32
	 * @param sess
	 * @param id
	 * @return
	 */
	BuffetLoreRelateInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加自助餐知识点关联信息
	 * @author wm
	 * @date 2019-5-17 下午05:47:47
	 * @param sess
	 * @param blr
	 */
	void save(Session sess,BuffetLoreRelateInfo blr);
	
	/**
	 * 删除自助餐知识点关联信息
	 * @author wm
	 * @date 2019-5-17 下午05:49:40
	 * @param sess
	 * @param blr
	 */
	void delete(Session sess,BuffetLoreRelateInfo blr);
	
	/**
	 * 修改自助餐知识点关联信息
	 * @author wm
	 * @date 2019-5-17 下午05:49:57
	 * @param sess
	 * @param blr
	 */
	void update(Session sess,BuffetLoreRelateInfo blr);
	
	/**
	 * 根据条件获取自助餐知识点关联信息列表
	 * @author wm
	 * @date 2019-5-17 下午05:50:12
	 * @param sess
	 * @param buffetId 自助餐题库编号
	 * @param relateLoreId 关联知识点(0时表示全部)
	 * @return
	 */
	List<BuffetLoreRelateInfo> findInfoByOpt(Session sess,Integer buffetId,Integer relateLoreId);
	
}
