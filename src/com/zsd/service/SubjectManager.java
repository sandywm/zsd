package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.Subject;

public interface SubjectManager {

	/**
	 * 根据显示状态获取科目列表
	 * @description
	 * @author wm
	 * @date 2019-4-28 下午04:53:05
	 * @param displayStatus 显示状态(-1:表示全部,0:显示,1:隐藏)
	 * @return
	 * @throws WEBException
	 */
	List<Subject> listInfoByDisplayStatus(Integer displayStatus)throws WEBException;
	/**
	 * 根据学科名称获取学科信息
	 * @author zdf
	 * 2019-7-10 下午03:53:49
	 * @param subName 学科名称
	 * @return
	 * @throws WEBException
	 */
	List<Subject> listInfoBySubName(String subName)throws WEBException;
}
