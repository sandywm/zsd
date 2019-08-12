package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.SendHwInfo;

public interface SendHwDao {

	/**
	 * 根据主键获取发送家庭作业实体
	 * @author wm
	 * @date 2019-7-27 下午04:53:41
	 * @param sess
	 * @param id
	 * @return
	 */
	SendHwInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加发送家庭作业
	 * @author wm
	 * @date 2019-7-27 下午04:53:56
	 * @param sess
	 * @param sw
	 */
	void save(Session sess,SendHwInfo sw);
	
	/**
	 * 修改发送家庭作业
	 * @author wm
	 * @date 2019-7-27 下午04:54:04
	 * @param sess
	 * @param sw
	 */
	void update(Session sess,SendHwInfo sw);
	
	/**
	 * 根据条件分页获取发布家庭作业记录列表
	 * @author wm
	 * @date 2019-7-27 下午04:54:15
	 * @param sess
	 * @param sendUserId 发送者编号(0表示全部)
	 * @param classId 接收班级编号(0表示全部)
	 * @param hwType 作业类型(0-全部,1,2,3)
	 * @param inUse 有效状态（-1全部，0：有效，1：无效）
	 * @param checkStatus 作业检查状态(-1全部，0:未检查，1:已检查)
	 * @param sDate 开始日期
	 * @param eDate 结束日期
	 * @param pageFlag 分页标记（true-分页）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<SendHwInfo> findPageInfoByOpt(Session sess,Integer sendUserId,Integer classId,Integer hwType,Integer checkStatus,Integer inUse,
			String sDate,String eDate,boolean pageFlag,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据条件获取发布家庭作业记录条数
	 * @author wm
	 * @date 2019-7-27 下午04:55:40
	 * @param sess
	 * @param sendUserId 发送者编号(0表示全部)
	 * @param classId 接收班级编号(0表示全部)
	 * @param hwType 作业类型(0-全部,1,2,3)
	 * @param inUse 有效状态（-1全部，0：有效，1：无效）
	 * @param checkStatus 作业检查状态(-1全部，0:未检查，1:已检查)
	 * @param sDate 开始日期
	 * @param eDate 结束日期
	 * @return
	 */
	Integer getCountByOpt(Session sess,Integer sendUserId,Integer classId,Integer hwType,Integer checkStatus,Integer inUse,String sDate,String eDate);
	
	List<SendHwInfo> findPageInfoByOpt(Session sess,Integer subId,Integer classId,Integer hwType,Integer checkStatus,Integer inUse,
			String sDate,String eDate);
}
