package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.SendHwInfo;

public interface SendHwManager {

	Integer addSendHw(Integer sendUserId,String hwTitle,Integer loreId,Integer classId,String className,Integer subId,
			String endDate,Integer hwType,String sysQueIdArr,String hwQueIdArr,String teaQueIdArr,Integer coin,Integer traceStatus) throws WEBException;
	
	boolean updateInfoById(Integer id,Integer coin,Integer traceStatus,Integer inUse) throws WEBException;
	
	SendHwInfo getEntityById(Integer id) throws WEBException;
	
	List<SendHwInfo> listPageInfoByOpt(Integer sendUserId,Integer classId,Integer hwType,Integer inUse,String sDate,String eDate,Integer pageNo,Integer pageSize) throws WEBException;
}
