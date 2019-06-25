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
	 * @param isfinish 完成状态
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws WEBException
	 */
	List<BuffetSendInfo> listBsInfoByOption(Integer stuId,
			Integer subId, Integer isfinish, String starttime, String endtime)throws WEBException;
}