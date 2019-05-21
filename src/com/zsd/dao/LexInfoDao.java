package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.LexInfo;
import com.zsd.module.LexLoreRelateInfo;

/**
 * 包含词库、知识点词库关联
 * @author Administrator
 * @createDate 2019-5-20
 */
public interface LexInfoDao {
	
	/**
	 * 根据主键获取词库信息实体
	 * @author wm
	 * @date 2019-5-20 上午11:10:42
	 * @param sess
	 * @param id  主键
	 * @return
	 */
	LexInfo getEntityById(Session sess,Integer id);
	
	/**
	 * 增加词库信息
	 * @author wm
	 * @date 2019-5-20 上午11:11:06
	 * @param sess
	 * @param lex 词库实体
	 */
	void save(Session sess,LexInfo lex);
	
	/**
	 * 修改词库信息
	 * @author wm
	 * @date 2019-5-20 上午11:11:24
	 * @param sess
	 * @param lex 词库实体
	 */
	void update(Session sess,LexInfo lex);
	
	/**
	 * 删除词库
	 * @author wm
	 * @date 2019-5-20 上午11:11:43
	 * @param sess
	 * @param lex 词库实体
	 */
	void delete(Session sess,LexInfo lex);
	
	/**
	 * 根据条件查询词库记录列表
	 * @author wm
	 * @date 2019-5-20 上午11:11:59
	 * @param sess
	 * @param titleName 词库标题(""全部)
	 * @param titlePyCode 词库标题拼音码(""全部)
	 * @return
	 */
	List<LexInfo> findInfoByOpt(Session sess,String titleName,String titlePyCode);
	
	/**
	 * 根据条件分页查询词库记录列表
	 * @author wm
	 * @date 2019-5-20 上午11:13:36
	 * @param sess
	 * @param titleName  词库标题(""全部)
	 * @param titlePyCode 词库标题拼音码(""全部)
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<LexInfo> findPageInfoByOpt(Session sess,String titleName,String titlePyCode,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据条件获取词库记录条数
	 * @author wm
	 * @date 2019-5-20 上午11:14:21
	 * @param sess
	 * @param titleName  词库标题(""全部)
	 * @param titlePyCode 词库标题拼音码(""全部)
	 * @return
	 */
	Integer getCountByOpt(Session sess,String titleName,String titlePyCode);
	
	/**
	 * 根据主键获取知识点词库关联信息实体
	 * @author wm
	 * @date 2019-5-20 上午11:14:48
	 * @param sess
	 * @param llrId 主键
	 * @return
	 */
	LexLoreRelateInfo getEntityByLlrId(Session sess,Integer llrId);
	
	/**
	 * 增加知识点词库关联信息
	 * @author wm
	 * @date 2019-5-20 上午11:15:20
	 * @param sess
	 * @param llr
	 */
	void saveLlr(Session sess,LexLoreRelateInfo llr);
	
	/**
	 * 修改知识点词库关联信息
	 * @author wm
	 * @date 2019-5-20 上午11:15:32
	 * @param sess
	 * @param llr
	 */
	void updateLlr(Session sess,LexLoreRelateInfo llr);
	
	/**
	 * 删除指定的知识点词库信息
	 * @author wm
	 * @date 2019-5-20 上午11:15:42
	 * @param sess
	 * @param llr
	 */
	void deleteLlr(Session sess,LexLoreRelateInfo llr);
	
	/**
	 * 根据条件获取知识点词库关联信息列表
	 * @author wm
	 * @date 2019-5-20 上午11:21:56
	 * @param sess
	 * @param loreId 知识点编号（0表示全部）
	 * @param lexId 词库编号（0表示全部）
	 * @return
	 */
	List<LexLoreRelateInfo> findInfoByOpt(Session sess,Integer lexId,Integer loreId);
}
