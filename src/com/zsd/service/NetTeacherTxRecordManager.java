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
	 * @param userId 用户编号
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherTxRecord> listnTxReCordByNtId(Integer userId,String txDate,Integer operFlag, Integer pageNo, Integer pageSize)throws WEBException;
	/**
	 * 	根据网络导师编号获取提现记录数
	 * @author zong
	 * 2019-5-27下午03:52:12
	 * @param userId 用户编号
	 * @return
	 * @throws WEBException
	 */
	Integer getnTxReCordCount(Integer userId,String txDate,Integer operFlag)throws WEBException;
	/**
	 * 根据主键获取账单信息
	 * @author zdf
	 * 2019-9-11 下午03:07:26
	 * @param Id 主键
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherTxRecord> listnTxReCordById( Integer Id)throws WEBException;
	
	/**
	 * 增加提现/返现记录信息
	 * @param stuId 学生编号
	 * @param teaId 导师编号
	 * @param txFee 提现费用
	 * @param bankName 银行名称
	 * @param bankNo 银行卡号
	 * @param operateUserId 操作人员编号（等于-1的时候表示返现记录，0时表示提现记录未到账，大于0时表示提现记录已到账）
	 * @param remark 备注
	 * @return
	 * @throws WEBException
	 */
	Integer addTX(Integer stuId,Integer teaId,Integer txFee,String bankName,String bankNo,
			Integer operateUserId,String remark)throws WEBException;
}
