package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.StudentPayOrderInfo;

/** 
 * @author zong
 * @version 2019-5-24 下午03:29:31
 */
public interface StudentPayOrderInfoDao {
	/**
	 * 主键加载学生购买信息实体
	 * @author zong
	 * 2019-5-24下午04:11:29
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	StudentPayOrderInfo get(Session sess,int id);
	
	/**
	 * 保存学生购买实体
	 * @author zong
	 * 2019-5-24下午04:12:34
	 * @param sess
	 * @param sPayOrderInfo 学生购买实体
	 */
	void save(Session sess,StudentPayOrderInfo sPayOrderInfo);
	
	/**
	 * 删除学生购买信息实体
	 * @author zong
	 * 2019-5-24下午04:13:19
	 * @param sess
	 * @param sPayOrderInfo 学生购买实体
	 */
	void delete(Session sess,StudentPayOrderInfo sPayOrderInfo);
	
	/**
	 * 删除指定学生购买信息实体
	 * @author zong
	 * 2019-5-24下午04:14:02
	 * @param sess
	 * @param id 主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新学生购买信息
	 * @author zong
	 * 2019-5-24下午04:22:19
	 * @param sess
	 * @param sPayOrderInfo 学生购买实体
	 */
	void update(Session sess,StudentPayOrderInfo sPayOrderInfo);
	/**
	 * 根据网络导师绑定主键 完成状态获取信息
	 * @author zong
	 * 2019-5-24下午05:07:47
	 * @param sess
	 * @param ntsId 老师学生绑定主键
	 * @param comSta 完成状态
	 * @return
	 */
	List<StudentPayOrderInfo> findSpayOrderInfoByOpt(Session sess,Integer ntsId,Integer comSta);
	/**
	 * 根据网络导师绑定主键 完成状态分页获取信息
	 * @author zong
	 * 2019-5-27下午04:25:04
	 * @param sess
	 * @param ntsId 老师学生绑定主键
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<StudentPayOrderInfo> findSpayOrderInfoByOpt(Session sess,Integer ntsId, Integer pageNo,Integer pageSize);
	/**
	 * 根据网络导师绑定主键 完成状态获取记录数
	 * @author zong
	 * 2019-5-27下午04:29:35
	 * @param ntsId
	 * @return
	 */
	Integer getspOrderInfoCount(Session sess,Integer ntsId);

}
