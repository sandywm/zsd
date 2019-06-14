package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.StudyAllTjInfo;

/** 
 * @author zong
 * @version 2019-6-9 上午09:58:43
 */
public interface StudyAllTjInfoDao {
	/**
	 * 根据主键加载全平台知识点信息实体
	 * @description
	 * @author zong
	 * @date 2019-6-9 上午09:58:43
	 * @param sess
	 * @param id 需要加载的全平台知识点信息的主键值
	 * @return 加载的全平台知识点信息PO
	 */
	StudyAllTjInfo get(Session sess,int id);
	
	/**
	 * 保存全平台知识点信息信息实体，新增一条全平台知识点信息记录
	 * @description
	 * @author zong
	 * @date 2019-6-9 上午09:58:43
	 * @param sess
	 * @param satjInfo 保存的全平台知识点信息实例
	 */
	void save(Session sess,StudyAllTjInfo satjInfo);
	
	/**
	 * 删除全平台知识点信息实体，删除一条全平台知识点信息记录
	 * @description
	 * @author zong
	 * @date 2019-6-9 上午09:58:43
	 * @param sess
	 * @param satjInfo 删除的全平台知识点信息实体
	 */
	void delete(Session sess,StudyAllTjInfo satjInfo);
	
	/**
	 * 根据主键删除全平台知识点信息实体，删除一条全平台知识点信息记录
	 * @description
	 * @author zong
	 * @date 2019-6-9 上午09:58:43
	 * @param sess
	 * @param id 需要删除全平台知识点信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条全平台知识点信息记录
	 * @description
	 * @author zong
	 * @date 2019-6-9 上午09:58:43
	 * @param sess
	 * @param satjInfo 需要更新的全平台知识点信息
	 */
	void update(Session sess,StudyAllTjInfo satjInfo);
	/**
	 * 根据科目编号,开始,结束时间获取全平台知识点记录
	 * @author zong
	 * 2019-6-9上午10:46:41
	 * @param sess
	 * @param subId 科目编号
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 */
	List<StudyAllTjInfo> findInfoByOpt(Session sess,Integer subId,String startTime,String endTime);
	/**
	 * 根据科目编号 ,学习时间获取全平台知识点记录
	 * @author zong
	 * 2019年6月13日下午5:42:11
	 * @param sess
	 * @param subId 学科编号
	 * @param studyDate 学习时间
	 * @return
	 */
	List<StudyAllTjInfo> findInfoByOpt(Session sess,Integer subId,String studyDate);

}
