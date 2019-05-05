package com.zsd.service;

import java.util.List;
import com.zsd.exception.WEBException;
import com.zsd.module.Education;

public interface EducationManager {

	/**
	 * 增加教材
	 * @author wm
	 * @date 2019-4-28 下午11:03:42 
	 * @param gradeId 年级编号
	 * @param ediId 出版社编号
	 * @param eduOrder 排序
	 * @param eduVolume 上/下册
	 * @param eduImg 教材图片
	 * @return
	 * @throws WEBException
	 */
	Integer addEdu(Integer gradeId,Integer ediId,Integer eduOrder,String eduVolume,String eduImg) throws WEBException;
	
	/**
	 * 修改指定编号的教材信息
	 * @author wm
	 * @date 2019-4-28 下午11:04:30 
	 * @param eduId 教材编号
	 * @param gradeId 年级编号
	 * @param ediId 出版社编号
	 * @param eduOrder 排序
	 * @param eduVolume 上/下册
	 * @param eduImg 教材图片
	 * @param showStatus 显示状态（0：显示，1：隐藏）
	 * @return
	 * @throws WEBException
	 */
	boolean updateEduById(Integer eduId,Integer gradeId,Integer ediId,Integer eduOrder,
			Integer showStatus,String eduVolume,String eduImg) throws WEBException;
	
	/**
	 * 获取指定教材编号的详情
	 * @author wm
	 * @date 2019-4-28 下午11:05:42 
	 * @param eduId 教材编号
	 * @return
	 * @throws WEBException
	 */
	List<Education> listSpecInfoById(Integer eduId) throws WEBException;
	
	/**
	 * 分页获取指定条件教材信息列表
	 * @author wm
	 * @date 2019-4-28 下午11:06:04 
	 * @param ediId 出版社编号(0表示全部)
	 * @param subId 学科编号(0表示全部)
	 * @param gradeId 年级编号(0表示全部)
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<Education> listPageInfoByOpt(Integer ediId,Integer subId,Integer gradeId,Integer pageNo,Integer pageSize) throws WEBException;
	
	/**
	 * 获取指定条件的教材记录条数
	 * @author wm
	 * @date 2019-4-28 下午11:09:32 
	 *  @param ediId 出版社编号(0表示全部)
	 * @param subId 学科编号(0表示全部)
	 * @param gradeId 年级编号(0表示全部)
	 * @return
	 */
	Integer getCountByOpt(Integer ediId,Integer subId,Integer gradeId) throws WEBException;
	
	/**
	 * 根据出版社编号、年级学科编号、卷册获取教材信息列表
	 * @author Administrator
	 * @date 2019-5-5 上午10:40:02
	 * @param ediId 出版社编号
	 * @param gradeId 年级学科编号
	 * @param eduVolume 卷册
	 * @return
	 * @throws WEBException
	 */
	List<Education> listInfoByOpt(Integer ediId,Integer gradeId,String eduVolume) throws WEBException;
}
