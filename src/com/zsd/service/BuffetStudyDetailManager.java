package com.zsd.service;

import java.util.List;

import com.zsd.exception.WEBException;
import com.zsd.module.BuffetStudyDetailInfo;

public interface BuffetStudyDetailManager {

	/**
	 * 批量增加自助餐学习详情信息
	 * @author wm
	 * @date 2019-6-26 上午09:19:57
	 * @param bsId 自助餐发送编号
	 * @param bqIdStr 自助餐题库编号组合，多个用逗号隔开
	 * @return
	 * @throws WEBException
	 */
	Integer addBatchBSD(Integer bsId,String bqIdStr) throws WEBException;
	
	/**
	 * 根据巴菲特编号插入自己做题时的情况
	 * @author wm
	 * @date 2019-6-26 上午09:20:34
	 * @param bsdId 主键
	 * @param myAnswer 自己选择的答案
	 * @param result 1：正确，0：错误
	 * @param studyTime 学习时间
	 * @param a 选项A
	 * @param b 选项B
	 * @param c 选项C
	 * @param d 选项D
	 * @param e 选项E
	 * @param f 选项F
	 * @return
	 * @throws WEBException
	 */
	boolean updateBuffetStudyDetailById(Integer bsdId,String myAnswer,Integer result,
			String studyTime,String a,String b,String c,String d,String e,String f)throws WEBException;
	
	/**
	 * 根据主键修改巴菲特学习记录的溯源完成标记（-1不修改）、解析完成标记（-1不修改）
	 * @author wm
	 * @date 2019-6-26 上午09:21:05
	 * @param bsdId 主键
	 * @param traceCompleteFlag 溯源完成标记
	 * @param currCompleteFlag 解析完成标记
	 * @return
	 * @throws WEBException
	 */
	boolean updateStatusById(Integer bsdId,Integer traceCompleteFlag, Integer currCompleteFlag)throws WEBException;
	
	/**
	 * 根据自助餐发送编号获取自助餐学习题库列表
	 * @author wm
	 * @date 2019-6-26 上午09:23:48
	 * @param bsId 自助餐发送编号
	 * @return
	 * @throws WEBException
	 */
	List<BuffetStudyDetailInfo> listInfoByBsId(Integer bsId)throws WEBException;
	
	/**
	 * 根据主键获取自助餐学习题库详情
	 * @author wm
	 * @date 2019-6-26 上午09:24:47
	 * @param bsdId 主键
	 * @return
	 * @throws WEBException
	 */
	BuffetStudyDetailInfo getEntityById(Integer bsdId)throws WEBException;
	/**
	 * 根据学生编号获取自助餐学习详情列表
	 * @author zdf
	 * 2019-7-2 下午04:45:46
	 * @param stuId 学生编号
	 * @param subName 学科名称
	 * @param succFlag  成功状态
	 * @return
	 * @throws WEBException
	 */
	List<BuffetStudyDetailInfo> listInfoByStuId(Integer stuId,String subName, Integer succFlag)throws WEBException;
}
