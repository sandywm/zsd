package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.BuffetSendInfo;

public interface BuffetSendInfoManager {
	/**
	 * 根据指定学生,学科,完成状态,时间段获取自助餐发布信息
	 * @author zdf
	 * 2019-6-19 下午04:42:20
	 * @param stuId 学生编号
	 * @param subId  学科编号
	 * @param isfinish 完成状态(0(全部),1(未完成),2(已完成))
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws WEBException
	 */
	List<BuffetSendInfo> listBsInfoByOption(Integer stuId,
			Integer subId, Integer isfinish, String starttime, String endtime)throws WEBException;
	/**
	 * 根据主键获取自助餐发布信息
	 * @author zdf
	 * 2019-7-1 下午05:01:31
	 * @param Id 主键
	 * @return
	 * @throws WEBException
	 */
	List<BuffetSendInfo> listBsInfoById(Integer Id)throws WEBException;
	
	/**
	 * 根据指定学生,学科,完成状态,时间段分页获取自助餐发布信息
	 * @author wm
	 * @date 2019-6-25 下午05:20:22
	 * @param stuId 学生编号
	 * @param subId  学科编号
	 * @param isfinish 完成状态(0(全部),1(未完成),2(已完成))
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<BuffetSendInfo> listPageInfoByOption(Integer stuId,Integer subId, Integer isfinish, 
			String sDate, String eDate,Integer pageNo,Integer pageSize)throws WEBException;
	
	/**
	 * 增加自助餐发布信息
	 * @author wm
	 * @date 2019-6-26 下午05:40:01
	 * @param studyLogId 学习记录编号
	 * @param sendTime 发送时间
	 * @param sendUserId 发送人编号
	 * @param sendMode 发送方式(0:网络导师发送,1:系统发送)
	 * @param allNumber 总数
	 * @return
	 * @throws WEBException
	 */
	Integer addBuffetSend(Integer studyLogId,String sendTime,Integer sendUserId,Integer sendMode,Integer allNumber)throws WEBException;
	
	/**
	 * 修改指定主键的学习结果、完成次数
	 * @author wm
	 * @date 2019-6-26 下午03:55:46
	 * @param id 主键
	 * @param isfinish 完成状态(0(不修改),1(未完成),2(已完成))
	 * @param completeNumber 完成次数(0不修改)
	 * @return
	 * @throws WEBException
	 */
	boolean updateBuffetSend(Integer id,Integer isfinish,Integer completeNumber)throws WEBException;
}
