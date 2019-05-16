package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.LoreInfo;

public interface LoreInfoManager {

	/**
	 * 增加知识点
	 * @author wm
	 * @date 2019-5-4 下午11:15:25 
	 * @param cptId 章节编号
	 * @param loreName  知识点名称
	 * @param lorePyCode 知识点拼音
	 * @param loreOrder 知识点排序
	 * @param mainLoreId 引用知识点编号
	 * @param loreCode 知识点编码
	 * @return
	 * @throws WEBException
	 */
	Integer addLore(Integer cptId,String loreName,String lorePyCode,Integer loreOrder,Integer mainLoreId,String loreCode)throws WEBException;
	
	/**
	 * 修改指定编号的知识点实体信息（-1:不修改）
	 * @author wm
	 * @date 2019-5-4 下午11:17:14 
	 * @param id 编号
	 * @param loreName 名称（"":不修改）
	 * @param cptId 章节编号（-1:不修改）
	 * @param orders 排列顺序（-1:不修改）
	 * @param inUse 是否启用（-1:不修改）
	 * @param isFree 是否免费（-1:不修改）
	 * @return
	 * @throws WEBException
	 */
	boolean updateLore(Integer id,String loreName,Integer cptId,Integer orders,Integer inUse,Integer isFree)throws WEBException;
	
	/**
	 * 获取指定章节下的知识点(有效)
	 * @author wm
	 * @date 2019-5-4 下午11:20:36 
	 * @param cptId 章节编号
	 * @return
	 * @throws WEBException
	 */
	List<LoreInfo> listInfoByCptId(Integer cptId)throws WEBException;
	
	/**
	 * 分页获取指定章节下的知识点列表
	 * @author wm
	 * @date 2019-5-4 下午11:21:39 
	 * @param cptId 章节编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<LoreInfo> listPageInfoByCptId(Integer cptId,Integer pageNo,Integer pageSize)throws WEBException;
	
	/**
	 * 获取指定章节下的知识点记录条数
	 * @author wm
	 * @date 2019-5-4 下午11:21:59 
	 * @param cptId 章节编号
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByCptId(Integer cptId)throws WEBException;
	
	/**
	 * 根据章节编号获取当前最大排序号
	 * @author wm
	 * @date 2019-5-4 下午11:23:30 
	 * @param cptId 章节编号
	 * @return
	 * @throws WEBException
	 */
	Integer getCurrentMaxOrderByCptId(Integer cptId)throws WEBException;
	
	/**
	 * 检查指定章节编号下知识点是否重名
	 * @author wm
	 * @date 2019-5-4 下午11:24:17 
	 * @param cptId 章节编号
	 * @param loreName 知识点名称
	 * @return true:存在
	 * @throws WEBException
	 */
	boolean checkExistByCptId(Integer cptId,String loreName)throws WEBException;
	
	/**
	 * 根据知识点编号获取详情
	 * @author wm
	 * @date 2019-5-4 下午11:25:28 
	 * @param id 知识点编号
	 * @return
	 * @throws WEBException
	 */
	LoreInfo getEntityById(Integer id)throws WEBException;
	
	/**
	 * 根据主知识点（通用版）获取所有子知识点信息列表
	 * @author wm
	 * @date 2019-5-4 下午11:26:37 
	 * @param mainLoreId 被引用的知识点编号
	 * @return
	 * @throws WEBException
	 */
	List<LoreInfo> listInfoByMainLoreId(Integer mainLoreId)throws WEBException;
	
	/**
	 * 根据知识点拼音码/名称、出版社编号模糊查询知识点列表
	 * @author wm
	 * @date 2019-5-4 下午11:30:42 
	 * @param lorePyCode 知识点拼音码(""不查询)
	 * @param loreName 知识点名称(""不查询)
	 * @param ediId 出版社编号(0表示全部)
	 * @return
	 * @throws WEBException
	 */
	List<LoreInfo> listInfoByLorePyOrName(String lorePyCode,String loreName,Integer ediId)throws WEBException;
	
	/**
	 * 修改指定知识点的编码
	 * @author wm
	 * @date 2019-5-6 上午11:34:11
	 * @param loreId 知识点编号
	 * @param loreCode 知识点编码
	 * @return
	 * @throws WEBException
	 */
	boolean updateLoreCodeById(Integer loreId,String loreCode)throws WEBException;
	
	/**
	 * 根据引用知识点编号（通用版），其他出版社获取知识点
	 * @author wm
	 * @date 2019-5-16 上午09:13:55
	 * @param mainLoreId mainLoreId 引用知识点编号（通用版）
	 * @param ediId ediId 其他出版社编号（通用版除外）
	 * @return
	 * @throws WEBException
	 */
	LoreInfo getLoreInfoByOpt(Integer mainLoreId,Integer ediId)throws WEBException;
	
	/**
	 * 获取全部数据（自动修改编码时用）--其他任何时候不要调用
	 * @author wm
	 * @date 2019-5-16 下午04:51:18
	 * @return
	 * @throws WEBException
	 */
	List<LoreInfo> listAllInfo()throws WEBException;
	
	void updateBatchLoreCode(List<LoreInfo> lList)throws WEBException;
}
