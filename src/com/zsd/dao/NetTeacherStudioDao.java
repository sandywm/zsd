package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.NetTeacherStudioInfo;

public interface NetTeacherStudioDao {
	/**
	 * 根据主键加载网络导师工作室信息
	 * @author zdf
	 * @date  2019-7-24 下午02:47:26
	 * @param sess
	 * @param id 需要加载的网络导师工作室主键值
	 * @return 加载的网络导师工作室信息
	 */
	NetTeacherStudioInfo get(Session sess,int id);
	
	/**
	 * 保存网络导师工作室信息
	 * @author zdf
	 * @date  2019-7-24 下午02:49:13
	 * @param sess
	 * @param ntStudio 网络导师工作室实体
	 */
	void save(Session sess,NetTeacherStudioInfo ntStudio);
	
	/**
	 * 删除网络导师工作室实体信息
	 * @author zdf
	 * @date  2019-7-24 下午02:50:16
	 * @param sess
	 * @param ntStudio 网络导师工作室实体
	 */
	void delete(Session sess,NetTeacherStudioInfo ntStudio);
	
	/**
	 * 根据主键删除网络导师工作室信息实体
	 * @description
	 * @author zdf
	 * @date 2019-7-24 下午02:50:16
	 * @param sess
	 * @param id 需要删除网络导师工作室信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条网络导师工作室信息记录
	 * @description
	 * @author zdf
	 * @date 2019-7-24 下午02:50:16
	 * @param sess
	 * @param ntStudio 需要更新网络老师工作室信息
	 */
	void update(Session sess,NetTeacherStudioInfo ntStudio);
	/**
	 * 根据用户编号获取网络导师工作室信息
	 * @author zdf
	 * 2019-7-28 上午10:11:21
	 * @param userId 用户编号
	 * @return
	 */
	List<NetTeacherStudioInfo> findNTStudioByuId(Session sess,Integer userId);
	/**
	 * 根据工作室邀请码获取工作室信息
	 * @author zdf
	 * 2019-7-31 上午09:53:26
	 * @param sess
	 * @param studioCode 邀请码
	 * @return
	 */
	List<NetTeacherStudioInfo> findNTStudioBystudioCode(Session sess,String studioCode );
	

}
