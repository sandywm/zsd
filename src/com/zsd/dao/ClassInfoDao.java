package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.ClassInfo;

public interface ClassInfoDao {
	/**
	 * 根据主键加载班级信息
	 * @author zong
	 * @date  2019-5-5 下午04:25:13
	 * @param sess
	 * @param id 主键值
	 * @return
	 */
	ClassInfo get(Session sess,int id);
	
	/**
	 * 保存班级信息实体
	 * @author zong
	 * @date  2019-5-5 下午04:25:58
	 * @param sess
	 * @param classInfo 班级实体
	 */
	void save(Session sess,ClassInfo classInfo);
	
	/**
	 * 删除班级信息实体
	 * @author zong
	 * @date  2019-5-5 下午04:26:55
	 * @param sess
	 * @param classInfo 班级实体
	 */
	void delete(Session sess,ClassInfo classInfo);
	
	/**
	 * 根据主键删除班级信息实体，删除一条班级信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-5 下午04:27:21
	 * @param sess
	 * @param id 需要删除班级信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条班级信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-5 下午04:27:58
	 * @param sess
	 * @param roleInfo 需要更新的班级信息
	 */
	void update(Session sess,ClassInfo classInfo);
	
	/**
	 * 获取所有班级列表
	 * @description
	 * @author zong
	 * @date 2019-5-5 下午04:28:35
	 * @param sess
	 * @return
	 */
	List<ClassInfo> findClassInfo(Session sess);

}
