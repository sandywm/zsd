package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.User;

public interface UserDao {
	/**
	 * 根据主键加载用户信息实体
	 * @author zong
	 * @date  2019-4-28 下午04:27:39
	 * @param sess
	 * @param id 需要加载的用户信息的主键值
	 * @return 加载的用户信息PO
	 */
	User get(Session sess, int id);

	/**
	 * 保存用户信息实体，新增一条用户信息记录
	 * @author zong
	 * @date  2019-4-28 下午04:29:59
	 * @param sess
	 * @param user  保存的用户信息实例
	 */
	void save(Session sess, User user);
	/**
	 * 更新指定用户信息记录
	 * @author zong
	 * @date  2019-4-28 下午04:32:33
	 * @param sess
	 * @param user 需要更新的用户信息
	 */
	void update(Session sess, User user);

	/**
	 * 根据指定用户账户获取用户信息
	 * @author zong
	 * @date  2019-4-28 下午04:34:13
	 * @param sess
	 * @param account 用户账号
	 * @return
	 */
	List<User> findInfoByAccount(Session sess, String account);
	/**
	 * 根据指定用户账户和密码获取用户信息
	 * @author zong
	 * @date  2019-4-28 下午04:34:13
	 * @param sess
	 * @param account 用户账号
	 * @param password  密码
	 * @return
	 */
	List<User> findInfoByAccPwd(Session sess, String account, String password);
	/**
	 * 根据用户编号获取用户信息
	 * @author zong
	 * 2019-5-13上午09:39:29
	 * @param sess
	 * @param id 用户编号
	 * @return
	 */
	List<User> getEntityById(Session sess, Integer id);
	/**
	 * 根据用户主键密码获取用户信息
	 * @author zong
	 * 2019-5-14下午05:26:29
	 * @param sess
	 * @param id 用户主键
	 * @param passwrod 密码
	 * @return
	 */
	List<User> checkUserPwd(Session sess, Integer id,String password);
	/**
	 * 根据用户条件查询用户信息
	 * @author zong
	 * 2019-5-20上午17:58:05
	 * @param sess
	 * @param accName  账户名称
	 * @param realName 真实姓名
	 * @param schName 学校名称
	 * @param roleId 角色编号
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param schoolType 学段
	 * @param gradeNo 年级编号
	 * @param pageNo  总页数
	 * @param pageSize 每页多少条
	 * @return
	 */
	List<User> findUserInfoByoption(Session sess,String accName,String realName,Integer schoolId,Integer roleId,String prov, String city, String county,Integer schoolType,
			Integer gradeNo,Integer classId,Integer pageNo,Integer pageSize);
	/**
	 * 根据用户条件查询用户信息总记录数
	 * @author zong
	 * 2019-5-20下午17:59:45
	 * @param sess
	 * @param accName  账户名称
	 * @param realName 真实姓名
	 * @param schName 学校名称
	 * @param roleId 角色编号
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param schoolType 学段
	 * @param gradeNo 年级编号
	 * @return 记录数
	 */
	Integer getUserByoptionCount(Session sess,String accName,String realName,Integer schoolId,Integer roleId,String prov, String city, String county,Integer schoolType,
			Integer gradeNo,Integer classId);

}
