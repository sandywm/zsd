package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.StudyDetailInfo;

public interface StudyDetailDao {

	/**
	 * 根据主键获取学习详情实体
	 * @author wm
	 * @date 2019-6-2 上午10:53:50
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	StudyDetailInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加学习详情
	 * @author wm
	 * @date 2019-6-2 上午10:54:03
	 * @param sess
	 * @param sd
	 */
	void save(Session sess,StudyDetailInfo sd);
	
	/**
	 * 删除指定学习详情
	 * @author wm
	 * @date 2019-6-2 上午10:54:12
	 * @param sess
	 * @param sd
	 */
	void delete(Session sess,StudyDetailInfo sd);
	
	/**
	 * 修改学习详情
	 * @author wm
	 * @date 2019-6-2 上午10:54:22
	 * @param sess
	 * @param sd
	 */
	void update(Session sess,StudyDetailInfo sd);
	
	/**
	 * 根据学习记录编号获取学习详情列表
	 * @author wm
	 * @date 2019-6-2 上午10:54:29
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @return
	 */
	List<StudyDetailInfo> findInfoByLogId(Session sess,Integer studyLogId);
	
	/**
	 * 根据学习记录编号获取当前级知识点所有答对的再次诊断记录列表
	 * @author wm
	 * @date 2019-6-3 上午11:52:43
	 * @param sess
	 * @param studyLogId 学习记录编号
	 * @param loreId 当前知识典编号
	 * @param loreTypeName 答题类型
	 * @return
	 */
	List<StudyDetailInfo> findCurrentRightInfoByLogId(Session sess,Integer studyLogId,Integer loreId,String loreTypeName);
	
	
}
