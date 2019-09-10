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
	List<NetTeacherStudent> findNTByStuId(Session sess,int stuId);
	/**
	 * 根据网络导师用户编号获取绑定学生
	 * @author zong
	 * 2019-5-23上午11:00:33
	 * @param sess
	 * @param ntId 用户编号
	 * @return
	 */
	List<NetTeacherStudent> findNTByntId(Session sess,int ntId);
	/**
	 * 根据网络导师用户编号,绑定状态获取绑定学生
	 * @author zong
	 * 2019-5-27上午09:41:30
	 * @param sess
	 * @param ntId 用户编号
	 * @param bindSta 绑定状态(1付费)
	 * @return
	 */
	List<NetTeacherStudent> findNTByntId(Session sess,int ntId,Integer bindSta);
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
		
		
}
