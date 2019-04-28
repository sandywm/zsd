package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.Edition;

public interface EditionDao {

	/**
	 * 根据主键加载出版社信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的出版社信息的主键值
	 * @return 加载的出版社信息PO
	 */
	Edition get(Session sess,int id);
	
	/**
	 * 保存出版社信息信息实体，新增一条出版社信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param edi 保存的出版社信息实例
	 */
	void save(Session sess,Edition edi);
	
	/**
	 * 删除出版社信息实体，删除一条出版社信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param edi 删除的出版社信息实体
	 */
	void delete(Session sess,Edition edi);
	
	/**
	 * 根据主键删除出版社信息实体，删除一条出版社信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除出版社信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条出版社信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param edi 需要更新的出版社信息
	 */
	void update(Session sess,Edition edi);
	
	/**
	 * 根据显示状态获取所有出版社列表
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:20:36
	 * @param sess
	 * @param showStatus 显示状态(-1:表示全部,0:显示,1:隐藏)
	 * @return
	 */
	List<Edition> findInfoByShowStatus(Session sess,Integer showStatus);
}
