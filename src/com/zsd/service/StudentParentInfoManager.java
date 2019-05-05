package com.zsd.service;

import com.zsd.exception.WEBException;

public interface StudentParentInfoManager {
	/**
	 * 添加学生家长信息
	 * @author zong
	 * @date  2019-5-4 上午11:38:08
	 * @param upId 家长编号
	 * @param uId 学生编号
	 * @return 主键
	 * @throws WEBException
	 */
	Integer addSpInfo(Integer upId,Integer uId)throws WEBException;
}
