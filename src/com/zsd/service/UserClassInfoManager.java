package com.zsd.service;

import com.zsd.exception.WEBException;

public interface UserClassInfoManager {
	/**
	 *  添加用户编辑信息（学生绑定班级）
	 * @author zong
	 * @date  2019-5-5 下午04:53:49
	 * @param userId 用户编号
	 * @param classId 班级编号
	 * @param roleId 角色编号
	 * @return 用户班级主键值
	 * @throws WEBException
	 */
	Integer addUcInfo(Integer userId,Integer classId,Integer roleId) throws WEBException;

}
