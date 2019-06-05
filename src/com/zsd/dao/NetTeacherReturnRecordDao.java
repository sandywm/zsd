package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.NetTeacherReturnRecord;

/** 
 * @author zong
 * @version 2019-5-28 下午03:28:38
 */
public interface NetTeacherReturnRecordDao {
	/**
	 * 主键加载返现信息实体
	 * @author zong
	 * 2019-5-24下午04:11:29
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	NetTeacherReturnRecord get(Session sess,int id);
	
	/**
	 * 保存返现实体
	 * @author zong
	 * 2019-5-24下午04:12:34
	 * @param sess
	 * @param nTrRecord 返现实体
	 */
	void save(Session sess,NetTeacherReturnRecord nTrRecord);
	
	/**
	 * 删除返现信息实体
	 * @author zong
	 * 2019-5-24下午04:13:19
	 * @param sess
	 * @param nTrRecord 返现实体
	 */
	void delete(Session sess,NetTeacherReturnRecord nTrRecord);
	
	/**
	 * 删除指定返现信息实体
	 * @author zong
	 * 2019-5-24下午04:14:02
	 * @param sess
	 * @param id 主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新返现信息
	 * @author zong
	 * 2019-5-24下午04:22:19
	 * @param sess
	 * @param nTrRecord 返现实体
	 */
	void update(Session sess,NetTeacherReturnRecord nTrRecord);
	/**
	 * 根据网络导师编号获取返现记录
	 * @author zong
	 * 2019-5-28下午03:33:13
	 * @param sess
	 * @param ntId 网络导师编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<NetTeacherReturnRecord> findnTrRecordByNtId(Session sess, Integer ntId,Integer pageNo,Integer pageSize);
	/**
	 * 根据网络导师编号获取返现记录数
	 * @author zong
	 * 2019-5-28下午03:34:53
	 * @param sess
	 * @param ntId 网络导师编号
	 * @return
	 */
	Integer getnTrRecordCount(Session sess, Integer ntId);
}
