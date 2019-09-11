package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.Email;

public interface EmailManager {

	/**
	 * 增加邮件
	 * @author wm
	 * @date 2019-8-31 上午09:04:03
	 * @param sendUserId 发送人
	 * @param title 标题
	 * @param content 内容
	 * @param emailType 邮件类型(sys,per)
	 * @param toUserId 接收人
	 * @return
	 * @throws WEBException
	 */
	Integer addEmail(Integer sendUserId,String title,String content,String emailType,Integer toUserId) throws WEBException;
	
	/**
	 * 批量删除邮件(多个时)
	 * @author wm
	 * @date 2019-8-31 上午09:04:49
	 * @param emailIdStr 邮件主键，多个逗号隔开
	 * @throws WEBException
	 */
	void delBatchInfoByIdStr(String emailIdStr,Integer userId) throws WEBException;
	
	/**
	 * 根据条件分页获取邮件列表
	 * @author wm
	 * @date 2019-8-31 上午09:05:17
	 * @param userId 接收人(0时表示全部)
	 * @param title 标题（""时表示全部）
	 * @param sDate 开始时间（""时表示全部）
	 * @param eDate 结束时间（""时表示全部）
	 * @param emailType 邮件类型(sys,per)，""时表示全部
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<Email> listPageInfoByOpt(Integer userId,String title,String sDate,String eDate,String emailType,int pageNo,int pageSize) throws WEBException;
	
	/**
	 * 根据条件获取邮件记录
	 * @author wm
	 * @date 2019-8-31 上午09:07:21
	 * @param userId 接收人(0时表示全部)
	 * @param title 标题（""时表示全部）
	 * @param sDate 开始时间（""时表示全部）
	 * @param eDate 结束时间（""时表示全部）
	 * @param emailType 邮件类型(sys,per)，""时表示全部
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByOpt(Integer userId,String title,String sDate,String eDate,String emailType) throws WEBException;
	
	/**
	 * 批量修改消息已读标识
	 * @author wm
	 * @date 2019-9-11 下午04:47:32
	 * @param emailIdStr 邮件主键，多个逗号隔开
	 * @throws WEBException
	 */
	void updateBatchInfoByIdStr(String emailIdStr,Integer userId) throws WEBException;
}
