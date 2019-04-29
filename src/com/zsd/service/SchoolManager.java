package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.School;

public interface SchoolManager {

	/**
	 * 增加学校
	 * @author Administrator
	 * @date 2019-4-29 上午11:52:54
	 * @param schoolName 学校名字
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param town 乡
	 * @param schoolType 学段[小学(1),初中(2),高中(3)]
	 * @param yearSystem 学年制（3,4,5,6）
	 * @return
	 * @throws WEBException
	 */
	Integer addSchool(String schoolName,String prov,String city,String county,
			String town,Integer schoolType,Integer yearSystem)throws WEBException;
	
	/**
	 * 修改指定学校的信息
	 * @author Administrator
	 * @date 2019-4-29 上午11:53:52
	 * @param id
	 * @param schoolName 学校名字
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param town 乡
	 * @param schoolType 学段[小学(1),初中(2),高中(3)]
	 * @param yearSystem 学年制（3,4,5,6）
	 * @param showStatus 显示状态（0：显示，1：隐藏）
	 * @return
	 * @throws WEBException
	 */
	boolean updateSchoolInfoById(Integer id,String schoolName, String prov,String city,String county,
			String town,Integer schoolType,Integer yearSystem,Integer showStatus)throws WEBException;
	
	/**
	 * 删除指定学校信息（学校没有学生、老师才能删除）
	 * @author Administrator
	 * @date 2019-4-29 上午11:54:40
	 * @param id 主键
	 * @return
	 * @throws WEBException
	 */
	boolean delSchoolById(Integer id)throws WEBException;
	
	/**
	 * 根据条件分页获取学校信息列表
	 * @author Administrator
	 * @date 2019-4-29 上午11:55:10
	 * @param schoolName 学校名字(""表示全部)
	 * @param prov 省(""表示全部)
	 * @param city 市(""表示全部)
	 * @param county 县(""表示全部)
	 * @param town 乡(""表示全部)
	 * @param schoolType 学段[全部[0],小学(1),初中(2),高中(3)]
	 * @param showStatus 显示状态（-1:全部,0：显示，1：隐藏）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<School> listPageInfoByOpt(String schoolName,String prov,String city,String county,
			String town,Integer schoolType,Integer showStatus,Integer pageNo,Integer pageSize)throws WEBException;
	
	/**
	 * 根据条件获取学校记录
	 * @author Administrator
	 * @date 2019-4-29 上午11:56:46
	 * @param schoolName 学校名字(""表示全部)
	 * @param prov 省(""表示全部)
	 * @param city 市(""表示全部)
	 * @param county 县(""表示全部)
	 * @param town 乡(""表示全部)
	 * @param schoolType 学段[全部[0],小学(1),初中(2),高中(3)]
	 * @param showStatus 显示状态（-1:全部,0：显示，1：隐藏）
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByOpt(String schoolName,String prov,String city,String county,
			String town,Integer schoolType,Integer showStatus)throws WEBException;
	
	/**
	 * 根据学校编号获取学校详细信息
	 * @author Administrator
	 * @date 2019-4-29 上午11:57:08
	 * @param id 主键
	 * @return
	 * @throws WEBException
	 */
	List<School> listInfoById(Integer id)throws WEBException;
	
	/**
	 * 根据条件获取学校列表--正常显示的（下拉框时使用）
	 * @author Administrator
	 * @date 2019-4-29 上午11:57:27
	 * @param prov 省
	 * @param city 市
	 * @param county 县
	 * @param town 乡
	 * @param schoolType 学段
	 * @return
	 * @throws WEBException
	 */
	List<School> listInfoByOpt(String prov,String city,String county,String town,Integer schoolType)throws WEBException;
	
	/**
	 * 根据学校名称获取学校列表
	 * @author Administrator
	 * @date 2019-4-29 下午04:06:04
	 * @param sName 学校名称
	 * @return
	 * @throws WEBException
	 */
	List<School> listInfoBySName(String sName)throws WEBException;
}
