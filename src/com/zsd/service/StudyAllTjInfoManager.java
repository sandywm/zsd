package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.StudyAllTjInfo;

/** 
 * @author zong
 * @version 2019-6-10 上午08:39:14
 */
public interface StudyAllTjInfoManager {
	/**
	 * 根据科目编号,开始,结束时间获取全平台知识点记录
	 * @author zong
	 * 2019-6-10上午10:46:41
	 * @param sess
	 * @param subId 科目编号
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 * @throws WEBException
	 */
	List<StudyAllTjInfo> findInfoByOpt(Integer subId,String startTime,String endTime)throws WEBException;
}
