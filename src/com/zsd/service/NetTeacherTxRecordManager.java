package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.NetTeacherTxRecord;

/** 
 * @author zong
 * @version 2019-5-25 上午10:05:02
 */
public interface NetTeacherTxRecordManager {
	/**
	 * 根据网络导师编号获取提现记录
	 * @author zong
	 * 2019-5-25上午10:05:46
	 * @param ntId 网络导师编号
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherTxRecord> findnTxReCordByNtId(Integer ntId,Integer operId)throws WEBException;
	/**
	 * 根据网络导师编号分页获取提现信息
	 * @author zong
	 * 2019-5-27上午11:25:20
	 * @param ntId 网络导师编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherTxRecord> listnTxReCordByNtId(Integer ntId, Integer pageNo, Integer pageSize)throws WEBException;
	/**
	 * 	根据网络导师编号获取提现记录数
	 * @author zong
	 * 2019-5-27下午03:52:12
	 * @param ntId 网络导师编号
	 * @return
	 * @throws WEBException
	 */
	Integer getnTxReCordCount(Integer ntId)throws WEBException;
}
