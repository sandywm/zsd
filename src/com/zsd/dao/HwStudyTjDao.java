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
	
	/**
	 * 根据条件获取指定学生家庭作业列表（发送时间降序排列）
	 * @author wm
	 * @date 2019-8-13 上午11:07:35
	 * @param sess
	 * @param hwType 作业类型(0-全部,1-家庭作业,2-课后复习,3-课前预习)
	 * @param subId 学科编号(0表示全部)
	 * @param stuId 学生编号
	 * @param comStatus 完成状态（-1表示全部，0-未完成，1-按时完成，2-补做完成）
	 * @param sDate 开始时间(针对发送时间)
	 * @param eDate 结束时间(针对发送时间)
	 * @param pageFlag pageFlag 分页标记（true-分页）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<HwStudyTjInfo> findPageInfoByOpt_1(Session sess,Integer hwType,Integer subId,Integer stuId,Integer comStatus,String sDate,String eDate,
			boolean pageFlag,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据条件获取指定学生家庭作业记录条数
	 * @author wm
	 * @date 2019-8-13 上午11:09:04
	 * @param sess
	 * @param hwType 作业类型(0-全部,1-家庭作业,2-课后复习,3-课前预习)
	 * @param subId 学科编号(0表示全部)
	 * @param stuId 学生编号
	 * @param comStatus 完成状态（-1表示全部，0-未完成，1-按时完成，2-补做完成）
	 * @param sDate 开始时间(针对发送时间)
	 * @param eDate 结束时间(针对发送时间)
	 * @return
	 */
	Integer getCountByOpt_1(Session sess,Integer hwType,Integer subId,Integer stuId,Integer comStatus,String sDate,String eDate);
	
	/**
	 * 根据发送作业编号、学生编号获取家庭作业记录
	 * @author wm
	 * @date 2019-8-13 上午11:26:28
	 * @param sess
	 * @param sendHwId 发送作业编号
	 * @param stuId 学生编号
	 * @return
	 */
	List<HwStudyTjInfo> findInfoBySendHwId(Session sess,Integer sendHwId,Integer stuId);
	
	/**
	 * 获取今天以前的指定学生的历史作业记录
	 * @author wm
	 * @date 2019-8-15 下午05:04:59
	 * @param sess
	 * @param hwType 作业类型(0-全部,1-家庭作业,2-课后复习,3-课前预习)
	 * @param subId 学科编号(0表示全部)
	 * @param stuId 学生编号
	 * @param comStatus 完成状态（-1表示全部，0-未完成，1-按时完成，2-补做完成）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<HwStudyTjInfo> findPageInfoByOpt_2(Session sess,Integer hwType,Integer subId,Integer stuId,Integer comStatus,Integer pageNo,Integer pageSize);
	
}
