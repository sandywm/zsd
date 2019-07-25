package com.zsd.service;

import com.zsd.exception.WEBException;

public interface TeaQueManager {

	Integer addTQ(Integer loreId,Integer queNum,String queTitle,String queSub,String queAnswer,
			String queResolution,String queType,String queType2,Integer teaId) throws WEBException;
	
	boolean updateInUseById(Integer tqId,Integer inUse) throws WEBException;
	
	boolean updateInfoById(Integer tqId,String queSub,String queAnswer,
			String queResolution,String queType,String queType2) throws WEBException;
	
//	List<>
}
