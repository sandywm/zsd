package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.BuffetMindRelationInfo;

public interface BuffetMindRelationInfoManager {
	/**
	 *  根据自助餐题库编号获取自助餐思维类型关联信息
	 * @author zdf
	 * 2019-7-4 上午11:33:20
	 * @param bqId 自助餐题库编号
	 * @return
	 * @throws WEBException
	 */
	List<BuffetMindRelationInfo> listBmrInfoBybqId(Integer bqId) throws WEBException;

}
