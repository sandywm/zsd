package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.UserClassInfo;

public interface UserClassInfoDao {
	/**
	 * 根据主键加载用户班级信息
	 * @author zong
	 * @date  2019-5-5 下午04:25:13
	 * @param sess
	 * @param id 主键值
	 * @return
	 */
	UserClassInfo get(Session sess,int id);
	
	/**
	 * 保存用户班级信息实体
	 * @author zong
	 * @date  2019-5-5 下午04:25:58
	 * @param sess
	 * @param userClassInfo 用户班级实体
	 */
	void save(Session sess,UserClassInfo userClassInfo);
	
	/**
	 * 删除用户班级信息实体
	 * @author zong
	 * @date  2019-5-5 下午04:26:55
	 * @param sess
	 * @param userClassInfo 用户班级实体
	 */
	void delete(Session sess,UserClassInfo userClassInfo);
	
	/**
	 * 根据主键删除用户班级信息实体，删除一条用户班级信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-5 下午04:27:21
	 * @param sess
	 * @param id 需要删除用户班级信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条用户班级信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-5 下午04:27:58
	 * @param sess
	 * @param userClassInfo 需要更新的用户班级信息
	 */
	void update(Session sess,UserClassInfo userClassInfo);
	
	/**
	 * 获取所有用户班级列表
	 * @description
	 * @author zong
	 * @date 2019-5-5 下午04:28:35
	 * @param sess
	 * @return
	 */
	List<UserClassInfo> findClassInfo(Session sess);
	/**
	 * 通过用户编号查看用户班级信息
	 * @author zong
	 * 2019-5-21下午05:36:35
	 * @param userId 用户编号
	 * @return
	 */
	List<UserClassInfo> findUcInfoByUserId(Session sess,Integer userId);
	
	/**
	 * 根据用户编号，角色编号获取用户班级信息实体
	 * @author wm
	 * @date 2019-5-24 上午11:20:21
	 * @param sess
	 * @param userId 用户编号
	 * @param roleId 角色编号
	 * @return
	 */
	UserClassInfo getEntityByOpt(Session sess,Integer userId,Integer roleId);

	/**
	 * 根据班级编号,角色编号获取用户班级信息
	 * @author zong
	 * 2019-6-2上午09:09:49
	 * @param sess
	 * @param classId 班级编号
	 * @param roleId 角色编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<UserClassInfo> findUcInfoByOpt(Session sess,Integer classId,Integer roleId,Integer pageNo,Integer pageSize);
	/**
	 * 根据班级编号,角色编号获取用户班级信息记录数
	 * @author zong
	 * 2019-6-2上午09:09:49
	 * @param sess
	 * @param classId 班级编号
	 * @param roleId 角色编号
	 * @return
	 */
	Integer getUciByOpt(Session sess,Integer classId,Integer roleId);

}
