package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.QuestionInfo;

/**
 * @author zong
 * @version 2019-5-19 上午11:43:57
 */
public interface QuestionInfoManager {
	/**
	 * 增加我的提问
	 * @author zong
	 * 2019-5-20下午04:53:13
	 * @param subId 学科编号
	 * @param userId 用户编号
	 * @param ntId 老师编号
	 * @param queTitle 问题标题
	 * @param queContent 问题内容
	 * @param queTime 提问时间
	 * @param queReplyContent 回复内容
	 * @param queReplyTime 回复时间
	 * @param readStatus 回复状态
	 * @return
	 * @throws WEBException
	 */
	Integer adddQue(Integer subId,Integer userId,Integer ntId,String queTitle,String queContent,String queTime,String queReplyContent,
	 String queReplyTime,Integer readStatus)throws WEBException;
	/**
	 * 根据学科,恢复状态获取问题信息
	 * 
	 * @author zong 2019-5-19上午11:46:30
	 * @param subId
	 *            学科编号
	 * @param readStatus
	 *            恢复状态
	 * @return
	 * @throws WEBException
	 */
	public List<QuestionInfo> listInfoByOpt(Integer subId, Integer readStatus,
			Integer pageNo, Integer pageSize) throws WEBException;
	/**
	 * 
	 * @author zong
	 * 2019-5-20上午09:40:19
	 * @param subId
	 * @param readStatus
	 * @return
	 * @throws WEBException
	 */
	Integer getInfoByOptCount(Integer subId, Integer readStatus)throws WEBException;
	/**
	 * 修改指定问题的回复内容,时间,状态
	 * @author zong
	 * 2019-5-21下午03:47:33
	 * @param qId 问题主键
	 * @param queReplyContent 回复内容
	 * @param queReplyTime 回复时间
	 * @param readStatus 回复状态
	 * @return
	 * @throws WEBException
	 */
	boolean updateQue(Integer qId,String queReplyContent, String queReplyTime,Integer readStatus)throws WEBException;
	/**
	 * 根据网络导师编号获取问题信息
	 * @author zong
	 * 2019-5-23下午04:45:09
	 * @param ntId 网络导师编号
	 * @return
	 * @throws WEBException
	 */
	List<QuestionInfo> findInfoByntId(Integer ntId)throws WEBException;
	/**
	 * 根据学生编号,回复状态分页获取信息
	 * @author zong
	 * 2019-5-23下午04:45:41
	 * @param stuId 学生编号
	 * @param readStatus 回复状态
	 * @param pageNo 第几页
	 * @param pageSize 每页多少条
	 * @return
	 * @throws WEBException
	 */
	List<QuestionInfo> listInfoByStu(Integer stuId,Integer readStatus, Integer pageNo, Integer pageSize) throws WEBException;
	/**
	 * 根据学生编号,回复状态获取记录数
	 * @author zong
	 * 2019-5-23下午04:46:42
	 * @param stuId 学生编号
	 * @param readStatus 回复状态
	 * @return
	 * @throws WEBException
	 */
	Integer getInfoByStuCount(Integer stuId,Integer readStatus)throws WEBException;

}
