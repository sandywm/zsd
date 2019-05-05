/**
 * 
 */
package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.Chapter;

public interface ChapterManger {
	
	/**
	 * 增加章节列表
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午09:45:09
	 * @param ediId 教材编号
	 * @param chapterName 章节名称
	 * @param chapterOrder 章节排序
	 * @return
	 * @throws WEBException
	 */
	Integer addChapter(Integer eduId,String chapterName,Integer chapterOrder) throws WEBException;
	
	/**
	 * 修改指定章节的章节名称、章节排序
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午09:45:45
	 * @param chapterId 章节编号
	 * @param chapterName 章节名称
	 * @param chapterOrder 章节排序
	 * @return
	 * @throws WEBException
	 */
	boolean updateChapter(Integer chapterId,String chapterName,Integer chapterOrder) throws WEBException;
	
	/**
	 * 根据（科目、年级、出版社、上/下册）获取到教材编号--获取到章节列表（升序排序）
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午09:46:17
	 * @param subId 学科编号
	 * @param gradeName 年级名称
	 * @param ediId 出版社编号
	 * @param eduVolume 上/下册
	 * @return
	 * @throws WEBException
	 */
	List<Chapter> ListInfoByOpt(Integer subId,String gradeName,Integer ediId,String eduVolume) throws WEBException;
	
	/**
	 * 根据教材编号获取章节列表（升序排序）
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午10:22:23
	 * @param eduId  教材编号
	 * @return
	 * @throws WEBException
	 */
	List<Chapter> ListInfoByEduId(Integer eduId) throws WEBException;
	
	/**
	 * 根据章节编号获取章节详情
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午10:41:44
	 * @param cptId 章节编号
	 * @return
	 * @throws WEBException
	 */
	Chapter getEntityById(Integer cptId) throws WEBException;
	
	/**
	 * 根据教材编号、章节名称获取章节信息
	 * @author Administrator
	 * @date 2019-5-5 上午09:25:20
	 * @param eduId 教材编号
	 * @param cptName 章节名称
	 * @return
	 */
	List<Chapter> listInfoByOpt(Integer eduId,String cptName) throws WEBException ;
}
