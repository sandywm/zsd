package com.zsd.dao;

import org.hibernate.Session;

import com.zsd.module.NetTeacherStudent;

/**
 * 网络导师学生Dao
 * @author zong
 * @date 2019-4-30
 */
public interface NetTeacherStudentDao {
	/**
	 * 根据主键加载网络导师学生信息实体
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 网络导师学生主键值
	 * @return 网络导师学生信息PO
	 */
	NetTeacherStudent get(Session sess,int id);
	
	/**
	 * 保存网络导师学生信息信息实体，新增一条网络导师学生信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param nts 保存的网络导师学生信息实例
	 */
	void save(Session sess,NetTeacherStudent nts);
	
	/**
	 * 删除网络导师学生信息实体，删除一条网络导师学生信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param nts 删除的网络导师学生信息实体
	 */
	void delete(Session sess,NetTeacherStudent nts);
	
	/**
	 * 根据主键删除网络导师学生信息实体，删除一条网络导师学生信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除网络导师学生信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条网络导师学生信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param nts 需要更新的网络导师学生信息
	 */
	void update(Session sess,NetTeacherStudent nts);
	

}
