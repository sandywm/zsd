package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.NetTeacherStudioRelationInfo;

public interface NetTeacherStudioRelationManager {
	/**
	 * 添加网络老师工作室老师信息
	 * @author zdf
	 * 2019-7-25 下午05:15:20
	 * @param ntStudioId  工作室编号
	 * @param teaId 网络老师编号
	 * @param addTime 加入时间
	 * @param outTime 离开时间
	 * @return
	 * @throws WEBException
	 */
	Integer addNTStudioRelation(Integer ntStudioId,Integer teaId, String addTime,String outTime)throws WEBException;
	/**
	 * sss
	 * @author zdf
	 * 2019-7-30 上午09:42:40
	 * @param ntStudioId
	 * @return
	 * @throws WEBException
	 */
	List<NetTeacherStudioRelationInfo> listInfoByNtStudioId(Integer ntStudioId)throws WEBException;
}
