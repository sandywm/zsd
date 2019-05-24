package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.NetTeacherInfo;


public interface NetTeacherInfoManager {
	/**
	 * 添加网络导师信息
	 * @author zong
	 * @date  2019-5-4 下午04:55:48
	 * @param teaId 网络导师（用户编号）
	 * @param subId 科目编号
	 * @param schoolType 学校类型
	 * @param baseMoney 辅导价格
	 * @param teacherIntro 导师简介 
	 * @param honorPic 个人荣誉
	 * @param bankName 银行名称
	 * @param bankNumber 银行卡号
	 * @param teaSign  个人签名
	 * @param teaEdu 导师学历
	 * @param graduateSchool 毕业院校
	 * @param major 专业
	 * @param schoolAge 教龄
	 * @param checkStatus 审核状态
	 * @param teaScore  导师评分
	 * @return
	 */
	Integer addNtInfo(Integer teaId, Integer subId, Integer schoolType,
			Integer baseMoney, String teacherIntro, String honorPic,
			String bankName, String bankNumber, String teaSign, String teaEdu,
			String graduateSchool, String major, Integer schoolAge,
			Integer checkStatus, Integer teaScore) throws WEBException;
	/**
	 * 更新网络导师信息
	 * @author zong
	 * @date  2019-5-4 下午05:01:13
	 * @param id 网络导师编号
	 * @param teaId 网络导师（用户编号）
	 * @param subId 科目编号
	 * @param schoolType 学校类型
	 * @param baseMoney 辅导价格
	 * @param teacherIntro 导师简介 
	 * @param honorPic 个人荣誉
	 * @param bankName 银行名称
	 * @param bankNumber 银行卡号
	 * @param teaSign  个人签名
	 * @param teaEdu 导师学历
	 * @param graduateSchool 毕业院校
	 * @param major 专业
	 * @param schoolAge 教龄
	 * @param checkStatus 审核状态
	 * @param teaScore  导师评分
	 * @return
	 */
	boolean updateNtInfo(Integer id,Integer teaId, Integer subId,
			Integer schoolType, Integer baseMoney, String teacherIntro,
			String honorPic, String bankName, String bankNumber,
			String teaSign, String teaEdu, String graduateSchool, String major,
			Integer schoolAge, Integer checkStatus, Integer teaScore) throws WEBException;
	/**
	 * 根据老师编号修改老师基本信息
	 * @author zong
	 * 2019-5-11下午05:08:05
	 * @param id 主键
	 * @param realName 真实姓名
	 * @param nickName 网络昵称
	 * @param teaSign  个性签名
	 * @param teaEdu 学历
	 * @param graduateSchool 毕业院校
	 * @param major 专业
	 * @param schoolAge 教龄
	 * @param sex 性别
	 * @param birthday 出生日期
	 * @return
	 * @throws WEBException
	 */
	boolean updateNtBybasicInfo(Integer id, String realName,String nickName,
			String teaSign, String teaEdu, String graduateSchool, String major,
			Integer schoolAge,String sex,String birthday) throws WEBException;
	/**
	 * 根据用户编号获取网络导师信息
	 * @author zong
	 * 2019-5-14下午03:53:38
	 * @param uid
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherInfo> listntInfoByuserId(Integer uid)throws WEBException; 
	/**
	 * 根据主键修改网络导师的审核状态
	 * @author zong
	 * 2019-5-18下午04:47:56
	 * @param id
	 * @param checkStatus
	 * @return
	 * @throws WEBException
	 */
	boolean updateNtInfoByCheckSta(Integer id,Integer checkStatus) throws WEBException;

}
