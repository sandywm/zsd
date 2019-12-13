package com.zsd.service;

import java.util.List;

import org.hibernate.Session;

import com.zsd.exception.WEBException;
import com.zsd.module.StudyStuQfTjInfo;

public interface StudyStuQfTjManager {

	/**
	 * 增加勤奋报告统计信息
	 * @author wm
	 * @date 2019-6-19 上午09:00:28
	 * @param userId 学生编号
	 * @param subId 科目编号
	 * @param oneZdSuccNum 一次性通过总数
	 * @param oneZdFailNum 一次性未通过总数
	 * @param againXxSuccNum 再次诊断(学习)通过
	 * @param againXxFailNum 再次诊断(学习)未通过
	 * @param noRelateNum 未溯源个数
	 * @param relateZdFailNum 关联诊断未通过
	 * @param relateXxSuccNum 关联学习通过
	 * @param relateXxFailNum 关联未学习通过
	 * @param rate 转化率
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param town 镇
	 * @param schoolType 学段
	 * @param schoolId 学校编号
	 * @param gradeName 年级名称
	 * @param classId 班级编号
	 * @return
	 * @throws WEBException
	 */
	Integer addQFTJ(Integer userId,Integer subId,  
			Integer oneZdSuccNum,Integer oneZdFailNum,Integer againXxSuccNum, Integer againXxFailNum,
			Integer noRelateNum, Integer relateZdFailNum,Integer relateXxSuccNum, Integer relateXxFailNum, String rate,
			String prov,String city, String county, String town,Integer schoolType, Integer schoolId,String gradeName, Integer classId) throws WEBException;
	/**
	 * 根据主键获取统计实体信息
	 * @author wm
	 * @date 2019-6-19 上午09:03:48
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	StudyStuQfTjInfo getEntityById(Integer id) throws WEBException;
	
	/**
	 * 修改指定编号的信息
	 * @author wm
	 * @date 2019-6-19 上午09:04:54
	 * @param id
	 * @param oneZdSuccNum 一次性通过总数(0不修改)
	 * @param oneZdFailNum 一次性未通过总数(0不修改)
	 * @param againXxSuccNum 再次诊断(学习)通过(0不修改)
	 * @param againXxFailNum 再次诊断(学习)未通过(0不修改)
	 * @param noRelateNum 未溯源个数(0不修改)
	 * @param relateZdFailNum 关联诊断未通过(0不修改)
	 * @param relateXxSuccNum 关联学习通过(0不修改)
	 * @param relateXxFailNum 关联未学习通过(0不修改)
	 * @param rate 转化率(""不修改)
	 * @return
	 * @throws WEBException
	 */
	boolean updateTjInfoById(Integer id,Integer oneZdSuccNum,Integer oneZdFailNum,Integer againXxSuccNum, Integer againXxFailNum,
			Integer noRelateNum, Integer relateZdFailNum,Integer relateXxSuccNum, Integer relateXxFailNum, String rate ) throws WEBException;
	
	/**
	 * 获取指定学生，指定科目，指定日期的勤奋报告统计信息
	 * @author wm
	 * @date 2019-6-19 上午09:04:15
	 * @param userId 学生编号
	 * @param subId 科目编号
	 * @param addDate 指定时间
	 * @return
	 * @throws WEBException
	 */
	StudyStuQfTjInfo getEntityByOpt(Integer userId,Integer subId,String addDate) throws WEBException;
	
	/**
	 * 根据条件获取勤奋报告统计信息列表
	 * @author wm
	 * @date 2019-6-19 上午09:05:48
	 * @param userId 学生编号
	 * @param subId 科目编号
	 * @param sDate 开始日期
	 * @param eDate 结束日期
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param town 镇
	 * @param schoolType 学段
	 * @param schoolId 学校编号
	 * @param gradeName 年级名称
	 * @param classId 班级编号
	 * @return
	 * @throws WEBException
	 */
	List<StudyStuQfTjInfo> listInfoByOpt(Integer userId,Integer subId,String sDate,String eDate,
			String prov,String city,String county,String town,Integer schoolType,Integer schoolId,String gradeName,Integer classId) throws WEBException;
	
	/**
	 * 获取指定条件的勤奋信息记录（去除重复，按照学生编号分组）--只做统计个数使用
	 * @author wm
	 * @date 2019-7-16 下午12:47:27
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
	 * @throws WEBException
	 */
	Integer getDistinctCountByOpt(Integer userId,Integer subId,String sDate,String eDate,
			String prov,String city,String county,String town,Integer schoolType,Integer schoolId,String gradeName,Integer classId) throws WEBException;
}
