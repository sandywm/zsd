package com.zsd.dao;

import java.util.List;

import org.hibernate.Session;

import com.zsd.module.BuffetSendInfo;

public interface BuffetSendInfoDao {
	/**
	 * 根据主键加载自助餐发布信息信息
	 * @author zdf
	 * @date  2019-6-18 下午05:00:03
	 * @param sess
	 * @param id 主键值
	 * @return
	 */
	BuffetSendInfo get(Session sess,int id);
	
	/**
	 * 保存自助餐发布信息信息实体
	 * @author zdf
	 * @date   2019-6-18 下午05:01:23
	 * @param sess
	 * @param bsInfo 自助餐发布信息实体
	 */
	void save(Session sess,BuffetSendInfo bsInfo);
	
	/**
	 * 删除自助餐发布信息信息实体
	 * @author zdf
	 * @date   2019-6-18 下午05:02:34
	 * @param sess
	 * @param bsInfo 自助餐发布信息实体
	 */
	void delete(Session sess,BuffetSendInfo bsInfo);
	
	/**
	 * 根据主键删除自助餐发布信息信息实体，删除一条自助餐发布信息信息记录
	 * @description
	 * @author zdf
	 * @date  2019-6-18 下午05:03:54
	 * @param sess
	 * @param id 需要删除自助餐发布信息信息的主键
	 */
	void delete(Session sess,int id);
	
	/**
	 * 更新一条自助餐发布信息信息记录
	 * @description
	 * @author zdf
	 * @date  2019-6-18 下午05:04:22
	 * @param sess
	 * @param bsInfo 需要更新的自助餐发布信息信息
	 */
	void update(Session sess,BuffetSendInfo bsInfo);
	/**
	 * 根据指定学生,学科,完成状态,时间段获取自助餐发布信息
	 * @author zdf
	 * 2019-6-18 下午05:09:17
	 * @param sess
	 * @param stuId 学生编号
	 * @param subId  学科编号
	 * @param isfinish 完成状态
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 */
	List<BuffetSendInfo> findBsInfoByOption(Session sess, Integer stuId,Integer subId, Integer isfinish, String starttime, String endtime);
	/**
	 * 根据主键查询自助餐发送信息
	 * @author zdf
	 * 2019-7-1 下午04:51:52
	 * @param sess
	 * @param id 主键
	 * @return
	 */
	List<BuffetSendInfo> findBsInfoById(Session sess,Integer id);
	 
	/**
	 * 根据指定学生,学科,完成状态,时间段分页获取自助餐发布信息
	 * @author wm
	 * @date 2019-6-25 下午05:22:08
	 * @param sess
	 * @param stuId 学生编号
	 * @param subId  学科编号
	 * @param isfinish 完成状态-1(全部),1(完成),2(未完成))
	 * @param sDate 开始时间
	 * @param eDate 结束时间
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	List<BuffetSendInfo> findPageInfoByOption(Session sess, Integer stuId,Integer subId, Integer isfinish, 
			String sDate, String eDate,Integer pageNo,Integer pageSize);
}
