package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.LexInfo;
import com.zsd.module.LexLoreRelateInfo;

public interface LexInfoManager {

	/**
	 * 增加词库
	 * @author wm
	 * @date 2019-5-20 下午04:46:31
	 * @param title 词库标题
	 * @param titlePy 标题拼音
	 * @param lexContent 词库内容
	 * @return
	 * @throws WEBException
	 */
	Integer addLex(String title,String titlePy,String lexContent) throws WEBException;
	
	/**
	 * 删除指定编号的词库信息
	 * @author wm
	 * @date 2019-5-20 下午04:46:54
	 * @param lexId 词库编号
	 * @return
	 * @throws WEBException
	 */
	boolean delLexById(Integer lexId) throws WEBException;
	
	/**
	 * 修改指定词库的信息
	 * @author wm
	 * @date 2019-5-20 下午04:47:10
	 * @param lexId 词库编号
	 * @param title 词库标题
	 * @param titlePy 标题拼音
	 * @param lexContent 词库内容
	 * @return
	 * @throws WEBException
	 */
	boolean updateLexById(Integer lexId,String title,String titlePy,String lexContent) throws WEBException;
	
	/**
	 * 根据词库编号获取词库详情
	 * @author wm
	 * @date 2019-5-20 下午04:47:31
	 * @param lexId 词库编号
	 * @return
	 * @throws WEBException
	 */
	LexInfo getEntityById(Integer lexId) throws WEBException;
	
	/**
	 * 根据条件查询词库记录列表
	 * @author wm
	 * @date 2019-5-20 下午04:47:48
	 * @param titleName 词库标题(""全部)
	 * @param titlePyCode 词库标题拼音码(""全部)
	 * @return
	 * @throws WEBException
	 */
	List<LexInfo> listInfoByOpt(String titleName,String titlePyCode) throws WEBException;
	
	/**
	 * 根据条件分页查询词库记录列表
	 * @author wm
	 * @date 2019-5-20 下午04:48:21
	 * @param titleName 词库标题(""全部)
	 * @param titlePyCode 词库标题拼音码(""全部)
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<LexInfo> listPageInfoByOpt(String titleName,String titlePyCode,Integer pageNo,Integer pageSize) throws WEBException;
	
	/**
	 * 根据条件获取词库记录条数
	 * @author wm
	 * @date 2019-5-20 下午04:48:42
	 * @param titleName 词库标题(""全部)
	 * @param titlePyCode 词库标题拼音码(""全部)
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByOpt(String titleName,String titlePyCode) throws WEBException;
	
	/**
	 * 增加知识点词库关联信息
	 * @author wm
	 * @date 2019-5-20 下午04:52:21
	 * @param lexId 词库编号
	 * @param loreId 知识点编号
	 * @return
	 * @throws WEBException
	 */
	Integer addLLR(Integer lexId,Integer loreId) throws WEBException;
	
	/**
	 * 删除指定编号的知识点词库关联信息（同时需要题库中的lexId=0）
	 * @author wm
	 * @date 2019-5-20 下午05:08:30
	 * @param llrId
	 * @return
	 * @throws WEBException
	 */
	boolean delLLRById(Integer llrId) throws WEBException;
	
	/**
	 * 根据条件查询知识点词库关联信息列表
	 * @author wm
	 * @date 2019-5-20 下午05:09:19
	 * @param lexId 词库编号（0表示全部）
	 * @param loreId 知识点编号（0表示全部）
	 * @return
	 * @throws WEBException
	 */
	List<LexLoreRelateInfo> listInfoByOpt(Integer lexId,Integer loreId) throws WEBException;
}
