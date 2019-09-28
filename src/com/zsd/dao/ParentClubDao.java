package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.ParentClubInfo;

public interface ParentClubDao {
	/**
	 * 根据主键加载家长群信息
	 * @author zdf
	 * 2019-9-27 上午10:34:57
	 * @param sess
	 * @param id 需要加载的家长群主键值
	 * @return 加载的家长群信息
	 */
	ParentClubInfo get(Session sess,int id);
	
	/**
	 * 保存家长群信息
	 * @author zdf
	 * 2019-9-27 上午10:35:32
	 * @param sess
	 * @param parenClubInfo 家长群实体
	 */
	void save(Session sess,ParentClubInfo parenClubInfo);
	/**
	 * 删除家长群实体信息
	 * @author zdf
	 * 2019-9-27 上午10:36:06
	 * @param sess
	 * @param parenClubInfo 家长群实体
	 */
	void delete(Session sess,ParentClubInfo parenClubInfo);
	/**
	 * 根据主键删除家长群信息实体
	 * @description
	 * @author zdf
	 * 2019-9-27 上午10:36:57
	 * @param sess
	 * @param id 需要删除家长群信息的主键
	 */
	void delete(Session sess,int id);
	/**
	 * 更新一条家长群信息记录
	 * @author zdf
	 * 2019-9-27 上午10:37:31
	 * @param sess
	 * @param parenClubInfo 需要更新家长群信息信息
	 */
	void update(Session sess,ParentClubInfo parenClubInfo);
	/**
	 *  根据用户编号获取家长群信息
	 * @author zdf
	 * 2019-9-27 上午10:38:24
	 * @param sess
	 * @param userId 用户编号
	 * @return
	 */
	List<ParentClubInfo> findParentClubByuId(Session sess,Integer userId);
	/**
	 * 根据工作室邀请码获取工作室信息
	 * @author zdf
	 * 2019-9-27 上午10:38:51
	 * @param sess
	 * @param pcCode
	 * @return
	 */
	/**
	 * 根据家长群获取工作室信息
	 * @author zdf
	 * 2019-7-31 上午09:53:26
	 * @param sess
	 * @param studioCode 家长群邀请码
	 * @return
	 */
	List<ParentClubInfo> findParentClubBypcCode(Session sess,String clubCode );

}
