/**
 * 
 */
package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.Chapter;

public interface ChapterDao {

	/**
	 * 根据主键加载章节信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的章节信息的主键值
	 * @return 加载的章节信息PO
	 */
	Chapter get(Session sess,int id);
	
	/**
	 * 保存章节信息信息实体，新增一条章节信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param cpt 保存的章节信息实例
	 */
	void save(Session sess,Chapter cpt);
	
	/**
	 * 删除章节信息实体，删除一条章节信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param cpt 删除的章节信息实体
	 */
	void delete(Session sess,Chapter cpt);
	
	/**
	 * 根据主键删除章节信息实体，删除一条章节信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除章节信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条章节信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param cpt 需要更新的章节信息
	 */
	void update(Session sess,Chapter cpt);
	
	/**
	 * 根据（科目、年级、出版社、上/下册）获取到教材编号--获取到章节列表(升序排列)
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午09:37:04
	 * @param sess
	 * @param subId 学科编号
	 * @param gradeName 年级名称
	 * @param ediId 出版社编号
	 * @param eduVolume 上下册
	 * @return
	 */
	List<Chapter> findInfoByOpt(Session sess,Integer subId,String gradeName,Integer ediId,String eduVolume);
	
	/**
	 * 根据教材编号获取章节列表(升序排列)
	 * @author  Administrator
	 * @ModifiedBy  
	 * @date  2019-5-3 下午10:23:37
	 * @param sess
	 * @param eduId 教材编号
	 * @return
	 */
	List<Chapter> findInfoByEduId(Session sess,Integer eduId);
	
	/**
	 * 根据教材编号、章节名称获取章节信息
	 * @author Administrator
	 * @date 2019-5-5 上午09:26:26
	 * @param sess
	 * @param eduId 教材编号
	 * @param cptName 章节名称
	 * @return
	 */
	List<Chapter> findInfoByOpt(Session sess,Integer eduId,String cptName);
}
