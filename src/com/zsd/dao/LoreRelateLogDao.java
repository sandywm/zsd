package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.LoreRelateLogInfo;

public interface LoreRelateLogDao {
	/**
	 * 根据主键加载知识点关联日志信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的知识点关联日志信息的主键值
	 * @return 加载的知识点关联日志信息PO
	 */
	LoreRelateLogInfo get(Session sess,int id);
	
	/**
	 * 保存知识点关联日志信息信息实体，新增一条知识点关联日志信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param lrl 保存的知识点关联日志信息实例
	 */
	void save(Session sess,LoreRelateLogInfo lrl);
	
	/**
	 * 删除知识点关联日志信息实体，删除一条知识点关联日志信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param lrl 删除的知识点关联日志信息实体
	 */
	void delete(Session sess,LoreRelateLogInfo lrl);
	
	/**
	 * 根据主键删除知识点关联日志信息实体，删除一条知识点关联日志信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除知识点关联日志信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 根据条件分页获取知识点关联日志信息列表
	 * @author wm
	 * @date 2019-10-31 下午05:07:51
	 * @param sess
	 * @param lorePyCode 知识点拼音首字母
	 * @param loreName 知识点名称
	 * @param ediId 出版社编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<LoreRelateLogInfo> findPageInfoByOpt(Session sess,String lorePyCode,String loreName,Integer ediId,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据条件获取知识点关联日志信息记录条数
	 * @author wm
	 * @date 2019-10-31 下午05:08:08
	 * @param sess
	 * @param lorePyCode 知识点拼音首字母
	 * @param loreName 知识点名称
	 * @param ediId 出版社编号
	 * @return
	 */
	Integer getCountByOpt(Session sess,String lorePyCode,String loreName,Integer ediId);
}
