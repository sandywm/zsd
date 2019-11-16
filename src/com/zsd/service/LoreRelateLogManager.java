package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.LoreRelateLogInfo;

public interface LoreRelateLogManager {

	/**
	 * 增加知识点关联日志
	 * @author wm
	 * @date 2019-11-2 上午11:37:29
	 * @param loreId 知识点编号
	 * @param relateType 关联类型(add--增加,del-删除)
	 * @param relateStatus关联状态(0:失败，1：成功)
	 * @param relateResult 关联结果
	 * @param relateUser 关联人员
	 * @return
	 * @throws WEBException
	 */
	Integer addLRL(Integer loreId,String relateType,Integer relateStatus,String relateResult,String relateUser) throws WEBException;
	
	/**
	 * 根据主键获取知识点关联日志实体
	 * @author wm
	 * @date 2019-11-2 上午11:39:36
	 * @param lrlId 主键
	 * @return
	 * @throws WEBException
	 */
	LoreRelateLogInfo getEntityById(Integer lrlId) throws WEBException;
	
	/**
	 * 根据知识点拼音码,知识点名称,出版社编号,关联状态分页获取日志列表
	 * @author wm
	 * @date 2019-11-2 上午11:39:56
	 * @param lorePyCode 知识点拼音码(""时不查询)
	 * @param loreName 知识点名称(""时不查询)
	 * @param ediId 出版社编号(0时不查询)
	 * @param relateStatus 关联结果（0：关联失败，1：关联成功）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<LoreRelateLogInfo> listPageInfoByOpt(String lorePyCode,String loreName,Integer ediId,Integer relateStatus,Integer pageNo,Integer pageSize)throws WEBException;
	
	/**
	 * 根据知识点拼音码,知识点名称,出版社编号,关联状态获取日志记录条数
	 * @author wm
	 * @date 2019-11-2 上午11:41:16
	 * @param lorePyCode 知识点拼音码(""时不查询)
	 * @param loreName 知识点名称(""时不查询)
	 * @param ediId 出版社编号(0时不查询)
	 * @param relateStatus 关联结果（0：关联失败，1：关联成功）
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByOpt(String lorePyCode,String loreName,Integer ediId,Integer relateStatus)throws WEBException;
}
