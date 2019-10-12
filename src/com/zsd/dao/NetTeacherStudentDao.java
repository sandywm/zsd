package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.NetTeacherStudent;

/**
 * 网络导师学生Dao
 * @author zong
 * @date 2019-4-30
 */
public interface NetTeacherStudentDao {
	/**
	 * 根据主键加载网络导师学生信息实体
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:11:30
	 * @param sess
	 * @param id 网络导师学生主键值
	 * @return 网络导师学生信息PO
	 */
	NetTeacherStudent get(Session sess,int id);
	
	/**
	 * 保存网络导师学生信息信息实体，新增一条网络导师学生信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:12:42
	 * @param sess
	 * @param nts 保存的网络导师学生信息实例
	 */
	void save(Session sess,NetTeacherStudent nts);
	
	/**
	 * 删除网络导师学生信息实体，删除一条网络导师学生信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:13:01
	 * @param sess
	 * @param nts 删除的网络导师学生信息实体
	 */
	void delete(Session sess,NetTeacherStudent nts);
	
	/**
	 * 根据主键删除网络导师学生信息实体，删除一条网络导师学生信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:15:19
	 * @param sess
	 * @param id 需要删除网络导师学生信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条网络导师学生信息记录
	 * @description
	 * @author zong
	 * @date 2019-4-28 下午03:16:39
	 * @param sess
	 * @param nts 需要更新的网络导师学生信息
	 */
	void update(Session sess,NetTeacherStudent nts);
	/**
	 * 根据学生用户编号获取绑定导师
	 * @author zong
	 * 2019-5-23上午10:59:15
	 * @param sess
	 * @param stuId 用户编号
	 * @return
	 */
	List<NetTeacherStudent> findNTByStuId(Session sess,Integer stuId);
	/**
	 * 根据网络导师用户编号获取绑定学生
	 * @author zong
	 * 2019-5-23上午11:00:33
	 * @param sess
	 * @param userId 用户编号
	 * @return
	 */
	List<NetTeacherStudent> findNTByntId(Session sess,Integer userId);
	/**
	 * 根据网络导师用户编号,绑定状态获取绑定学生
	 * @author zong
	 * 2019-5-27上午09:41:30
	 * @param sess
	 * @param userId 用户编号
	 * @param bindSta 绑定状态(1付费)
	 * @return
	 */
	List<NetTeacherStudent> findNTByntId(Session sess,Integer userId,String stuName,Integer bindSta,Integer pageNo,Integer pageSize);
	/**
	 * 根据网络导师用户编号,绑定状态获取绑定学生
	 * @author zdf
	 * 2019-9-16 下午02:08:49
	 * @param sess
	 * @param ntId
	 * @param bindSta
	 * @return
	 */
	List<NetTeacherStudent> findNtsByNtId(Session sess,Integer ntId,Integer bindSta);
	/**
	 * 根据网络导师用户编号,绑定状态获取绑定学生总记录数
	 * @author zdf
	 * 2019-9-16 上午11:41:09
	 * @param sess
	 * @param ntId
	 * @param bindSta
	 * @return
	 */
	Integer getNTByNTIdCount(Session sess,Integer userId,String stuName,Integer bindSta);
	/**
	 * 根据绑定状态,学生姓名查看网络导师学生绑定信息(我的班级)
	 * @author zong
	 * 2019-5-29上午11:33:39
	 * @param sess
	 * @param stuName 学生姓名
	 * @param bindSta 绑定状态
	 * @param ntId 网络导师(用户编号)
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<NetTeacherStudent> findNTByStuNameOrBindSta(Session sess,Integer ntId,Integer paySta,Integer bindFlag,String stuName,Integer pageNo,Integer pageSize);
	/**
	 * 根据绑定状态,学生姓名查看网络导师学生绑定信息记录数(我的班级)
	 * @author zong
	 * 2019-5-29下午04:24:42
	 * @param sess
	 * @param stuName 学生姓名
	 * @param bindSta 绑定状态
	 * @param ntId 网络导师(用户编号)
	 * @return
	 */
	Integer getNtsBystunameOrBindSta(Session sess,Integer ntId,Integer paySta,Integer bindFlag, String stuName);
	/**
	 * 根据网络导师编号获取班内免费试用,付费学生人数
	 * @author zong
	 * 2019-5-31下午04:46:21
	 * @param sess
	 * @param ntId 网络导师编号
	 * @param bindFlag 绑定状态
	 * @return
	 */
	Integer getByStuNum(Session sess,Integer ntId,Integer bindFlag);
	/**
	 * 根据学科编号,学段 查看学生是否绑定导师
	 * @author zdf
	 * 2019-8-31 上午09:09:14
	 * @param sess
	 * @param stuID 学生编号
	 * @param subID 学科编号
	 * @param schoolType 学段
	 * @return
	 */
	boolean isBindTeaBySubIdAndSchType(Session sess, Integer stuID, Integer subID,Integer schoolType);
	
	/**
	 * 获取绑定日期没结束且未取消未清除的信息列表
	 * @param sess
	 * @param stuId 学生编号
	 * @return
	 */
	List<NetTeacherStudent> findValidInfoByOpt(Session sess,Integer stuId);
	/**
	 * 根据网络导师用户编号获取绑定学生信息
	 * @author zdf
	 * 2019-9-20 下午05:15:20
	 * @param sess
	 * @param userId  用户编号
	 * @param bindFlag  绑定标识
	 * @return
	 */
	List<NetTeacherStudent> findBindStu(Session  sess, Integer userId/*,Integer bindFlag*/);
	/**
	 * 根据学生用户编号获取绑定信息
	 * @author zdf
	 * 2019-9-23 下午03:38:17
	 * @param sess
	 * @param stuId  学生用户编号
	 * @return
	 */
	List<NetTeacherStudent> findBindNt(Session sess, Integer stuId);
	
	/**
	 * 获取指定学生指定学科的绑定日期没结束且未取消未清除的信息
	 * @author wm
	 * @date 2019-9-24 下午05:49:08
	 * @param sess
	 * @param stuId 学生编号
	 * @param subId 科目编号
	 * @return
	 */
	NetTeacherStudent getValidInfoByOpt(Session sess,Integer stuId,Integer subId);
	
	/**
	 * 根据导师用户编号、学生编号获取绑定的信息
	 * @author wm
	 * @date 2019-9-25 下午04:00:16
	 * @param sess
	 * @param userId 导师用户编号
	 * @param stuId 学生编号
	 * @return
	 */
	NetTeacherStudent getEntityInfoByOpt(Session sess,Integer userId,Integer stuId);
	
	/**
	 * 获取指定学生指定科目有无绑定记录列表
	 * @author wm
	 * @date 2019-10-11 上午09:03:15
	 * @param sess
	 * @param stuId 学生编号
	 * @param subId 科目编号
	 * @return
	 */
	List<NetTeacherStudent> listAllInfoByOpt(Session sess,Integer stuId,Integer subId);
	
	/**
	 * 通过条件分页获取学生导师绑定信息
	 * @author wm
	 * @date 2019-10-12 上午08:48:30
	 * @param sess
	 * @param stuAccount 学生账号（""表示全部）
	 * @param stuRealName 学生真实姓名（""表示全部）
	 * @param ntAccount 导师账号（""表示全部）
	 * @param ntRealName 导师姓名（""表示全部）
	 * @param subId 学科编号（0表示全部）
	 * @param schoolType 学段（0表示全部）
	 * @param bindStatus 绑定状态（-2：表示全部，-1：免费试用，0：取消，1：付费绑定，2：免费绑定）
	 * @param bindSdate 开始时间
	 * @param bindEdate 结束时间
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<NetTeacherStudent> findAllPageInfoByOpt(Session sess,String stuAccount,String stuRealName,String ntAccount,String ntRealName,
			Integer subId,Integer schoolType,Integer bindStatus,String bindSdate,String bindEdate,Integer pageNo,Integer pageSize);
	
	/**
	 * 通过条件获取学生导师绑定记录条数
	 * @author wm
	 * @date 2019-10-12 上午08:51:52
	 * @param sess
	 * @param stuAccount 学生账号（""表示全部）
	 * @param stuRealName 学生真实姓名（""表示全部）
	 * @param ntAccount 导师账号（""表示全部）
	 * @param ntRealName 导师姓名（""表示全部）
	 * @param subId 学科编号（0表示全部）
	 * @param schoolType 学段（0表示全部）
	 * @param bindStatus 绑定状态（-2：表示全部，-1：免费试用，0：取消，1：付费绑定，2：免费绑定）
	 * @param bindSdate 开始时间
	 * @param bindEdate 结束时间
	 * @return
	 */
	Integer getCountByOpt(Session sess,String stuAccount,String stuRealName,String ntAccount,String ntRealName,
			Integer subId,Integer schoolType,Integer bindStatus,String bindSdate,String bindEdate);
}
