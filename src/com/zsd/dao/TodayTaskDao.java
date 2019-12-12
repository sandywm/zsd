package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.TodayTask;

public interface TodayTaskDao {

	/**
	 * 根据主键加载今日任务信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的今日任务信息的主键值
	 * @return 加载的今日任务信息PO
	 */
	TodayTask get(Session sess,int id);
	
	/**
	 * 保存今日任务信息信息实体，新增一条今日任务信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param task 保存的今日任务信息实例
	 */
	void save(Session sess,TodayTask task);
	
	/**
	 * 删除今日任务信息实体，删除一条今日任务信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param task 删除的今日任务信息实体
	 */
	void delete(Session sess,TodayTask task);
	
	/**
	 * 根据主键删除今日任务信息实体，删除一条今日任务信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除今日任务信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条今日任务信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param task 需要更新的今日任务信息
	 */
	void update(Session sess,TodayTask task);
	
	/**
	 * 根据学生编号、时间获取任务列表
	 * @author wm
	 * @date 2019-12-10 下午09:40:02 
	 * @param sess
	 * @param stuId 学生编号
	 * @param loreId 知识点编号（0表示全部）
	 * @param addDate 日期
	 * @param reviewStatus 复习状态(0：全部，1:未复习，2：已复习)
	 * @param confirmStatus 父母确认状态(0：全部，1:未确认，2：已确认)
	 * @return
	 */
	List<TodayTask> findInfoByOpt(Session sess,Integer stuId,Integer loreId,String addDate,Integer reviewStatus,Integer confirmStatus );
}
