package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.LoreQuestionErrorInfo;

public interface LoreQuestionErrorDao {
	/**
	 * 根据主键加载知识点题库错误信息实体
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 需要加载的知识点题库错误信息的主键值
	 * @return 加载的知识点题库错误信息PO
	 */
	LoreQuestionErrorInfo get(Session sess,int id);
	
	/**
	 * 保存知识点题库错误信息信息实体，新增一条知识点题库错误信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param lqs 保存的知识点题库错误信息实例
	 */
	void save(Session sess,LoreQuestionErrorInfo lqs);
	
	/**
	 * 删除知识点题库错误信息实体，删除一条知识点题库错误信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param lqs 删除的知识点题库错误信息实体
	 */
	void delete(Session sess,LoreQuestionErrorInfo lqs);
	
	/**
	 * 根据主键删除知识点题库错误信息实体，删除一条知识点题库错误信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除知识点题库错误信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条知识点题库错误信息记录
	 * @description
	 * @author Administrator
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param lqs 需要更新的知识点题库错误信息
	 */
	void update(Session sess,LoreQuestionErrorInfo lqs);
	
	/**
	 * 根据条件分页获取知识点题库错误信息列表
	 * @author wm
	 * @date 2019-5-10 下午06:34:13
	 * @param sess
	 * @param userId 提交错误学生编号(0表示全部)
	 * @param errorType 错误类型(""表示全部,noPicError:没有图片,contentError:内容错误,anserError:答案错误,otherError:其他错误)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @param updateStatus 修改状态(-1:全部,0:未修改,1:已修改)
	 * @return
	 */
	List<LoreQuestionErrorInfo> findPageInfoByOpt(Session sess,Integer userId,String errorType,String sDate,String eDate,Integer updateStatus,Integer pageNo,Integer pageSize);
	
	/**
	 * 根据条件获取知识点题库错误信息记录条数
	 * @author wm
	 * @date 2019-5-10 下午06:47:41
	 * @param sess
	 * @param userId 提交错误学生编号(0表示全部)
	 * @param errorType 错误类型(""表示全部,noPicError:没有图片,contentError:内容错误,anserError:答案错误,otherError:其他错误)
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @param updateStatus 修改状态(-1:全部,0:未修改,1:已修改)
	 * @return
	 */
	Integer getCountByOpt(Session sess,Integer userId,String errorType,String sDate,String eDate,Integer updateStatus);
}
