package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.GradeSubject;

public interface GradeSubjectDao {
	/**
	 * 根据主键加载年级科目实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的年级科目的主键值
	 * @return 加载的年级科目PO
	 */
	GradeSubject get(Session sess,int id);
	
	/**
	 * 保存年级科目信息实体，新增一条年级科目记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param gSub 保存的年级科目实例
	 */
	void save(Session sess,GradeSubject gSub);
	
	/**
	 * 删除年级科目实体，删除一条年级科目记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param gSub 删除的年级科目实体
	 */
	void delete(Session sess,GradeSubject gSub);
	
	/**
	 * 根据主键删除年级科目实体，删除一条年级科目记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除年级科目的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条年级科目记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param gSub 需要更新的年级科目
	 */
	void update(Session sess,GradeSubject gSub);
	
	/**
	 * 根据条件分页获取年级学科列表
	 * @author Administrator
	 * @date 2019-4-28 下午09:46:55 
	 * @param sess
	 * @param gradeName 年级名次(""表示全部)
	 * @param subId 学科编号（0表示全部）
	 * @param shoolType 学段（0：全部,1：小学,2：初中,3：高中）
	 * @param showStatus 显示状态（-1：全部,0：显示，1：隐藏）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<GradeSubject> findPageInfoByOpt(Session sess,String gradeName,Integer subId,Integer shoolType,
			Integer showStatus,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据条件获取年级学科记录条数
	 * @author Administrator
	 * @date 2019-4-28 下午09:43:45 
	 * @param sess
	 * @param gradeName 年级名次(""表示全部)
	 * @param subId 学科编号（0表示全部）
	 * @param shoolType 学段（0：全部,1：小学,2：初中,3：高中）
	 * @param showStatus 显示状态（-1：全部,0：显示，1：隐藏）
	 * @return
	 */
	Integer getCountByOpt(Session sess,String gradeName,Integer subId,Integer shoolType,
			Integer showStatus);
	
	/**
	 * 获取指定编号的年级学科列表
	 * @author wm
	 * @date 2019-4-28 下午10:37:19 
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	List<GradeSubject> findSpecInfoById(Session sess,Integer id);
	
	/**
	 * 获取指定学科编号下的学科年级列表
	 * @author wm
	 * @date 2019-4-28 下午11:12:41 
	 * @param sess
	 * @param subId 学科编号
	 * @return
	 */
	List<GradeSubject> findSpecInfoBySubId(Session sess,Integer subId);
}
