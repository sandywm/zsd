package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.NetTeacherReturnRecord;

/** 
 * @author zong
 * @version 2019-5-28 下午03:55:19
 */
public interface NetTeacherReturnRecordManager {
	/**
	 * 根据网络导师编号获取返现信息
	 * @author zong
	 * 2019-5-28下午03:57:01
	 * @param ntId 网络导师编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherReturnRecord> listnTrRecordByNtId(Integer ntId, Integer pageNo, Integer pageSize) throws WEBException;
	/**
	 *  根据网络导师编号获取返现信息记录数
	 * @author zong
	 * 2019-5-28下午04:12:16
	 * @param ntId 网络导师编号
	 * @return
	 * @throws WEBException
	 */
	Integer getnTrRecordCount(Integer ntId)throws WEBException;
}
