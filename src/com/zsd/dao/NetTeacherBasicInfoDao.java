package com.zsd.dao;

import org.hibernate.Session;

import com.zsd.module.NetTeacherBasicInfo;

/** 
 * @author zong
 * @version 2019-5-11 下午05:57:04
 */
public interface NetTeacherBasicInfoDao {
	/**
	 * 根据主键加载网络老师背景资料信息实体
	 * @description
	 * @author zong
	 * @date 2019-5-11 下午05:57:04
	 * @param sess
	 * @param id 网络老师背景资料主键值
	 * @return 网络老师背景资料信息PO
	 */
	NetTeacherBasicInfo get(Session sess,int id);
	
	/**
	 * 保存网络老师背景资料信息信息实体，新增一条网络老师背景资料信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-11 下午05:57:04
	 * @param sess
	 * @param roleInfo 保存的网络老师背景资料信息实例
	 */
	void save(Session sess,NetTeacherBasicInfo ntbInfo);
	
	/**
	 * 删除网络老师背景资料信息实体，删除一条网络老师背景资料信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-11 下午05:57:04
	 * @param sess
	 * @param ntbInfo 删除的网络老师背景资料信息实体
	 */
	void delete(Session sess,NetTeacherBasicInfo ntbInfo);
	
	/**
	 * 根据主键删除网络老师背景资料信息实体，删除一条网络老师背景资料信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-11 下午05:57:04
	 * @param sess
	 * @param id 需要删除网络老师背景资料信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条网络老师背景资料信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-11 下午05:57:04
	 * @param sess
	 * @param ntbInfo 需要更新的网络老师背景资料信息
	 */
	void update(Session sess,NetTeacherBasicInfo ntbInfo);
	

}
