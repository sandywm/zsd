package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.BuffetAbilityRelationInfo;

public interface BuffetAbilityRelationInfoManager {
	/**
	 *  根据自助餐题库编号获取自助餐能力类型关联信息
	 * @author zdf
	 * 2019-7-4 上午11:34:46
	 * @param bqId 自助餐题库编号
	 * @return
	 * @throws WEBException
	 */
	List<BuffetAbilityRelationInfo>  listBarInfoBybqId(Integer bqId)throws WEBException;
}
