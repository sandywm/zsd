package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.RoleUserInfo;

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
	/**
	 * 根据省市县查询角色用户信息
	 * @author zong
	 * 2019-5-8上午09:15:03
	 * @param sess
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param schoolType 学段
	 * @param schoolId 学校编号
	 * @param gradeNo 年级编号
	 * @param classId	班级编号
	 * @return
	 * @throws WEBException
	 */
	List<RoleUserInfo> listUserRoleInfoByPosition(String prov, String city, String county,Integer schoolType,
			Integer schoolId,Integer gradeNo,Integer classId)throws WEBException;
	/**
	 * 根据用户条件查询用户信息
	 * @author zong
	 * 2019-5-9上午09:58:05
 	 * @param accName  账户名称
	 * @param realName 真实姓名
	 * @param schName 学校名称
	 * @param roleId 角色编号
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param schoolType 学段
	 * @param schoolId 学校编号
	 * @param gradeNo 年级编号
	 * @param pageNo  总页数
	 * @param pageSize 每页多少条
	 * @return
	 * @throws WEBException
	 */
	List<RoleUserInfo> listUserRoleInfoByoption(String accName, String realName, Integer schoolId, Integer roleId,
			String prov, String city, String county, Integer schoolType,
			Integer gradeNo, Integer classId, Integer pageNo,
			Integer pageSize) throws WEBException;
	/**
	 * 根据用户条件获取总记录数
	 * @author zong
	 * 2019-5-10下午05:07:59
	 * @param accName  账户名称
	 * @param realName 真实姓名
	 * @param schName 学校名称
	 * @param roleId 角色编号
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param schoolType 学段
	 * @param schoolId 学校编号
	 * @param gradeNo 年级编号
	 * @return 记录数
	 * @throws WEBException
	 */
	Integer listRuInfoByoption(String accName, String realName, Integer schoolId, Integer roleId,
			String prov, String city, String county, Integer schoolType,
			Integer gradeNo, Integer classId) throws WEBException;
	
	
}
