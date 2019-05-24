package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.StuSubjectEduInfo;

public interface StuSubjectEduDao {

	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-5-24 下午04:44:20
	 * @param sess
	 * @param id
	 * @return
	 */
	StuSubjectEduInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加学生学科教材信息表
	 * @author wm
	 * @date 2019-5-24 下午04:44:29
	 * @param sess
	 * @param sse
	 */
	void add(Session sess,StuSubjectEduInfo sse);
	
	/**
	 * 修改学生学科教材信息表
	 * @author wm
	 * @date 2019-5-24 下午04:44:33
	 * @param sess
	 * @param sse
	 */
	void update(Session sess,StuSubjectEduInfo sse);
	
	/**
	 * 根据学生编号、学科编号获取学生学科教材信息列表
	 * @author wm
	 * @date 2019-5-24 下午04:44:36
	 * @param sess
	 * @param stuId 学生编号
	 * @param subId 学科编号
	 * @return
	 */
	List<StuSubjectEduInfo> findInfoByOpt(Session sess,Integer stuId,Integer subId);
	
	
}
