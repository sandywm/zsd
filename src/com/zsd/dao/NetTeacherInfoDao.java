package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.NetTeacherInfo;

public interface NetTeacherInfoDao {
	/**
	 * 根据主键加载网络导师信息
	 * @author zong
	 * @date  2019-5-4 下午02:47:26
	 * @param sess
	 * @param id 需要加载的网络导师主键值
	 * @return 加载的网络导师信息
	 */
	NetTeacherInfo get(Session sess,int id);
	
	/**
	 * 保存网络导师信息
	 * @author zong
	 * @date  2019-5-4 下午02:49:13
	 * @param sess
	 * @param nt 网络导师实体
	 */
	void save(Session sess,NetTeacherInfo nt);
	
	/**
	 * 删除网络导师实体信息
	 * @author zong
	 * @date  2019-5-4 下午02:50:16
	 * @param sess
	 * @param nt 网络导师实体
	 */
	void delete(Session sess,NetTeacherInfo nt);
	
	/**
	 * 根据主键删除网络导师信息实体
	 * @description
	 * @author zong
	 * @date 2019-5-4 下午02:50:16
	 * @param sess
	 * @param id 需要删除网络导师信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条网络导师信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-4 下午02:50:16
	 * @param sess
	 * @param nt 需要更新的出版社信息
	 */
	void update(Session sess,NetTeacherInfo nt);
	/**
	 * 根据用户编号查询导师信息
	 * @author zong
	 * 2019-5-14下午03:50:39
	 * @param sess
	 * @param uid 用户编号
	 * @return
	 */
	List<NetTeacherInfo> findntInfoByuserId(Session sess,Integer uid);
	/**
	 * 根据网络导师编号获取导师信息
	 * @author zdf
	 * 2019-8-30 下午04:29:46
	 * @param sess
	 * @param Id 主键
	 * @return
	 */
	List<NetTeacherInfo> findntInfoByTeaId(Session sess,Integer Id);
	/**
	 * 根据条件获取网络导师信息
	 * @author zong
	 * 2019-5-16上午09:52:44
	 * @param sess
	 * @param accName 账户
	 * @param realName 真实姓名
	 * @param checkSta 审核状态
	 * @param sDate 注册时间(开始)
	 * @param eDate 注册时间(结束)
	 * @return
	 */
	List<NetTeacherInfo> getNtByOption(Session sess,String accName,String realName,Integer checkSta,String sDate,String eDate, Integer pageNo,
			Integer pageSize);
	/**
	 * 根据条件获取网络导师记录数
	 * @author zong
	 * 2019-5-16下午04:04:44
	 * @param sess
	 * @param accName 账户
	 * @param realName 真实姓名
	 * @param checkSta 审核状态
	 * @param sDate 注册时间(开始)
	 * @param eDate 注册时间(结束)
	 * @return
	 */
	Integer  getNtByOptionCount (Session sess,String accName,String realName,Integer checkSta,String sDate,String eDate);
	/**
	 * 根据用户编号判断网络导师是否审核通过
	 * @author zdf
	 * 2019-9-19 下午02:59:56
	 * @param sess
	 * @param userId 用户编号
	 * @return
	 */
	boolean  checkNtInfoByUserId(Session sess,Integer userId);
	

}
