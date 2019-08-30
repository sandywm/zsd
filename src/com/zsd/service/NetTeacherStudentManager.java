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
	 * 更新网络导师学生绑定信息
	 * @author zong 
	 * @date  2019-5-4 上午11:32:36
	 * @param id 网络导师学生绑定主键
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
	boolean updateNTSByStuId(Integer id,Integer stuId,Integer teaId,String bindDate,	
			Integer bindStatus,String endDate, Integer clearStatus
			,String clearDate, String cancelDate ,Integer payStatus)throws WEBException;
	
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
	 * @param NTId 用户编号
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherStudent> listByntId(Integer ntId)throws WEBException;
	/**
	 * 根据网络导师用户编号,绑定状态 获取绑定学生
	 * @author zong
	 * 2019-5-27上午09:45:59
	 * @param ntId 用户编号
	 * @param bindSta 绑定状态
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherStudent> listNTByntId(Integer ntId,Integer bindSta)throws WEBException;
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
}
