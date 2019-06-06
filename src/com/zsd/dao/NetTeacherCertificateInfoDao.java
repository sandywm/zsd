package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.NetTeacherCertificateInfo;

/** 
 * @author zong
 * @version 2019-5-13 下午05:20:07
 */
public interface NetTeacherCertificateInfoDao {
	/**
	 * 根据主键加载网络导师证件信息实体
	 * @description
	 * @author zong
	 * @date 2019-5-13下午05:23:03
	 * @param sess
	 * @param id 需要加载的网络导师证件信息的主键值
	 * @return 加载的网络导师证件信息
	 */
	NetTeacherCertificateInfo get(Session sess,int id);
	
	/**
	 * 保存网络导师证件信息信息实体，新增一条网络导师证件信息记录
	 * @author zong
	 * 2019-5-13下午05:23:46
	 * @param sess
	 * @param ntcInfo 网络导师证件实体
	 */
	void save(Session sess,NetTeacherCertificateInfo ntcInfo);
	
	/**
	 * 删除网络导师证件信息实体，删除一条网络导师证件信息记录
	 * @author zong
	 * 2019-5-13下午05:24:41
	 * @param sess
	 * @param ntcInfo 网络导师证件实体
	 */
	void delete(Session sess,NetTeacherCertificateInfo ntcInfo);
	
	/**
	 * 根据主键删除网络导师证件信息实体，删除一条网络导师证件信息记录
	 * @author zong
	 * 2019-5-13下午05:25:14
	 * @param sess
	 * @param id 需要删除网络导师证件信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条网络导师证件信息记录
	 * @author zong
	 * 2019-5-13下午05:25:44
	 * @param sess
	 * @param ntcInfo需要更新的网络导师证件信息
	 */
	void update(Session sess,NetTeacherCertificateInfo ntcInfo);
	
	/**
	 * 根据主键获取网络导师证件信息记录
	 * @author zong
	 * 2019-5-13下午05:26:52
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	List<NetTeacherCertificateInfo> getEntityByid(Session sess,Integer id);
	/**
	 * 根据网络导师编号获取审核信息
	 * @author zong
	 * 2019-6-5上午10:37:03
	 * @param sess
	 * @param teaId
	 * @return
	 */
	List<NetTeacherCertificateInfo> getNtcByTeaId(Session sess,Integer teaId);
	

}
