package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.QuestionInfo;

/** 
 * @author zong
 * @version 2019-5-19 上午10:06:26
 */
public interface QuestionInfoDao {
	/**
	 * 根据主键加载问题信息实体
	 * @description
	 * @author zong
	 * @date 2019-5-19上午10:12:30
	 * @param sess
	 * @param id 需要加载的问题信息的主键值
	 * @return 加载的问题信息PO
	 */
	QuestionInfo get(Session sess,int id);
	
	/**
	 * 保存问题信息信息实体，新增一条问题信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-19上午10:12:30
	 * @param sess
	 * @param cpt 保存的问题信息实例
	 */
	void save(Session sess,QuestionInfo queInfo);
	
	/**
	 * 删除问题信息实体，删除问题信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-19上午10:12:30
	 * @param sess
	 * @param cpt 删除的问题信息实体
	 */
	void delete(Session sess,QuestionInfo queInfo);
	
	/**
	 * 根据主键删除问题信息实体，删除一条问题信息记录
	 * @description
	 * @author zong
	 * @date2019-5-19 下午03:15:19
	 * @param sess
	 * @param id 需要删除问题信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条问题信息记录
	 * @description
	 * @author zong
	 * @date2019-5-19 下午03:16:39
	 * @param sess
	 * @param cpt 需要更新的问题信息
	 */
	void update(Session sess,QuestionInfo queInfo);
		
	/**
	 * 根据学科,回复状态获取问题列表
	 * @author zong
	 * 2019-5-19上午10:35:01
	 * @param sess
	 * @param subId  学科编号
	 * @param readStatus 恢复状态
	 * @return
	 */
	List<QuestionInfo> findInfoByOpt(Session sess,Integer userId,Integer subId,Integer readStatus, Integer pageNo,
			Integer pageSize);
	/**
	 * 
	 * @author zong
	 * 2019-5-20上午09:30:34
	 * @param sess
	 * @param userId 用户编号
	 * @param subId 学科编号
	 * @param readStatus 恢复状态
	 * @return
	 */
	Integer  getInfoByOptCount(Session sess,Integer userId,Integer subId,Integer readStatus);
	/**
	 * 根据网络导师编号获取问题信息
	 * @author zong
	 * 2019-5-23上午11:42:13
	 * @param sess
	 * @param ntId 
	 * @return
	 */
	List<QuestionInfo> findInfoByntId(Session sess,Integer ntId);
	/**
	 * 根据学生编号回复状态查看问题信息
	 * @author zong
	 * 2019-5-23下午04:29:00
	 * @param sess
	 * @param stuId 用户编号
	 * @param readStatus 回复状态
	 * @param pageNo 第几页
	 * @param pageSize 每页多少条
	 * @return
	 */
	List<QuestionInfo> findInfoByStu(Session sess,Integer stuId,Integer readStatus, Integer pageNo,
			Integer pageSize);
	/**
	 * 根据学生编号回复状态查看问题信息记录数
	 * @author zong
	 * 2019-5-20上午09:30:34
	 * @param sess
	 * @param subId
	 * @param readStatus
	 * @return
	 */
	Integer  getInfoByStuCount(Session sess,Integer stuId,Integer readStatus);
	
}
