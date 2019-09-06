package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.SysFeeInfo;

public interface SysFeeDao {

	/**
	 * 根据主键获取系统费用信息实体
	 * @author wm
	 * @date 2019-9-6 下午05:28:55
	 * @param sess
	 * @param id
	 * @return
	 */
	SysFeeInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 根据费用类型、学段、有效状态获取系统费用列表
	 * @author wm
	 * @date 2019-9-6 下午05:29:10
	 * @param sess
	 * @param feeType 费用类型(0:全部,1-导师费,2-会员费)
	 * @param schoolType 学段(0:全部,1-小学,2-初中,3-高中)
	 * @param showStatus 有效状态(0:全部,1-有效,2-无效)
	 * @return
	 */
	List<SysFeeInfo> findInfoByOpt(Session sess,Integer feeType,Integer schoolType,Integer showStatus);
}
