package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.ClassInfo;

public interface ClassInfoDao {
	/**
	 * 根据主键加载班级信息
	 * @author zong
	 * @date  2019-5-5 下午04:25:13
	 * @param sess
	 * @param id 主键值
	 * @return
	 */
	ClassInfo get(Session sess,int id);
	
	/**
	 * 保存班级信息实体
	 * @author zong
	 * @date  2019-5-5 下午04:25:58
	 * @param sess
	 * @param classInfo 班级实体
	 */
	void save(Session sess,ClassInfo classInfo);
	
	/**
	 * 删除班级信息实体
	 * @author zong
	 * @date  2019-5-5 下午04:26:55
	 * @param sess
	 * @param classInfo 班级实体
	 */
	void delete(Session sess,ClassInfo classInfo);
	
	/**
	 * 根据主键删除班级信息实体，删除一条班级信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-5 下午04:27:21
	 * @param sess
	 * @param id 需要删除班级信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条班级信息记录
	 * @description
	 * @author zong
	 * @date 2019-5-5 下午04:27:58
	 * @param sess
	 * @param roleInfo 需要更新的班级信息
	 */
	void update(Session sess,ClassInfo classInfo);
	
	/**
	 * 获取所有班级列表
	 * @description
	 * @author zong
	 * @date 2019-5-5 下午04:28:35
	 * @param sess
	 * @return
	 */
	List<ClassInfo> findClassInfo(Session sess);
	 /**
	  * 通过指定学校指定年级(通过当前时间换算)的班级名称获取该班级实体
	  * @author zong
	  * @date  2019-5-6 上午08:42:20
	  * @param sess
	  * @param gradeId 年级
	  * @param currentTime 当前时间
	  * @param schoolId 学校编号
	  * @param className 班级名称
	  * @return 班级实体
	  */
	List<ClassInfo> findClassInfoByOption(Session sess, Integer gradeId,String currentTime,
			Integer schoolId, String className);
	/**
	 * 根据主键获取班级信息
	 * @author zong
	 * 2019-5-11上午11:37:52
	 * @param sess
	 * @param cId 主键
	 * @return 班级实体
	 */
	List<ClassInfo> findClassInfoById(Session sess, Integer cId);
	
	/**
	 * 根据学校编号，班级名称，创建班级时间获取该班级信息
	 * @author sandy
	 * @date 2019-12-18 上午08:26:02
	 * @param sess
	 * @param schoolId 学校编号
	 * @param className 班级名称
	 * @param buildClassDate 班级创建时间
	 * @return
	 */
	List<ClassInfo> findClassInfoByOption(Session sess, Integer schoolId,
			String className, String buildClassDate);
	
	/**
	 * 根据创建班级时间，乡镇,学校编号获取班级列表
	 * @author wm
	 * @date 2019-12-21 上午10:26:17
	 * @param sess
	 * @param buildClassDate 班级创建日期
	 * @param town 乡镇（""时不传）
	 * @param schoolId 学校编号(0时不传)
	 * @return
	 */
	List<ClassInfo> findClassInfoByOpt(Session sess,String buildClassDate,String town,Integer schoolId);

}
