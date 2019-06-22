package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.StudyStuQfTjInfo;

public interface StudyStuQfTjDao {

	/**
	 * 根据主键获取实体信息
	 * @author wm
	 * @date 2019-6-18 下午04:30:56
	 * @param sess
	 * @param id
	 * @return
	 */
	StudyStuQfTjInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加勤奋信息
	 * @author wm
	 * @date 2019-6-18 下午04:31:05
	 * @param sess
	 * @param qftj
	 */
	void save(Session sess,StudyStuQfTjInfo qftj);
	
	/**
	 * 修改勤奋信息
	 * @author wm
	 * @date 2019-6-18 下午04:31:22
	 * @param sess
	 * @param qftj
	 */
	void update(Session sess,StudyStuQfTjInfo qftj);
	
	/**
	 * 根据学生编号、科目编号、日期获取勤奋统计信息
	 * @author wm
	 * @date 2019-6-19 上午08:57:57
	 * @param sess
	 * @param userId  学生编号
	 * @param subId 科目编号
	 * @param addDate 指定日期
	 * @return
	 */
	StudyStuQfTjInfo getEntityByOpt(Session sess,Integer userId,Integer subId,String addDate);
	
	/**
	 * 根据条件获取勤奋信息(统计用)
	 * @author wm
	 * @date 2019-6-18 下午04:31:29
	 * @param sess
	 * @param userId 学生编号（0不查询）
	 * @param subId 科目编号（0不查询）
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @param prov 省 (""不查询)
	 * @param city 市(""不查询)
	 * @param county 县(""不查询)
	 * @param town 镇(""不查询)
	 * @param schoolType 学段(0不查询)
	 * @param schoolId 学校编号(0不查询)
	 * @param gradeName 年级名称(""不查询)
	 * @param classId 班级编号(0不查询)
	 * @return
	 */
	List<StudyStuQfTjInfo> findInfoByOpt(Session sess,Integer userId,Integer subId,String sDate,String eDate,
			String prov,String city,String county,String town,Integer schoolType,Integer schoolId,String gradeName,Integer classId);
}
