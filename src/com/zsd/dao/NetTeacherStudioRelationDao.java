package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.NetTeacherStudioRelationInfo;

public interface NetTeacherStudioRelationDao {
	/**
	 * 根据主键加载网络导师工作室老师信息
	 * @author zdf
	 * @date  2019-7-25 10:47:26
	 * @param sess
	 * @param id 需要加载的网络导师工作室老师主键值
	 * @return 加载的网络导师工作室老师信息
	 */
	NetTeacherStudioRelationInfo get(Session sess,int id);
	
	/**
	 * 保存网络导师工作室老师信息
	 * @author zdf
	 * @date  2019-7-25 10:49:13
	 * @param sess
	 * @param ntsr 网络导师工作室老师实体
	 */
	void save(Session sess,NetTeacherStudioRelationInfo ntsr);
	
	/**
	 * 删除网络导师工作室老师实体信息
	 * @author zdf
	 * @date  2019-7-25 10:50:16
	 * @param sess
	 * @param ntsr  网络导师工作室老师实体
	 */
	void delete(Session sess,NetTeacherStudioRelationInfo ntsr);
	
	/**
	 * 根据主键删除网络导师工作室老师信息实体
	 * @description
	 * @author zdf
	 * @date 2019-7-25 10:50:16
	 * @param sess
	 * @param id 需要删除网络导师工作室老师信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条网络导师工作室老师信息记录
	 * @description
	 * @author zdf
	 * @date 2019-7-25 10:50:16
	 * @param sess
	 * @param ntsr 需要更新网络老师工作室老师信息
	 */
	void update(Session sess,NetTeacherStudioRelationInfo ntsr);
	/**
	 * 根据工作室编号获取工作室老师信息
	 * @author zdf
	 * 2019-7-30 上午09:18:06
	 * @param sess
	 * @param ntStudioId 工作室编号
	 * @return
	 */
	List<NetTeacherStudioRelationInfo> findInfoByNtStudioId(Session sess, Integer ntStudioId);
	/**
	 * 根据导师编号获取工作室关联信息
	 * @author zdf
	 * 2019-9-4 下午05:29:11
	 * @param sess
	 * @param ntId 导师编号
	 * @return
	 */
	List<NetTeacherStudioRelationInfo> findInfoByTeaId(Session sess, Integer ntId);
}
