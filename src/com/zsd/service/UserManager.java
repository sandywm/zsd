package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.User;

public interface UserManager {
	/**
	 * 添加用户信息实体
	 * 
	 * @author zong
	 * @date 2019-4-29 上午10:21:35
	 * @param userAccount
	 *            用户账户
	 * @param realName
	 *            真实姓名
	 * @param password
	 *            密码
	 * @param mobile
	 *            电话号码
	 * @param lastLoginDate
	 *            最后登录时间
	 * @param lastLoginIp
	 *            最后登录Ip
	 * @param signDate
	 *            注册时间
	 * @param schoolId
	 *            学校编号
	 * @param endDate
	 *            账户到期时间
	 * @param yearSystem
	 *            学校学年制
	 * @param prov
	 *            省
	 * @param city
	 *            市
	 * @return
	 * @throws WEBException
	 */
	Integer addUser(String userAccount, String realName, String password,
			String mobile, String lastLoginDate, String lastLoginIp,
			String signDate, Integer schoolId, String endDate,
			Integer yearSystem, String prov, String city) throws WEBException;

	/**
	 * 更新用户信息实体
	 * 
	 * @author zong
	 * @date 2019-4-29 上午11:57:25
	 * @param id
	 *            用户编号
	 * @param nickName
	 *            网络昵称
	 * @param realName
	 *            真实姓名
	 * @param sex
	 *            性别
	 * @param password
	 *            密码
	 * @param email
	 *            电子邮箱
	 * @param mobile
	 *            电话号码
	 * @param portrait
	 *            头像
	 * @param birthday
	 *            出生日期
	 * @param qq
	 *            QQ
	 * @return
	 * @throws WEBException
	 */
	boolean updateUser(Integer id, String nickName, String realName,
			String sex, String password, String email, String mobile,
			String portrait, String birthday, String qq) throws WEBException;

	/**
	 * 每次登录账户是修改用户信息
	 * 
	 * @author zong
	 * @date 2019-4-29 上午11:53:23
	 * @param id
	 *            用户编号
	 * @param lastLoginDate
	 *            最后登录时间
	 * @param lastLoginIp
	 *            最后登录IP
	 * @param loginTimes
	 *            累计登录次数
	 * @param loginStatus
	 *            登录次数（50次一个周期）
	 * @return
	 * @throws WEBException
	 */
	boolean updateUser(Integer id, String lastLoginDate, String lastLoginIp,
			Integer loginTimes, Integer loginStatus) throws WEBException;

	/**
	 * 根据用户账户获取用户信息
	 * 
	 * @author zong
	 * @date 2019-4-28 下午05:01:48
	 * @param account
	 *            用户账户
	 * @return
	 * @throws WEBException
	 */
	List<User> listInfoByAccount(String account, String password)
			throws WEBException;

	List<User> listInfoByAccount(String account) throws WEBException;
}
