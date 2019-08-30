package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.Email;

public interface EmailManager {

	Integer addEmail(Integer sendUserId,String title,String content,String emailType,Integer toUserId) throws WEBException;
	
	void delBatchInfoByIdStr(String emailIdStr) throws WEBException;
	
	List<Email> listPageInfoByOpt(Integer userId,String title,String sDate,String eDate,String emailType,int pageNo,int pageSize) throws WEBException;
	
	Integer getCountByOpt(Integer userId,String title,String sDate,String eDate,String emailType) throws WEBException;
}
