package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.HwStudyTjInfo;

public interface HwStudyTjDao {

	/**
	 * 根据主键获取佳通作业统计信息实体
	 * @author wm
	 * @date 2019-7-27 下午05:00:21
	 * @param sess
	 * @param id
	 * @return
	 */
	HwStudyTjInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加家庭作业做题统计信息
	 * @author wm
	 * @date 2019-7-27 下午05:00:35
	 * @param sess
	 * @param hwTj
	 */
	void save(Session sess,HwStudyTjInfo hwTj);
	
	/**
	 * 修改家庭作业做题统计信息
	 * @author wm
	 * @date 2019-7-27 下午05:00:57
	 * @param sess
	 * @param hwTj
	 */
	void update(Session sess,HwStudyTjInfo hwTj);
	
	/**
	 * 根据条件分页获取家庭作业做题统计信息列表
	 * @author wm
	 * @date 2019-7-27 下午05:01:11
	 * @param sess
	 * @param hwSendId 发送作业编号（0表示全部）
	 * @param stuId 学生编号（0表示全部）
	 * @param comStatus 完成状态（-1表示全部，0-未完成，1-按时完成，2-补做完成）
	 * @param pageFlag 分页标记（true-分页）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<HwStudyTjInfo> findPageInfoByOpt(Session sess,Integer hwSendId,Integer stuId,Integer comStatus,boolean pageFlag,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据条件获取家庭作业做题统计信息记录条数
	 * @author wm
	 * @date 2019-7-27 下午05:02:41
	 * @param sess
	 * @param hwSendId 发送作业编号（0表示全部）
	 * @param stuId 学生编号（0表示全部）
	 * @param comStatus 完成状态（-1表示全部，0-未完成，1-按时完成，2-补做完成）
	 * @return
	 */
	Integer getCountByOpt(Session sess,Integer hwSendId,Integer stuId,Integer comStatus);
	
	
}
