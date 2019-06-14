package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.StudyStuTjInfo;

/** 
 * @author zong
 * @version 2019-6-9 上午09:58:28
 */
public interface StudyStuTjInfoDao {
	/**
	 * 根据主键加载学生学习统计信息实体
	 * @description
	 * @author zong
	 * @date 2019-6-9 上午09:58:28
	 * @param sess
	 * @param id 需要加载的学生学习统计信息的主键值
	 * @return 加载的学生学习统计信息PO
	 */
	StudyStuTjInfo get(Session sess,int id);
	
	/**
	 * 保存学生学习统计信息信息实体，新增一条学生学习统计信息记录
	 * @description
	 * @author zong
	 * @date 2019-6-9 上午09:58:28
	 * @param sess
	 * @param sstjInfo 保存的学生学习统计信息实例
	 */
	void save(Session sess,StudyStuTjInfo sstjInfo);
	
	/**
	 * 删除学生学习统计信息实体，删除一条学生学习统计信息记录
	 * @description
	 * @author zong
	 * @date 2019-6-9 上午09:58:28
	 * @param sess
	 * @param sstjInfo 删除的学生学习统计信息实体
	 */
	void delete(Session sess,StudyStuTjInfo sstjInfo);
	
	/**
	 * 根据主键删除学生学习统计信息实体，删除一条学生学习统计信息记录
	 * @description
	 * @author zong
	 * @date 2019-6-9 上午09:58:28
	 * @param sess
	 * @param id 需要删除学生学习统计信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条学生学习统计信息记录
	 * @description
	 * @author zong
	 * @date 2019-6-9 上午09:58:28
	 * @param sess
	 * @param sstjInfo 需要更新的学生学习统计信息
	 */
	void update(Session sess,StudyStuTjInfo sstjInfo);
	/**
	 * 根据用户编号,科目编号,开始,结束时间获取学生学习统计记录
	 * @author zong
	 * 2019-6-9上午10:15:13
	 * @param userId 用户编号
	 * @param subId 科目编号
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	List<StudyStuTjInfo> findInfoByOpt(Session sess,Integer userId,Integer subId,String startTime,String endTime);
	/**
	 * 根据学习时间、学生编号、学科编号获取学生个人学习统计列表
	 * @author zong
	 * 2019年6月13日下午5:31:00
	 * @param sess
	 * @param userId 学生编号
	 * @param subId 学科编号
	 * @param studyDate 学习时间
	 * @return
	 */
	List<StudyStuTjInfo> findInfoByOption(Session sess,Integer userId,Integer subId,String studyDate);
	
	
}
