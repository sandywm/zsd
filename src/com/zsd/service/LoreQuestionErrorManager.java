package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.LoreQuestionErrorInfo;

public interface LoreQuestionErrorManager {

	/**
	 * 根据主键获取错题实体
	 * @author wm
	 * @date 2019-5-23 下午05:27:30
	 * @param id 主键
	 * @return
	 * @throws WEBException
	 */
	LoreQuestionErrorInfo getEntityById(Integer id) throws WEBException;
	
	/**
	 * 增加错题记录
	 * @author wm
	 * @date 2019-5-23 下午05:27:50
	 * @param userId
	 * @param userId 提交错误用户编号
	 * @param loreQuestionId 知识点错题编号
	 * @param errorType 错误类型
	 * @param content 意见内容
	 * @param addDate 提交日期
	 * @return
	 * @throws WEBException
	 */
	Integer addLQE(Integer userId,Integer loreQuestionId,String errorType,String content,String addDate) throws WEBException;
	
	/**
	 * 修改错题
	 * @author wm
	 * @date 2019-5-23 下午05:28:14
	 * @param lqeId 错题报告编号
	 * @param updateStatus 修改状态
	 * @param updateUserName 修改人员姓名
	 * @param updateDate 修改日期
	 * @param remark 备注
	 * @return
	 * @throws WEBException
	 */
	boolean updateLQE(Integer lqeId,Integer addCoinNumber,Integer updateStatus,String updateUserName, String updateDate,String remark) throws WEBException;
	
	/**
	 * 分页获取错题列表
	 * @author wm
	 * @date 2019-5-23 下午05:28:43
	 * @param userId 提交人员编号(0表示全部)
	 * @param lqId 题编号(0表示全部)
	 * @param sDate 开始日期
	 * @param sDate 结束日期
	 * @param errorType 错误类型(""表示全部,noPicError:没有图片,contentError:内容错误,anserError:答案错误,otherError:其他错误)
	 * @param updateStatus 修改状态(-1:全部,0:未修改,1:已修改)
	 * @param pageNo 页码
	 * @param pageSize 每页记录条数
	 * @return
	 * @throws WEBException
	 */
	List<LoreQuestionErrorInfo> listPageInfoByOptions(Integer userId,Integer lqId,String sDate,String eDate,
			String errorType,Integer updateStatus,Integer pageNo, Integer pageSize) throws WEBException;
	
	/**
	 * 获取错题记录条数
	 * @author wm
	 * @date 2019-5-23 下午05:29:09
	 * @param userId 提交人员编号(0表示全部)
	 * @param lqId 题编号(0表示全部)
	 * @param sDate 开始日期
	 * @param sDate 结束日期
	 * @param errorType 错误类型(""表示全部,noPicError:没有图片,contentError:内容错误,anserError:答案错误,otherError:其他错误)
	 * @param updateStatus 修改状态(-1:全部,0:未修改,1:已修改)
	 * @param pageNo 页码
	 * @param pageSize 每页记录条数
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByOptions(Integer userId,Integer lqId,String sDate,String eDate,String errorType,Integer updateStatus) throws WEBException;
}
