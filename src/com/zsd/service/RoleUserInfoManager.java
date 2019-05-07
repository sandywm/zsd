package com.zsd.service;

import com.zsd.exception.WEBException;

public interface RoleUserInfoManager {
	
	/**
	 * 添加角色用户信息
	 * @author zong
	 * @date  2019-5-4 上午11:03:23
	 * @param userId 用户编号
	 * @param roleId 角色编号
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param town 乡
	 * @param schoolType 学段
	 * @param schoolId 学校
	 * @param gradeNo 年级号
	 * @param classId 班级编号
	 * @return
	 * @throws WEBException
	 */
	Integer addRoleUserInfo(Integer userId,Integer roleId, String prov, String city,
			String county, String town, Integer schoolType, Integer schoolId,
			Integer gradeNo, Integer classId)throws WEBException;
	
	/**
	 * 修改角色用户信息
	 * @author zong
	 * @date  2019-5-4 上午11:04:55
	 * @param id 角色用户编号
	 * @param userId 用户编号
	 * @param roleId 角色编号
	 * @return 
	 * @throws WEBException
	 */
	boolean updateRoleUserById(Integer id,Integer userId,Integer roleId)throws WEBException;
	
	
}
