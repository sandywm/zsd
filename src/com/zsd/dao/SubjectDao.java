package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.Subject;

public interface SubjectDao {
	
	/**
	 * 根据主键加载科目信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的科目信息的主键值
	 * @return 加载的科目信息PO
	 */
	Subject get(Session sess,int id);
	
	/**
	 * 保存科目信息信息实体，新增一条科目信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param sub 保存的科目信息实例
	 */
	void save(Session sess,Subject sub);
	
	/**
	 * 删除科目信息实体，删除一条科目信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param sub 删除的科目信息实体
	 */
	void delete(Session sess,Subject sub);
	
	/**
	 * 根据主键删除科目信息实体，删除一条科目信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除科目信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条科目信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param sub 需要更新的科目信息
	 */
	void update(Session sess,Subject sub);
	
	/**
	 * 根据是否可见状态获取科目列表
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午04:46:02
	 * @param sess
	 * @param displayStatus 显示状态(-1:表示全部,0:显示,1:隐藏)
	 * @return
	 */
	List<Subject> findInfoByDisplayStatus(Session sess,Integer displayStatus);
}
