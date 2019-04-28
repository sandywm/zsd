package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.Education;

public interface EducationDao {
	/**
	 * 根据主键加载教材信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的教材信息的主键值
	 * @return 加载的教材信息PO
	 */
	Education get(Session sess,int id);
	
	/**
	 * 保存教材信息信息实体，新增一条教材信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param edu 保存的教材信息实例
	 */
	void save(Session sess,Education edu);
	
	/**
	 * 删除教材信息实体，删除一条教材信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param edu 删除的教材信息实体
	 */
	void delete(Session sess,Education edu);
	
	/**
	 * 根据主键删除教材信息实体，删除一条教材信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除教材信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条教材信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param edu 需要更新的教材信息
	 */
	void update(Session sess,Education edu);
	
	/**
	 * 获取指定主键编号的教材详细信息
	 * @author wm
	 * @date 2019-4-28 下午10:55:52 
	 * @param sess
	 * @param id 主键编号
	 * @return
	 */
	List<Education> findSpecInfoById(Session sess,Integer id);
	
	/**
	 * 根据条件分页获取教材详细信息列表
	 * @author wm
	 * @date 2019-4-28 下午10:56:16 
	 * @param sess
	 * @param ediId 出版社编号(0表示全部)
	 * @param subId 学科编号(0表示全部)
	 * @param gradeId 年级编号(0表示全部)
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<Education> findPageInfoByOpt(Session sess,Integer ediId,Integer subId,Integer gradeId,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据条件获取教材记录条数
	 * @author wm
	 * @date 2019-4-28 下午10:57:19 
	 * @param sess
	 * @param ediId 出版社编号(0表示全部)
	 * @param subId 学科编号(0表示全部)
	 * @param gradeId 年级编号(0表示全部)
	 * @return
	 */
	Integer getCountByOpt(Session sess,Integer ediId,Integer subId,Integer gradeId);
}
