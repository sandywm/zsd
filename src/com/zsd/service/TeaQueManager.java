package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.TeaQueInfo;

public interface TeaQueManager {

	/**
	 * 增加老师上传家庭作业题库
	 * @author wm
	 * @date 2019-7-25 下午05:08:03
	 * @param loreId 知识点编号
	 * @param queNum 题库顺序
	 * @param queTitle 题库标题
	 * @param queSub 题库题干
	 * @param queAnswer 题库答案
	 * @param queResolution 题库解析
	 * @param queType 题型一
	 * @param queType2 题型二
	 * @param teaId 上传老师编号
	 * @return
	 * @throws WEBException
	 */
	Integer addTQ(Integer loreId,Integer queNum,String queTitle,String queSub,String queAnswer,
			String queResolution,String queType,String queType2,Integer teaId) throws WEBException;
	
	/**
	 * 设置题库有/无效
	 * @author wm
	 * @date 2019-7-25 下午05:09:41
	 * @param tqId 题库编号
	 * @param inUse 有/无效状态(0：有效，1：无效)
	 * @return
	 * @throws WEBException
	 */
	boolean updateInUseById(Integer tqId,Integer inUse) throws WEBException;
	
	/**
	 * 修改题库明细
	 * @author wm
	 * @date 2019-7-25 下午05:12:39
	 * @param tqId 题库编号
	 * @param queSub 题库题干
	 * @param queAnswer 题库答案
	 * @param queResolution 题库解析
	 * @param queType 题型一
	 * @return
	 * @throws WEBException
	 */
	boolean updateInfoById(Integer tqId,String queSub,String queAnswer,
			String queResolution,String queType) throws WEBException;
	
	/**
	 * 根据条件是否获取老师自传家庭作业信息列表记录列表
	 * @author wm
	 * @date 2019-7-25 下午05:14:30
	 * @param loreId 知识点编号(0表示全部)
	 * @param teaId 老师编号(0表示全部)
	 * @param pageFlag 分页标记(true：分页,false：不分页)
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<TeaQueInfo> listInfoByOpt(Integer loreId,Integer teaId,boolean pageFlag,Integer pageNo,Integer pageSize) throws WEBException;
	
	/**
	 * 根据条件是否获取老师自传家庭作业信息列表记录条数
	 * @author wm
	 * @date 2019-7-25 下午05:25:42
	 * @param loreId 知识点编号(0表示全部)
	 * @param teaId 老师编号(0表示全部)
	 * @return
	 * @throws WEBException
	 */
	Integer getCountByOpt(Integer loreId,Integer teaId) throws WEBException;
}
