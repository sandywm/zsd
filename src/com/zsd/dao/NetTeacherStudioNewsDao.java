package com.zsd.dao;

import org.hibernate.Session;

import com.zsd.module.NetTeacherStudioNewsInfo;

public interface NetTeacherStudioNewsDao {
	/**
	 * 根据主键加载网络导师工作室新闻信息
	 * @author zdf
	 * @date  2019-7-25 9:47:26
	 * @param sess
	 * @param id 需要加载的网络导师工作室新闻主键值
	 * @return 加载的网络导师工作室新闻信息
	 */
	NetTeacherStudioNewsInfo get(Session sess,int id);
	
	/**
	 * 保存网络导师工作室新闻信息
	 * @author zdf
	 * @date  2019-7-25 9:49:13
	 * @param sess
	 * @param ntsNews 网络导师工作室新闻实体
	 */
	void save(Session sess,NetTeacherStudioNewsInfo ntsNews);
	
	/**
	 * 删除网络导师工作室新闻实体信息
	 * @author zdf
	 * @date  2019-7-25 9:50:16
	 * @param sess
	 * @param ntsNews  网络导师工作室新闻实体
	 */
	void delete(Session sess,NetTeacherStudioNewsInfo ntsNews);
	
	/**
	 * 根据主键删除网络导师工作室新闻信息实体
	 * @description
	 * @author zdf
	 * @date 2019-7-25 9:50:16
	 * @param sess
	 * @param id 需要删除网络导师工作室新闻信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条网络导师工作室新闻信息记录
	 * @description
	 * @author zdf
	 * @date 2019-7-25 9:50:16
	 * @param sess
	 * @param ntsNews 需要更新网络老师工作室新闻信息
	 */
	void update(Session sess,NetTeacherStudioNewsInfo ntsNews);

}
