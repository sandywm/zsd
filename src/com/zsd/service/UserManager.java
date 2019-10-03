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
	 * 更新用户信息实体(学生个人中心)
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
	 * @param birthday
	 *            出生日期
	 * @param qq
	 *            QQ
	 * @return
	 * @throws WEBException
	 */
	boolean updateUserStu(Integer id, String nickName, String realName,
			String sex,  String birthday, String qq) throws WEBException;

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
	boolean updateUserLogin(Integer id, String lastLoginDate, String lastLoginIp,
			Integer loginTimes, Integer loginStatus) throws WEBException;
	/**
	 * 修改指定用户的账户状态,截止时间
	 * @author zong
	 * 2019-5-10下午05:15:01
	 * @param id 用户编号
	 * @param accStatus 账户状态
	 * @param endDate 截止时间
	 * @return
	 * @throws WEBException
	 */
	boolean updateUser(Integer id,  Integer accStatus, Integer freeSta, String endDate) throws WEBException;

	/**
	 * 根据用户账户和密码获取用户信息
	 * @author zong
	 * @date  2019-5-4 上午10:06:52
	 * @param account 用户账户
	 * @param password 密码
	 * @return
	 * @throws WEBException
	 */
	List<User> listInfoByAccount(String account, String password)
			throws WEBException;
	/**
	 * 根据用户账户获取用户信息
	 * @author zong
	 * @date 2019-4-28 下午05:01:48
	 * @param account   用户账户
	 * @return
	 * @throws WEBException
	 */
	List<User> listInfoByAccount(String account) throws WEBException;
	/**
	 * 根据用户名和密码判断登录信息
	 * @param account 用户名
	 * @param password 密码
	 * @return
	 * @throws WEBException
	 */
	boolean userLogin(String account,String password) throws WEBException;
	/**
	 * 修改指定用户的金币数,经验,知识典币,账号余额
	 * @author zong
	 * 2019-5-11下午04:21:42
	 * @param id 用户编号
	 * @param coin 金币
	 * @param exp 经验
	 * @param zsdCoin 知识典币
	 * @param accMoney 账号余额
	 * @return 
	 * @throws WEBException
	 */
	boolean updateUser(Integer id,  Integer coin, Integer exp,Integer zsdCoin,Integer accMoney) throws WEBException;
	/**
	 * 修改指定用户的邮箱
	 * @author zong
	 * 2019-5-13上午09:30:51
	 * @param id 用户编号
	 * @param email 电子邮箱地址
	 * @return
	 * @throws WEBException
	 */
	boolean updateUserByEmail(Integer id ,String email) throws WEBException;
	/**
	 * 修改指定用户的电话号码
	 * @author zong
	 * 2019-5-13上午09:32:29
	 * @param id 用户编号
	 * @param mobile 电话号码
	 * @return
	 * @throws WEBException
	 */
	boolean updateUserByMobile(Integer id ,String mobile) throws WEBException;
	/**
	 * 修改指定用户编号的密码
	 * @author zong
	 * 2019-5-13上午09:41:55
	 * @param id 用户编号
	 * @param password 密码
	 * @return
	 * @throws WEBException
	 */
	boolean updateUserBypwd(Integer id,String password) throws WEBException;
	/**
	 * 获取指定编号的用户信息
	 * @author zong
	 * 2019-5-13上午09:43:24
	 * @param sess
	 * @param id 用户编号
	 * @return
	 * @throws WEBException
	 */
	List<User> listEntityById(Integer id) throws WEBException;
	/**
	 * 检查是否为当前用户密码
	 * @author zong
	 * 2019-5-14下午05:33:06
	 * @param id 用户主键
	 * @param password 密码
	 * @return
	 * @throws WEBException
	 */
	boolean checkCurrpwd(Integer id,String password) throws WEBException;
	/**
	 * 根据用户条件获取用户信息
	 * @author zong
	 * 2019-5-21上午10:18:58
	 * @param accName  账户名称
	 * @param realName 真实姓名
	 * @param schName 学校名称
	 * @param roleId 角色编号
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param town 乡镇
	 * @param schoolType 学段
	 * @param gradeNo 年级编号
	 * @param pageNo  总页数
	 * @param pageSize 每页多少条
	 * @return
	 * @throws WEBException
	 */
	List<User> listUserInfoByoption(String accName,
			String realName, Integer schoolId, Integer roleId, String prov,
			String city, String county, String town, Integer schoolType, Integer gradeNo,
			Integer classId, Integer pageNo, Integer pageSize)throws WEBException;
	/**
	 * 根据用户条件获取用户信息总记录数
	 * @author zong
	 * 2019-5-21上午10:21:01
	 * @param accName  账户名称
	 * @param realName 真实姓名
	 * @param schName 学校名称
	 * @param roleId 角色编号
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param town 乡镇
	 * @param schoolType 学段
	 * @param gradeNo 年级编号
	 * @return
	 * @throws WEBException
	 */
	Integer getUserByoptionCount(String accName,
			String realName, Integer schoolId, Integer roleId, String prov,
			String city, String county, String town, Integer schoolType, Integer gradeNo,
			Integer classId)throws WEBException;
	/**
	 * 更新用户头像信息
	 * @author zong
	 * 2019-6-3下午04:40:23
	 * @param id 用户编号
	 * @param portrait 头像地址
	 * @return
	 * @throws WEBException
	 */
	boolean updateUserByHead(Integer id ,String portrait) throws WEBException;
	/**
	 * 判断手机号码是否存在
	 * @author zong
	 * 2019-6-10下午03:25:37
	 * @param mobile 手机号码
	 * @return
	 * @throws WEBException
	 */
	boolean  checkUserMobile(String mobile) throws WEBException;
	
	/**
	 * 修改学生的学校信息
	 * @author wm
	 * @date 2019-8-31 下午05:06:26
	 * @param userId 学生编号
	 * @param schoolId 学校编号
	 * @param yearSystem 学年制
	 * @return
	 * @throws WEBException
	 */
	boolean updateSchoolInfo(Integer userId,Integer schoolId,Integer yearSystem) throws WEBException;
	
	/**
	 * 修改学生升学标记
	 * @author wm
	 * @date 2019-9-9 下午05:30:11
	 * @param userId
	 * @param dateFlag 每年九月1日
	 * @return
	 * @throws WEBException
	 */
	boolean updateStuDateFlagById(Integer userId,String dateFlag) throws WEBException;
}
