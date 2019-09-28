package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.ParentClubRelationInfo;

public interface ParentClubRelationDao {
	/**
	 * 根据主键加载家长群列表信息
	 * @author zdf
	 * 2019-9-27 上午10:43:27
	 * @param sess
	 * @param id 需要加载的家长群列表主键值
	 * @return 加载的家长群列表信息
	 */
	ParentClubRelationInfo get(Session sess,int id);
	/**
	 * 保存家长群列表信息
	 * @author zdf
	 * 2019-9-27 上午10:43:56
	 * @param sess
	 * @param pcInfo 家长群列表实体
	 */
	void save(Session sess,ParentClubRelationInfo pcInfo);
	/**
	 * 删除家长群列表实体信息
	 * @author zdf
	  * 2019-9-27 上午10:44:24
	 * @param sess
	 * @param pcrInfo  家长群列表实体
	 */
	void delete(Session sess,ParentClubRelationInfo pcrInfo);
	/**
	 * 根据主键删除家长群列表信息实体
	 * @description
	 * @author zdf
	 * 2019-9-27 上午10:44:46
	 * @param sess
	 * @param id 需要删除家长群列表信息的主键
	 */
	void delete(Session sess,int id);
	/**
	 * 更新一条家长群列表信息记录
	 * @description
	 * @author zdf
	 * 2019-9-27 上午10:45:07
	 * @param sess
	 * @param pcrInfo 需要更新家长群列表信息
	 */
	void update(Session sess,ParentClubRelationInfo pcrInfo);
	/**
	 * 根据家长群编号获取家长群家长信息
	 * @author zdf
	 * 2019-9-27 上午10:45:31
	 * @param sess
	 * @param pcrId 家长群编号
	 * @return
	 */
	List<ParentClubRelationInfo> findInfoByPcrId(Session sess, Integer pcrId);
	/**
	 * 根据用户编号获取家长群关联信息
	 * @author zdf
	 * 2019-9-27 上午10:45:52
	 * @param sess
	 * @param userId 用户编号
	 * @return
	 */
	List<ParentClubRelationInfo> findInfoByUserId(Session sess, Integer userId);

}
