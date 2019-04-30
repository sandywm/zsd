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

}
