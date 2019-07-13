package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.GuideStudyInfo;

public interface GuideStudyDao {

	/**
	 * 根据主键获取指导学习信息
	 * @author wm
	 * @date 2019-7-13 上午09:40:52
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	GuideStudyInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加指导学习信息
	 * @author wm
	 * @date 2019-7-13 上午09:41:12
	 * @param sess
	 * @param gs
	 */
	void save(Session sess,GuideStudyInfo gs);
	
	/**
	 * 修改指定的指导学习信息
	 * @author wm
	 * @date 2019-7-13 上午09:41:22
	 * @param sess
	 * @param gs
	 */
	void update(Session sess,GuideStudyInfo gs);
	
	/**
	 * 根据条件是否分页获取指导学习记录列表
	 * @author wm
	 * @date 2019-7-13 上午09:41:34
	 * @param sess
	 * @param stuId 学生编号（0表示全部）
	 * @param guideStatus 指导状态(0表示全部，1(未指导),2(已指导))
	 * @param guideUserId 指导人（0表示全部）
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @param pageFlag 分页标记(true:分页)
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<GuideStudyInfo> findPageInfoByOpt(Session sess,Integer stuId,Integer guideStatus,
			Integer guideUserId,String sDate,String eDate,boolean pageFlag,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据条件获取指导学习记录条数
	 * @author wm
	 * @date 2019-7-13 上午09:41:57
	 * @param sess
	 * @param stuId 学生编号（0表示全部）
	 * @param guideStatus 指导状态(0表示全部，1(未指导),2(已指导))
	 * @param guideUserId 指导人（0表示全部）
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @return
	 */
	Integer getCountByOpt(Session sess,Integer stuId,Integer guideStatus,
			Integer guideUserId,String sDate,String eDate);
}
