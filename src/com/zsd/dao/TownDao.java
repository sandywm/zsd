package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.TownInfo;

public interface TownDao {

	/**
	 * 根据县编码获取镇列表
	 * @author wm
	 * @date 2019-6-21 下午04:27:10
	 * @param sess
	 * @param countyCode 县编码
	 * @return
	 */
	List<TownInfo> findInfoByCountyCode(Session sess,String countyCode);
	
	/**
	 * 增加镇数据
	 * @author wm
	 * @date 2019-6-21 下午04:27:28
	 * @param sess
	 * @param town
	 */
	void save(Session sess, TownInfo town);
	
	/**
	 * 根据县名称获取镇列表
	 * @author wm
	 * @date 2020-1-3 下午03:31:02
	 * @param sess
	 * @param countyName 县名称
	 * @return
	 */
	List<TownInfo> findInfoByCountyName(Session sess,String countyName);
}
