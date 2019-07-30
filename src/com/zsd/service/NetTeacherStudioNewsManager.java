package com.zsd.service;

import com.zsd.exception.WEBException;

public interface NetTeacherStudioNewsManager {
	/**
	 * 添加网络老师工作室新闻信息
	 * @author zdf
	 * 2019-7-25 下午05:09:08
	 * @param ntStudioId 网络导师工作室编号
	 * @param newsTitle 新闻标题
	 * @param newsContent 新闻内容
	 * @param sendTime 发送时间
	 * @return
	 * @throws WEBException
	 */
	Integer addNTStudioNews(Integer ntStudioId, String newsTitle, String newsContent,String sendTime)throws WEBException;
}
