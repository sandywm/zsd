package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.NetTeacherTxRecord;

/** 
 * @author zong
 * @version 2019-5-24 下午03:31:20
 */
public interface NetTeacherTxRecordDao {
	/**
	 * 主键加载提现信息实体
	 * @author zong
	 * 2019-5-24下午04:11:29
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	NetTeacherTxRecord get(Session sess,int id);
	
	/**
	 * 保存提现实体
	 * @author zong
	 * 2019-5-24下午04:12:34
	 * @param sess
	 * @param nTxRecord 提现实体
	 */
	void save(Session sess,NetTeacherTxRecord nTxRecord);
	
	/**
	 * 删除提现信息实体
	 * @author zong
	 * 2019-5-24下午04:13:19
	 * @param sess
	 * @param nTxRecord 提现实体
	 */
	void delete(Session sess,NetTeacherTxRecord nTxRecord);
	
	/**
	 * 删除指定提现信息实体
	 * @author zong
	 * 2019-5-24下午04:14:02
	 * @param sess
	 * @param id 主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新提现信息
	 * @author zong
	 * 2019-5-24下午04:22:19
	 * @param sess
	 * @param nTxRecord 提现实体
	 */
	void update(Session sess,NetTeacherTxRecord nTxRecord);
	/**
	 * 查看指定网络导师的提现记录
	 * @author zong
	 * 2019-5-24下午05:37:21
	 * @param sess
	 * @param ntId 网络导师编号
	 * @return
	 */
	List<NetTeacherTxRecord> findnTxReCordByNtId(Session sess,Integer ntId,Integer operId);
	/**
	 * 根据网络导师编号分页获取提现记录
	 * @author zong
	 * 2019-5-27上午11:22:28
	 * @param sess
	 * @param ntId 网络导师编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<NetTeacherTxRecord> findnTxReCordByNtId(Session sess,Integer userId, Integer pageNo,Integer pageSize);
	/**
	 * 根据网络导师编号获取提现记录数
	 * @author zong
	 * 2019-5-27下午03:48:02
	 * @param sess
	 * @param ntId 网络导师编号
	 * @return
	 */
	Integer  getnTxReCordCount(Session sess,Integer userId);
	/**
	 * 根据主键获取账单详情
	 * @author zdf
	 * 2019-9-11 下午03:04:51
	 * @param sess
	 * @param Id 主键 
	 * @return
	 */
	List<NetTeacherTxRecord> findnTxReCordById(Session sess,Integer Id);
}
