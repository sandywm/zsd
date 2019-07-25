package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.TeaQueInfo;

public interface TeaQueDao {

	/**
	 * 获取指定编号的实体信息
	 * @author wm
	 * @date 2019-7-25 下午04:17:06
	 * @param sess
	 * @param id
	 * @return
	 */
	TeaQueInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 新增老师自传家庭作业信息
	 * @author wm
	 * @date 2019-7-25 下午04:17:16
	 * @param sess
	 * @param tq
	 */
	void save(Session sess,TeaQueInfo tq);
	
	/**
	 * 修改老师自传家庭作业信息
	 * @author wm
	 * @date 2019-7-25 下午04:17:54
	 * @param sess
	 * @param tq
	 */
	void update(Session sess,TeaQueInfo tq);
	
	/**
	 * 根据条件是否分页获取老师自传家庭作业信息列表
	 * @author wm
	 * @date 2019-7-25 下午04:18:09
	 * @param sess
	 * @param loreId 知识点编号(0表示全部)
	 * @param teaId 老师编号(0表示全部)
	 * @param pageFlag 分页标记(true：分页,false：不分页)
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<TeaQueInfo> findInfoByOpt(Session sess,Integer loreId,Integer teaId,boolean pageFlag,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据条件是否获取老师自传家庭作业信息列表记录条数
	 * @author wm
	 * @date 2019-7-25 下午04:18:59
	 * @param sess
	 * @param loreId 知识点编号(0表示全部)
	 * @param teaId 老师编号(0表示全部)
	 * @return
	 */
	Integer getCountByOpt(Session sess,Integer loreId,Integer teaId);
}
