package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.NetTeacherBasicInfo;

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
	/**
	 * 根据网络导师主键获取网络导师教学经历或成果分享
	 * @author zong
	 * 2019-6-5下午04:49:50
	 * @param teaId 网络导师主键
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherBasicInfo> listNtbByTeaId(Integer teaId)throws WEBException;
}
