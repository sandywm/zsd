package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.HwStudyDetailInfo;

public interface HwStudyDetailDao {

	/**
	 * 根据主键获取家庭作业做题记录信息实体
	 * @author wm
	 * @date 2019-7-28 上午11:53:58
	 * @param sess
	 * @param id
	 * @return
	 */
	HwStudyDetailInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加家庭作业做题记录
	 * @author wm
	 * @date 2019-7-28 上午11:54:26
	 * @param sess
	 * @param hwsd
	 */
	void save(Session sess,HwStudyDetailInfo hwsd);
	
	/**
	 * 修改家庭作业做题记录信息
	 * @author wm
	 * @date 2019-7-28 上午11:55:22
	 * @param sess
	 * @param hwsd
	 */
	void update(Session sess,HwStudyDetailInfo hwsd);
	
	/**
	 * 根据条件获取家庭作业做题记录列表
	 * @author wm
	 * @date 2019-7-28 上午11:55:31
	 * @param sess
	 * @param sendHwId 发布家庭作业编号
	 * @param hwTjId 家庭作业统计编号(0表示全部)
	 * @param queId 题库编号（0表示全部）
	 * @param queArea 题库范围(（""表示全部）)
	 * @return
	 */
	List<HwStudyDetailInfo> findInfoByOpt(Session sess,Integer sendHwId,Integer hwTjId,Integer queId,String queArea);
}
