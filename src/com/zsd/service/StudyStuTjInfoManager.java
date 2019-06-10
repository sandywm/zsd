package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.StudyStuTjInfo;

/** 
 * @author zong
 * @version 2019-6-10 上午08:38:55
 */
public interface StudyStuTjInfoManager {
	/**
	 * 根据用户编号,科目编号,开始,结束时间获取学生学习统计记录
	 * @author zong
	 * 2019-6-10上午10:15:13
	 * @param userId 用户编号
	 * @param subId 科目编号
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @return
	 * @throws WEBException
	 */
	List<StudyStuTjInfo> findInfoByOpt(Integer userId,Integer subId,String startTime,String endTime)throws WEBException;
}
