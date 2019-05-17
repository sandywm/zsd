package com.zsd.service.impl;

import java.sql.Timestamp;
import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.BuffetQueInfo;
import com.zsd.service.BuffetQueInfoManager;

public class BuffetQueManagerImpl implements BuffetQueInfoManager{

	
	@Override
	public Integer addBQ(Integer btId, Integer loreId, Integer num,
			String title, String subject, String answer, Integer lexId,
			Integer tipsId, String resolution, String queType, Integer order,
			String answerA, String answerB, String answerC, String answerD,
			String answerE, String answerF, String operateUserName,
			String operateDate) throws WEBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BuffetQueInfo getEntityById(Integer buffetId) throws WEBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BuffetQueInfo> listInfoByOpt(Integer loreId,
			Integer buffetType, Integer inUse) throws WEBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BuffetQueInfo> listPageInfoByLoreId(Integer loreId, int pageNo,
			int pageSize) throws WEBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getCountByLoreId(Integer loreId) throws WEBException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateInUseStatusById(Integer buffetId, Integer inUse,
			String operateUserName, Timestamp operateDate) throws WEBException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Integer getLastCountByOpt(Integer loreId, Integer basicBuffetTypeId)
			throws WEBException {
		// TODO Auto-generated method stub
		return null;
	}

}
