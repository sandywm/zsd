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

}
