package com.zsd.service;

import com.zsd.exception.WEBException;

/**
 * @author zong
 * @version 2019-5-12 上午09:09:40
 */
public interface NetTeacherBasicInfoManager {
	/**
	 * 添加老师的背景资料信息
	 * @author zong
	 * 2019-5-12上午09:11:49
	 * @param ntId 老师编号
	 * @param title 标题
	 * @param dataRange 时间段
	 * @param description 描述
	 * @param type 类型
	 * @param addData  添加时间
	 * @return
	 * @throws WEBException
	 */
	Integer addNtbInfo(Integer ntId, String title,
			String dataRange, String description, Integer type, String addData)
			throws WEBException;

}
