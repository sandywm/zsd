package com.zsd.service;

import com.zsd.exception.WEBException;
import com.zsd.module.StudentParentInfo;

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
	
	/**
	 * 根据家长编号获取孩子家长关联信息实体
	 * @author wm
	 * @date 2019-6-20 上午11:36:58
	 * @param parId 家长编号
	 * @return
	 * @throws WEBException
	 */
	StudentParentInfo getEntityByParId(Integer parId)throws WEBException;
	/**
	 *  根据学生编号获取孩子家长关联信息实体
	 * @author zdf
	 * 2019-9-26 下午03:33:57
	 * @param stuId 学生编号
	 * @return
	 * @throws WEBException
	 */
	StudentParentInfo getEntityBystuId(Integer stuId)throws WEBException;
}
