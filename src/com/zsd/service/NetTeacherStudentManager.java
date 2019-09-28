package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.NetTeacherStudent;

public interface NetTeacherStudentManager {
	/**
	 * 添加网络导师学生绑定信息
	 * @author zong
	 * @date  2019-5-4 上午11:26:31
	 * @param stuId 学生编号
	 * @param teaId 导师编号
	 * @param bindDate 绑定时间
	 * @param bindStatus 绑定状态
	 * @param endDate 到期时间
	 * @param clearStatus 清除状态
	 * @param clearDate 清除时间
	 * @param cancelDate 取消时间
	 * @param payStatus 绑定状态
	 * @return
	 * @throws WEBException
	 */
	Integer addNTS(Integer stuId,Integer teaId,String bindDate,	
			Integer bindStatus,String endDate, Integer clearStatus
			,String clearDate, String cancelDate ,Integer payStatus)throws WEBException;
	/**
	 * 根据主键更新导师学生绑定(取消导师绑定)
	 * @author zdf
	 * 2019-8-30 下午05:32:08
	 * @param id
	 * @param bindStatus
	 * @param cancelDate
	 * @return
	 * @throws WEBException
	 */
	boolean updateNTS(Integer id,Integer bindStatus, String cancelDate )throws WEBException;
	
	/**
	 * 根据学生编号获取网络导师学生绑定信息
	 * @author zong
	 * @date  2019-5-4 上午11:33:52
	 * @param stuId 学生编号
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherStudent> listByStuId(Integer stuId)throws WEBException;
	/**
	 * 根据网络导师用户编号获取绑定学生
	 * @author zong
	 * 2019-5-23上午11:03:45
	 * @param userId 用户编号
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherStudent> listByntId(Integer userId)throws WEBException;
	/**
	 * 根据网络导师用户编号,绑定状态 获取绑定学生
	 * @author zong
	 * 2019-5-27上午09:45:59
	 * @param userId 用户编号
	 * @param bindSta 绑定状态
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherStudent> listNTByntId(Integer userId,String stuName,Integer bindSta,Integer pageNo,Integer pageSize)throws WEBException;
	/**
	 * 根据网络导师用户编号,绑定状态 获取绑定学生总记录数
	 * @author zdf
	 * 2019-9-16 上午11:48:04
	 * @param ntId
	 * @param bindSta
	 * @return
	 * @throws WEBException
	 */
	Integer getNTByntIdCount(Integer userId,String stuName,Integer bindSta)throws WEBException;
	/**
	 * 根据绑定状态,学生姓名查看网络导师学生绑定信息(我的班级)
	 * @author zong
	 * 2019-5-31下午03:48:36
	 * @param ntId 网络导师编号
	 * @param paySta 支付状态
	 * @param bindFlag 绑定标识
	 * @param stuName 学生姓名
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<NetTeacherStudent> listNTByStuNameOrBindSta(Integer ntId,Integer paySta,
			Integer bindFlag,String stuName,Integer pageNo,Integer pageSize)throws WEBException;
	/**
	 * 根据绑定状态,学生姓名查看网络导师学生绑定记录数(我的班级)
	 * @author zong
	 * 2019-5-31下午03:49:08
	 * @param ntId 网络导师编号
	 * @param paySta 支付状态
	 * @param bindFlag 绑定标识
	 * @param stuName 学生姓名
	 * @return
	 */
	Integer getNtsBystunameOrBindSta(Integer ntId,Integer paySta,Integer bindFlag, String stuName)throws WEBException;
	/**
	 * 根据网络导师编号获取班内免费试用,付费学生人数
	 * @author zong
	 * 2019-5-31下午05:00:07
	 * @param ntId 网络导师编号
	 * @param bindFlag 绑定状态
	 * @return
	 * @throws WEBException
	 */
	Integer getByStuNum(Integer ntId, Integer bindFlag)throws WEBException; 
	
	/**
	 * 当学生升学时，之前与之绑定的网络导师将被取消，修改clearStatus的值为1,bindStatus为0
	 * @author wm
	 * @date 2019-8-30 上午11:14:54
	 * @param id
	 * @return
	 * @throws WEBException
	 */
	boolean clearUserNetTeacher(Integer id)throws WEBException;
	
	 /**
	  *  根据学科编号,学段 查看学生是否绑定导师
	  * @author zdf
	  * 2019-8-31 上午09:15:28
	  * @param stuId 学生编号
	  * @param subId 学科编号
	  * @param schoolType 学段
	  * @return
	  * @throws WEBException
	  */
	 boolean  isBindTeaBySubIdAndSchType(Integer stuId ,Integer subId,Integer schoolType)throws WEBException;
	 
	 /**
	  * 获取绑定日期没结束且未取消未清除的信息列表
	  * @param stuId 学生编号
	  * @return
	  * @throws WEBException
	  */
	 List<NetTeacherStudent> listValidInfoByOpt(Integer stuId)throws WEBException;
	 
	 /**
	  * 根据主键获取实体信息
	  * @author wm
	  * @date 2019-9-11 下午05:24:24
	  * @param id
	  * @return
	  * @throws WEBException
	  */
	 NetTeacherStudent getEntityById(Integer id)throws WEBException;
	 /**
	  *  根据网络导师用户编号,绑定状态 获取绑定学生
	  * @author zdf
	  * 2019-9-16 下午02:10:17
	  * @param ntId
	  * @param bindSta
	  * @return
	  * @throws WEBException
	  */
	 List<NetTeacherStudent> listNtsByNtId(Integer ntId,Integer bindSta) throws WEBException;
	 /**
	  * 根据网络导师用户编号获取绑定学生信息
	  * @author zdf
	  * 2019-9-20 下午05:42:45
	  * @param userId 导师用户编号
	  * @param bindFlag 绑定标识
	  * @return
	  * @throws WEBException
	  */
	 List<NetTeacherStudent> listBindStu(Integer userId/*,Integer bindFlag*/)throws WEBException;
	 /**
	  *  根据学生用户编号获取绑定信息
	  * @author zdf
	  * 2019-9-23 下午03:51:28
	  * @param sess
	  * @param stuId 学生用户编号
	  * @return
	  * @throws WEBException
	  */
	 List<NetTeacherStudent> listBindNt(Integer stuId)throws WEBException;
	 
	 /**
	  * 获取指定学生指定学科的绑定日期没结束且未取消未清除的信息
	  * @author wm
	  * @date 2019-9-24 下午05:53:14
	  * @param stuId 学生编号
	  * @param subId 科目编号
	  * @return
	  * @throws WEBException
	  */
	 NetTeacherStudent getValidInfoByOpt(Integer stuId,Integer subId)throws WEBException;
	 
	 /**
	  * 根据导师用户编号、学生编号获取绑定的信息
	  * @author wm
	  * @date 2019-9-25 下午04:05:54
	  * @param ntId 导师用户编号
	  * @param stuId 学生编号
	  * @return
	  * @throws WEBException
	  */
	 NetTeacherStudent getEntityInfoByOpt(Integer userId,Integer stuId)throws WEBException;
	 
	 /**
	  * 修改绑定信息
	  * @author wm
	  * @date 2019-9-28 下午06:03:44
	  * @param id
	  * @param bindDate 绑定日期
	  * @param bindStatus 绑定状态
	  * @param endDate 结束日期
	  * @param payStatus 付款状态
	  * @return
	  * @throws WEBException
	  */
	 boolean updateInfoById(Integer id,String bindDate,Integer bindStatus,String endDate,Integer payStatus)throws WEBException;
}
